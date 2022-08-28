package com.sthomas.artsee.presentation.explore

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sthomas.artsee.domain.model.ArtPreview
import com.sthomas.artsee.domain.repository.ArtRepository
import javax.inject.Inject

class ExplorePagingSource @Inject constructor(
    private val artRepository: ArtRepository
) :  PagingSource<Int, ArtPreview>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtPreview> {
        return try {
            val page = params.key ?: 1
            val artworkList = artRepository.getPagedArtPreviews(
                page = page,
                limit = 20
            )
            Log.d(TAG, "artworkList size: ${artworkList.size}")
            LoadResult.Page(
                data = artworkList,
                prevKey = null, // only page forward
                nextKey = page + 1
            )
        } catch (e: Exception) {
            Log.e(TAG, "Error requesting paged data: $e")
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, ArtPreview>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
    companion object {
        const val TAG = "ExplorePagingSource"
    }
}