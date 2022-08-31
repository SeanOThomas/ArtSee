package com.sthomas.artsee.domain.repository

import com.sthomas.artsee.domain.model.Art
import com.sthomas.artsee.domain.model.ArtPreview
import kotlinx.coroutines.flow.Flow


interface ArtRepository {
    fun getArtPreviews() : Flow<Resource<List<Art>>>
    suspend fun getPagedArtPreviews(page: Int, limit: Int) : Resource<List<ArtPreview>>
    suspend fun getArt(id: String) : Resource<Art>
    suspend fun saveArt(art: Art) : Resource<Boolean>
    suspend fun unsaveArt(art: Art) : Resource<Boolean>
}