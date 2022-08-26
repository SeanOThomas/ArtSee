package com.sthomas.artsee.data.remote

import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.sthomas.artsee.data.remote.dto.Artwork
import com.sthomas.artsee.domain.Art
import com.sthomas.artsee.domain.ArtPreview
import com.sthomas.artsee.domain.ArtRepository
import kotlinx.coroutines.flow.Flow

class ArtRepositoryRemote(
    val artRemoteAPI: ArtRemoteAPI
) : ArtRepository, PagingSource<Int, ArtPreview>() {
    override fun getPagingArt(): Flow<PagingData<ArtPreview>> {
        TODO("Not yet implemented")

    }

    override fun getArt(id: String): Art {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArtPreview> {
        try {
            val page = params.key ?: 1
            val artworkList = artRemoteAPI.getArtList(
                page = page,
                limit = 20
            ).artwork

            val artPreviewList = artworkList.map { it.toArtPreview() }

            return LoadResult.Page(
                data = artPreviewList,
                prevKey = null, // only page forward
                nextKey = page + 1
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error if it is an
            // expected error (such as a network failure).
            throw e
        }

    }

    override fun getRefreshKey(state: PagingState<Int, ArtPreview>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }

    private fun Artwork.toArtPreview() = ArtPreview(
        artId = id,
        thumbnail = "https://www.artic.edu/iiif/2/${id}/full/843,/0/default.jpg"
    )
}