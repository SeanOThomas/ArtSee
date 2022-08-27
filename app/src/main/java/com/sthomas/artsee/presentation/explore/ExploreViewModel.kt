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

@HiltViewModel
class ExploreViewModel @Inject constructor(
    @Named(Keys.explore)
    private val artRepository: ArtRepository,
) : ViewModel() {

    val flow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        ExplorePagingSource(artRepository)
    }.flow.cachedIn(viewModelScope)
}