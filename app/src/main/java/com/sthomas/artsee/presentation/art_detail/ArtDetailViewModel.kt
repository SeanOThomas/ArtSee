package com.sthomas.artsee.presentation.art_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sthomas.artsee.di.Keys
import com.sthomas.artsee.domain.repository.ArtRepository
import com.sthomas.artsee.ui.Args
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named

@HiltViewModel
class ArtDetailViewModel @Inject constructor(
    @Named(Keys.remote)
    private val remoteRepository: ArtRepository,
    @Named(Keys.storage)
    private val storageRepository: ArtRepository,
    initialState: ArtDetailState,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var state by mutableStateOf(initialState)
        private set

    init {
        savedStateHandle.get<String>(Args.artId)?.let {
            getArtDetail(it)
        }
    }

    private fun getArtDetail(artId: String) {
        viewModelScope.launch {
            state = state.copy(
                isLoading = true,
            )
            // in case the art was saved, try to load art from storage
            // before loading from remote.
            val artFromStorage = storageRepository.getArt(artId)
            state = state.copy(
                art = artFromStorage.data,
                isLoading = false,
                isSaved = true
            )
        }
    }

    fun saveArt() {
        viewModelScope.launch {
            if (state.isSaved) {
                // unsave the art
                state = state.copy(
                    isSaving = true,
                )
                storageRepository.unsaveArt(checkNotNull(state.art))
                state = state.copy(
                    isSaving = false,
                    isSaved = false
                )
            } else {
                // save the art
                state = state.copy(
                    isSaving = true,
                )
                storageRepository.saveArt(checkNotNull(state.art))
                state = state.copy(
                    isSaving = false,
                    isSaved = true
                )
            }
        }
    }
}