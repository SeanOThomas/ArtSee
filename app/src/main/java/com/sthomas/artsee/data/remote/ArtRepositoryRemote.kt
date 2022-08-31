package com.sthomas.artsee.data.remote

import com.sthomas.artsee.data.mappers.toArt
import com.sthomas.artsee.data.mappers.toArtPreview
import com.sthomas.artsee.domain.model.Art
import com.sthomas.artsee.domain.model.ArtPreview
import com.sthomas.artsee.domain.repository.ArtRepository
import com.sthomas.artsee.domain.repository.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ArtRepositoryRemote @Inject constructor(
    private val artRemoteAPI: ArtRemoteAPI
) : ArtRepository {

    override suspend fun getPagedArtPreviews(page: Int, limit: Int): Resource<List<ArtPreview>> {
        return try {
            Resource.Success(
                data = artRemoteAPI.getArtList(page, limit).artDtoList
                    .filter { it.imageId != null }
                    .map { it.toArtPreview() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getArt(id: String): Resource<Art> {
        return try {
            Resource.Success(
                data = artRemoteAPI.getArt(id).art.toArt()
            )
        }
        catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun saveArt(art: Art) = throw IllegalStateException("Not supported")

    override suspend fun unsaveArt(art: Art) = throw IllegalStateException("Not supported")

    override fun getArtPreviews(): Flow<Resource<List<Art>>> = throw IllegalStateException("Not supported")
}