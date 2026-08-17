package com.savevault.instabookmark.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "posts")
@TypeConverters(TagsConverter::class)
data class PostEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val url: String,
    val caption: String?,
    val author: String?,
    val thumbnailUrl: String?,
    val tagsJson: String = "[]"
) {
    val tags: List<String>
        get() = TagsConverter.fromJson(tagsJson)
}

class TagsConverter {
    companion object {
        private val gson = Gson()
        @JvmStatic
        fun fromJson(json: String): List<String> {
            val type = object : TypeToken<List<String>>() {}.type
            return gson.fromJson(json, type) ?: emptyList()
        }
        @JvmStatic
        fun toJson(list: List<String>): String = gson.toJson(list)
    }
    @TypeConverter
    fun fromTags(tags: List<String>): String = toJson(tags)
    @TypeConverter
    fun toTags(json: String): List<String> = fromJson(json)
}
