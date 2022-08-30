package com.sthomas.artsee.data.remote

import com.sthomas.artsee.data.remote.dto.ArtDto
import com.sthomas.artsee.domain.model.Art
import com.sthomas.artsee.domain.model.ArtPreview
import com.sthomas.artsee.domain.repository.ArtRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtRepositoryRemote @Inject constructor(
    private val artRemoteAPI: ArtRemoteAPI
) : ArtRepository {

    override suspend fun getPagedArtPreviews(page: Int, limit: Int): List<ArtPreview> {
        return artRemoteAPI.getArtList(page, limit).artDtoList
            .filter { it.imageId != null }
            .map { it.toArtPreview() }
    }

    override suspend fun getArt(id: String): Art? {
        return artRemoteAPI.getArt(id).art.toArt()
    }

    override suspend fun saveArt(art: Art) = throw IllegalStateException("Not supported")

    override suspend fun unsaveArt(art: Art) = throw IllegalStateException("Not supported")

    override fun getArtPreviews(): Flow<List<ArtPreview>> = throw IllegalStateException("Not supported")

    private fun ArtDto.toArt() = Art(
        id = id.toString(),
        title = title,
        imageUrl = "https://www.artic.edu/iiif/2/${imageId}/full/843,/0/default.jpg",
        artistName = artist,
        year = yearCreated?.toString()
    )

    private fun ArtDto.toArtPreview() = ArtPreview(
        artId = id.toString(),
        thumbnail = "https://www.artic.edu/iiif/2/${imageId}/full/843,/0/default.jpg"
    )
}