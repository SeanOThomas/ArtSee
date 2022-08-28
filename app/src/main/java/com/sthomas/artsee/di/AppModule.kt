package com.sthomas.artsee.di

import android.content.Context
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sthomas.artsee.data.remote.ArtRemoteAPI
import com.sthomas.artsee.data.remote.ArtRepositoryRemote
import com.sthomas.artsee.data.storage.ArtRepositoryStorage
import com.sthomas.artsee.domain.repository.ArtRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
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

    @Provides
    @Singleton
    @Named(Keys.explore)
    fun provideExploreArtRepository(artRemoteAPI: ArtRemoteAPI) : ArtRepository {
        return ArtRepositoryRemote(artRemoteAPI)
    }

    @Provides
    @Singleton
    @Named(Keys.saved)
    fun provideSavedArtRepository(context: Context) : ArtRepository {
        return ArtRepositoryStorage(context)
    }
}