package com.savevault.instabookmark

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.savevault.instabookmark.data.AppDatabase
import com.savevault.instabookmark.data.PostEntity
import com.savevault.instabookmark.data.TagsConverter
import com.savevault.instabookmark.network.InstagramOEmbedService
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val db = AppDatabase.getInstance(application)
    private val postDao = db.postDao()

    // Full list of posts from DB
    private val _allPosts = postDao.getAllPosts()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())
    val allPosts: StateFlow<List<PostEntity>> = _allPosts

    // Search query entered by user
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    // Selected tag filters
    private val _selectedTags = MutableStateFlow<Set<String>>(emptySet())
    val selectedTags: StateFlow<Set<String>> = _selectedTags.asStateFlow()

    // Combined filtered list for UI
    val filteredPosts: StateFlow<List<PostEntity>> = combine(
        _allPosts,
        _searchQuery,
        _selectedTags
    ) { posts, query, tags ->
        posts.filter { post ->
            // Text search across url, caption, author, tags
            val matchesQuery = query.isBlank() || listOf(
                post.url,
                post.caption ?: "",
                post.author ?: "",
                post.tags.joinToString(",")
            ).any { it.contains(query, ignoreCase = true) }

            // Tag filter
            val matchesTags = tags.isEmpty() || tags.all { tag -> post.tags.contains(tag) }

            matchesQuery && matchesTags
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // All distinct tags present in DB (for chip list)
    val allTags: StateFlow<Set<String>> = _allPosts.map { list ->
        list.flatMap { it.tags }.toSet()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptySet())

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://graph.facebook.com") // Instagram OEmbed endpoint base
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val oembedService = retrofit.create(InstagramOEmbedService::class.java)

    // TODO: Replace with your actual Meta App Access Token (AppID|ClientToken)
    private val oembedAccessToken = "YOUR_APP_ID|YOUR_CLIENT_TOKEN"

    fun savePostFromUrl(url: String) {
        viewModelScope.launch {
            try {
                val metadata = oembedService.fetchMetadata(url, oembedAccessToken)
                val post = PostEntity(
                    url = url,
                    caption = metadata.title,
                    author = metadata.author_name,
                    thumbnailUrl = metadata.thumbnail_url,
                    tagsJson = "[]"
                )
                postDao.insert(post)
            } catch (e: Exception) {
                // fallback: store only URL
                val post = PostEntity(url = url, caption = null, author = null, thumbnailUrl = null, tagsJson = "[]")
                postDao.insert(post)
            }
        }
    }

    fun addTags(postId: Long, newTags: List<String>) {
        viewModelScope.launch {
            val post = _allPosts.value.find { it.id == postId } ?: return@launch
            val updated = post.copy(tagsJson = TagsConverter.toJson(newTags))
            postDao.update(updated)
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun toggleTagSelection(tag: String) {
        val current = _selectedTags.value.toMutableSet()
        if (current.contains(tag)) current.remove(tag) else current.add(tag)
        _selectedTags.value = current
    }

    fun deletePost(post: PostEntity) {
        viewModelScope.launch {
            postDao.delete(post)
        }
    }
}
