package com.sthomas.artsee.domain.repository

import com.sthomas.artsee.domain.model.Art
import com.sthomas.artsee.domain.model.ArtPreview
import kotlinx.coroutines.flow.Flow


interface ArtRepository {
    fun getArtPreviews() : Flow<List<ArtPreview>>
    suspend fun getPagedArtPreviews(page: Int, limit: Int) : List<ArtPreview>
    suspend fun getArt(id: String) : Art?
    suspend fun saveArt(art: Art)
    suspend fun unsaveArt(art: Art)
}