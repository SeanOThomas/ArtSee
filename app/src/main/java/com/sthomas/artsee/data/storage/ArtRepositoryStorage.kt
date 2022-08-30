package com.sthomas.artsee.data.storage

import android.content.Context
import androidx.datastore.dataStore
import com.sthomas.artsee.data.storage.dto.SavedArtDto
import com.sthomas.artsee.domain.model.Art
import com.sthomas.artsee.domain.model.ArtPreview
import com.sthomas.artsee.domain.repository.ArtRepository
import kotlinx.collections.immutable.plus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

private val Context.datastore by dataStore("saved-art.json", SavedArtSerializer)

class ArtRepositoryStorage(
    private val context: Context
) : ArtRepository {

    override fun getArtPreviews(): Flow<List<Art>> {
        return context.datastore.data.map { it.artList }
    }

    override suspend fun getPagedArtPreviews(page: Int, limit: Int): List<ArtPreview> =
        throw IllegalStateException("Not supported")

    override suspend fun getArt(id: String): Art? {
        val savedArtDto = context.datastore.data.first()
        return savedArtDto.artList.firstOrNull {
            it.id == id
        }
    }

    override suspend fun saveArt(art: Art) {
        context.datastore.updateData { savedArtDto ->
            savedArtDto.copy(
                artList = savedArtDto.artList.plus(art)
            )
        }
    }

    override suspend fun unsaveArt(art: Art) {
        context.datastore.updateData { savedArtDto ->
            savedArtDto.copy(
                artList = savedArtDto.artList.filterNot { it.id == art.id }
            )
        }
    }

    private fun SavedArtDto.toArtPreviews() = artList.map {
        ArtPreview(it.id, it.imageUrl)
    }

}