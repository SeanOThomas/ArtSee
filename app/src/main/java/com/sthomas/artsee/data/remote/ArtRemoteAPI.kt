package com.sthomas.artsee.data.remote

import com.sthomas.artsee.data.remote.dto.ArtListResponseDto
import com.sthomas.artsee.data.remote.dto.ArtResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ArtRemoteAPI {
    @GET("artworks")
    suspend fun getArtList(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("is_on_view") isOnView: Boolean = true
    ): ArtListResponseDto

    @GET("artworks/{id}")
    suspend fun getArt(
        @Path("id") id: String,
    ): ArtResponseDto
}

