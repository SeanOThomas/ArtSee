package com.sthomas.artsee.data.storage

import android.content.Context
import androidx.datastore.dataStore
import com.sthomas.artsee.data.storage.dto.SavedArtDto
import com.sthomas.artsee.domain.model.Art
import com.sthomas.artsee.domain.model.ArtPreview
import com.sthomas.artsee.domain.repository.ArtRepository
import com.sthomas.artsee.domain.repository.Resource
import kotlinx.collections.immutable.plus
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

private val Context.datastore by dataStore("saved-art.json", SavedArtSerializer)

class ArtRepositoryStorage(
    private val context: Context
) : ArtRepository {

    override fun getArtPreviews(): Flow<Resource<List<Art>>> {
        return try {
            context.datastore.data.map {
                Resource.Success(
                    data = it.artList.reversed()
                )
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return flow {
                emit(
                    Resource.Error(e.message ?: "An unknown error occurred.")
                )
            }
        }
    }

    override suspend fun getPagedArtPreviews(page: Int, limit: Int): Resource<List<ArtPreview>> =
        throw IllegalStateException("Not supported")

    override suspend fun getArt(id: String): Resource<Art> {
        return try {
            Resource.Success(
                data = context.datastore.data
                    .first().artList
                    .firstOrNull {
                        it.id == id
                    }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun saveArt(art: Art) : Resource<Boolean> {
        return try {
            context.datastore.updateData { savedArtDto ->
                savedArtDto.copy(
                    artList = savedArtDto.artList.plus(art)
                )
            }
            Resource.Success(data = true)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun unsaveArt(art: Art) : Resource<Boolean> {
        return try {
            context.datastore.updateData { savedArtDto ->
                savedArtDto.copy(
                    artList = savedArtDto.artList.filterNot { it.id == art.id }
                )
            }
            Resource.Success(data = true)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }
}