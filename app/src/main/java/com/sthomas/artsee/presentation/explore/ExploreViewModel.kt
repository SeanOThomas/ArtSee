package com.sthomas.artsee.presentation.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.sthomas.artsee.di.Keys
import com.sthomas.artsee.domain.repository.ArtRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import javax.inject.Named

/**
 * Expose paging data for the 'Explore' tab of the home screen.
 */
@HiltViewModel
class ExploreViewModel @Inject constructor(
    @Named(Keys.remote)
    private val artRepository: ArtRepository,
) : ViewModel() {

    val exploreArtPager = Pager(
        PagingConfig(pageSize = 20)
    ) {
        ExplorePagingSource(artRepository)
    }.flow.cachedIn(viewModelScope)
}