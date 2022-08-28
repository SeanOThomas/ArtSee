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
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.modules.EmptySerializersModule
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import java.util.logging.Level
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @OptIn(ExperimentalSerializationApi::class)
    fun provideArtRemoteApi(): ArtRemoteAPI {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
        val loggingClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        val contentType = "application/json".toMediaType()

        val json = Json {
            ignoreUnknownKeys = true
            prettyPrint = true
        }
        return Retrofit.Builder()
            .baseUrl("https://api.artic.edu/api/v1/")
            .addConverterFactory(
                json
                    .asConverterFactory(contentType)

            )
            .client(loggingClient)
            .build()
            .create()
    }

    @Provides
    @Singleton
    @Named(Keys.explore)
    fun provideExploreArtRepository(artRemoteAPI: ArtRemoteAPI): ArtRepository {
        return ArtRepositoryRemote(artRemoteAPI)
    }

    @Provides
    @Singleton
    @Named(Keys.saved)
    fun provideSavedArtRepository(@ApplicationContext context: Context): ArtRepository {
        return ArtRepositoryStorage(context)
    }
}