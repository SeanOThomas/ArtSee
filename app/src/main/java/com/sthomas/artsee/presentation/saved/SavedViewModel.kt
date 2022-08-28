package com.sthomas.artsee.presentation.saved

import androidx.lifecycle.ViewModel
import com.sthomas.artsee.di.Keys
import com.sthomas.artsee.domain.model.ArtPreview
import com.sthomas.artsee.domain.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Named

@HiltViewModel
class SavedViewModel(
    @Named(Keys.saved)
    private val artRepository: ArtRepository
) : ViewModel() {

    fun getSavedArtPreviews() : Flow<List<ArtPreview>> {
        return artRepository.getArtPreviews()
    }
}