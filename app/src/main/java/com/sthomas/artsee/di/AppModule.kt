package com.sthomas.artsee.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sthomas.artsee.data.remote.ArtRemoteAPI
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @OptIn(ExperimentalSerializationApi::class)
    fun provideArtRemoteApi() : ArtRemoteAPI {
        val contentType = MediaType.parse("application/json")
        requireNotNull(contentType)
        return Retrofit.Builder()
            .baseUrl("https://api.artic.edu/api/v1/")
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
            .create()
    }
}