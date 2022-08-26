package com.sthomas.artsee.data.remote

import com.sthomas.artsee.data.remote.dto.ArtworkResponse
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query


interface ArtRemoteAPI {
    @GET("artworks")
    suspend fun getArtList(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): ArtworkResponse

    companion object {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .build()

        var service: ArtRemoteAPI = retrofit.create(ArtRemoteAPI::class.java)
    }
}

