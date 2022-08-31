package com.sthomas.artsee.presentation.explore

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sthomas.artsee.domain.model.ArtPreview
import com.sthomas.artsee.domain.repository.ArtRepository
import com.sthomas.artsee.domain.repository.Resource
import javax.inject.Inject

class ExplorePagingSource @Inject constructor(
    private val artRepository: ArtRepository
) : PagingSource<Int, ArtPreview>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtPreview> {
        val page = params.key ?: 1
        val resource = artRepository.getPagedArtPreviews(
            page = page,
            limit = 10
        )
        return if (resource is Resource.Success) {
            LoadResult.Page(
                data = resource.data as List<ArtPreview>,
                prevKey = if (page == 1) null else page - 1,
                nextKey = page + 1
            )
        } else {
            LoadResult.Error(Throwable(message = resource.message))
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtPreview>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}