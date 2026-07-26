package com.savevault.instabookmark.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(post: PostEntity): Long

    @Update
    suspend fun update(post: PostEntity)

    @Query("SELECT * FROM posts ORDER BY id DESC")
    fun getAllPosts(): Flow<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE tagsJson LIKE '%' || :tag || '%' ORDER BY id DESC")
    fun getPostsByTag(tag: String): Flow<List<PostEntity>>

    @Query("SELECT * FROM posts WHERE url LIKE '%' || :query || '%' OR caption LIKE '%' || :query || '%' OR author LIKE '%' || :query || '%' OR tagsJson LIKE '%' || :query || '%' ORDER BY id DESC")
    fun searchPosts(query: String): Flow<List<PostEntity>>
}
