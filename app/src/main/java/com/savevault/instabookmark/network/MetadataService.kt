package com.savevault.instabookmark.network

import retrofit2.http.GET
import retrofit2.http.Query

interface MetadataService {
    @GET("/")
    suspend fun fetchMetadata(
        @Query("url") postUrl: String
    ): MicrolinkResponse
}

data class MicrolinkResponse(
    val status: String?,
    val data: MicrolinkData?
)

data class MicrolinkData(
    val author: String?,
    val title: String?, // acts as caption
    val image: MicrolinkImage?
)

data class MicrolinkImage(
    val url: String?
)
