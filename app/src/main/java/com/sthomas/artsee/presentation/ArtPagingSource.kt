package com.sthomas.artsee.presentation

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sthomas.artsee.domain.ArtPreview
import com.sthomas.artsee.domain.ArtRepository
import javax.inject.Inject

class ArtPagingSource @Inject constructor(
    private val artRepository: ArtRepository
) :  PagingSource<Int, ArtPreview>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtPreview> {
        return try {
            val page = params.key ?: 1
            val artworkList = artRepository.getArtPreviews(
                page = page,
                limit = 20
            )
            LoadResult.Page(
                data = artworkList,
                prevKey = null, // only page forward
                nextKey = page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtPreview>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}