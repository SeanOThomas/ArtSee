package com.sthomas.artsee.domain


interface ArtRepository {
    suspend fun getArtPreviews(page: Int, limit: Int) : List<ArtPreview>
    suspend fun getArt(id: String) : Art
}