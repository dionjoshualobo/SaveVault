package com.savevault.instabookmark.network

import retrofit2.http.GET
import retrofit2.http.Query

interface InstagramOEmbedService {
    @GET("/instagram_oembed")
    suspend fun fetchMetadata(
        @Query("url") postUrl: String,
        @Query("access_token") accessToken: String
    ): OEmbedResponse
}

data class OEmbedResponse(
    val author_name: String?,
    val title: String?, // caption equivalent
    val thumbnail_url: String?,
    val html: String?
)
