package com.sthomas.artsee.data.storage

import android.content.Context
import androidx.datastore.dataStore
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

    override suspend fun getArtPreviews(): Flow<List<ArtPreview>> {
        // TODO clean up
        return context.datastore.data.map { it.artList.map { ArtPreview("","") } }
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
}