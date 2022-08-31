package com.sthomas.artsee.presentation.art_detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.sthomas.artsee.common.arch.StateViewModel
import com.sthomas.artsee.di.Keys
import com.sthomas.artsee.domain.repository.ArtRepository
import com.sthomas.artsee.domain.repository.Resource
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
) : StateViewModel<ArtDetailState>(initialState) {

    init {
        savedStateHandle.get<String>(Args.artId)?.let {
            getArtDetail(it)
        }
    }

    /**
     * Retrieve art data for the provided [artId]. In the case the art was saved,
     * first try storage, then try remote.
     */
    private fun getArtDetail(artId: String) {
        viewModelScope.launch {
            setState {
                copy(isLoading = true)
            }
            val resourceFromStorage = storageRepository.getArt(artId)
            if (resourceFromStorage is Resource.Success && resourceFromStorage.data != null) {
                setState {
                    copy(
                        art = resourceFromStorage.data,
                        isLoading = false,
                        isSaved = true
                    )
                }
            } else {
                val resourceFromRemote = remoteRepository.getArt(artId)
                if (resourceFromRemote is Resource.Success) {
                    setState {
                        copy(
                            art = resourceFromRemote.data,
                            isLoading = false,
                        )
                    }
                } else {
                    setState {
                        copy(
                            isLoading = false,
                            error = resourceFromRemote.message
                        )
                    }
                }
            }
        }
    }

    /**
     * Handles both save and unsave button clicks.
     */
    fun saveArt() {
        viewModelScope.launch {
            setState {
                copy(isSaving = true)
            }
            if (state.isSaved) {
                // unsave the art
                val resource = storageRepository.unsaveArt(checkNotNull(state.art))
                if (resource is Resource.Success) {
                    setState {
                        copy(
                            isSaving = false,
                            isSaved = false
                        )
                    }
                } else {
                    setState {
                        copy(
                            isSaving = false,
                            error = resource.message
                        )
                    }
                }
            } else {
                // save the art
                val resource = storageRepository.saveArt(checkNotNull(state.art))
                if (resource is Resource.Success) {
                    setState {
                        copy(
                            isSaving = false,
                            isSaved = true
                        )
                    }
                } else {
                    setState {
                        copy(
                            isSaving = false,
                            error = resource.message
                        )
                    }
                }
            }
        }
    }
}