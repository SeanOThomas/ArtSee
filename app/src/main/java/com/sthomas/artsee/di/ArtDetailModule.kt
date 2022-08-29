package com.sthomas.artsee.di

import com.sthomas.artsee.presentation.art_detail.ArtDetailState
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object ArtDetailModule {
    @Provides
    fun provideInitialArtDetailState(): ArtDetailState {
        return ArtDetailState(
            art = null,
            isLoading = true,
            isSaving = false,
            isSaved = false
        )
    }
}