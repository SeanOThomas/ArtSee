package com.sthomas.artsee.domain

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow


interface ArtRepository {
    fun getPagingArt() : Flow<PagingData<ArtPreview>>
    fun getArt(id: String) : Art
}