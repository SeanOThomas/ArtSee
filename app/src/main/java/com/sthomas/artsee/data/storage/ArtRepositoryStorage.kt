package com.sthomas.artsee.data.storage

import android.content.Context
import androidx.datastore.dataStore
import com.sthomas.artsee.data.storage.dto.SavedArtDto
import com.sthomas.artsee.domain.model.Art
import com.sthomas.artsee.domain.model.ArtPreview
import com.sthomas.artsee.domain.repository.ArtRepository
import kotlinx.collections.immutable.plus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.datastore by dataStore("saved-art.json", SavedArtSerializer)

class ArtRepositoryStorage(
    private val context: Context
) : ArtRepository {

    override fun getArtPreviews(): Flow<List<ArtPreview>> {
        return context.datastore.data.map { it.toArtPreviews() }
    }

    override suspend fun getPagedArtPreviews(page: Int, limit: Int): List<ArtPreview> {
        TODO("Not yet implemented")
    }

    override suspend fun getArt(id: String): Art {
        TODO("Not yet implemented")
    }

    override suspend fun saveArt(art: Art) {
        context.datastore.updateData {
            it.copy(
                artList = it.artList.plus(art)
            )
        }
    }

    private fun SavedArtDto.toArtPreviews() = artList.map {
        ArtPreview(it.id, it.imageUrl)
    }

}