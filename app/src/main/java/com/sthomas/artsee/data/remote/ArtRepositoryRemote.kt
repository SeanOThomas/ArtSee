package com.sthomas.artsee.data.remote

import com.sthomas.artsee.data.remote.dto.ArtDto
import com.sthomas.artsee.domain.model.Art
import com.sthomas.artsee.domain.model.ArtPreview
import com.sthomas.artsee.domain.repository.ArtRepository
import javax.inject.Inject

class ArtRepositoryRemote @Inject constructor(
    private val artRemoteAPI: ArtRemoteAPI
) : ArtRepository {

    override suspend fun getPagedArtPreviews(page: Int, limit: Int): List<ArtPreview> {
        return artRemoteAPI.getArtList(page, limit).artDtoList
            .map {
                it.toArtPreview()
            }
    }

    override suspend fun getArt(id: String): Art {
        TODO("Not yet implemented")
    }

    override suspend fun saveArt(art: Art) = throw IllegalStateException("Not supported")

    override suspend fun getArtPreviews(): List<ArtPreview> = throw IllegalStateException("Not supported")

    private fun ArtDto.toArtPreview() = ArtPreview(
        artId = id,
        thumbnail = "https://www.artic.edu/iiif/2/${id}/full/843,/0/default.jpg"
    )
}