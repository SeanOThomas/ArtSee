package com.sthomas.artsee.data.remote

import com.sthomas.artsee.data.remote.dto.Artwork
import com.sthomas.artsee.domain.Art
import com.sthomas.artsee.domain.ArtPreview
import com.sthomas.artsee.domain.ArtRepository
import javax.inject.Inject

class ArtRepositoryRemote @Inject constructor(
    private val artRemoteAPI: ArtRemoteAPI
) : ArtRepository {

    override suspend fun getArtPreviews(page: Int, limit: Int): List<ArtPreview> {
        return artRemoteAPI.getArtList(page, limit).artwork
            .map {
                it.toArtPreview()
            }
    }

    override suspend fun getArt(id: String): Art {
        TODO("Not yet implemented")
    }

    private fun Artwork.toArtPreview() = ArtPreview(
        artId = id,
        thumbnail = "https://www.artic.edu/iiif/2/${id}/full/843,/0/default.jpg"
    )
}