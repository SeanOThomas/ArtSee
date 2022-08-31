package com.sthomas.artsee.repostiory

import com.sthomas.artsee.domain.model.Art
import com.sthomas.artsee.domain.model.ArtPreview
import com.sthomas.artsee.domain.repository.ArtRepository
import com.sthomas.artsee.domain.repository.Resource
import kotlinx.coroutines.flow.Flow


class FakeRepository(
) : ArtRepository {

    val artList = mutableListOf<Art>()
    var isError: Boolean = false

    override suspend fun getArt(id: String): Resource<Art> {
        return if (isError) {
            Resource.Error(message = "NA")
        } else {
            Resource.Success(data = artList.find { it.id == id })
        }
    }

    override suspend fun saveArt(art: Art): Resource<Boolean> {
        return if (isError) {
            Resource.Error(message = "NA")
        } else {
            artList.add(art)
            Resource.Success(data = true)
        }
    }

    override suspend fun unsaveArt(art: Art): Resource<Boolean> {
        return if (isError) {
            Resource.Error(message = "NA")
        } else {
            artList.remove(art)
            Resource.Success(data = true)
        }
    }

    override fun getArtPreviews(): Flow<Resource<List<Art>>> {
        throw IllegalStateException()
    }

    override suspend fun getPagedArtPreviews(page: Int, limit: Int): Resource<List<ArtPreview>> {
        throw IllegalStateException()
    }

}