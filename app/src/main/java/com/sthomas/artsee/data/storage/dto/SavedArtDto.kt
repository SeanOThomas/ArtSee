package com.sthomas.artsee.data.storage.dto

import com.sthomas.artsee.domain.model.Art
import kotlinx.serialization.Serializable

@Serializable
data class SavedArtDto(
    val artList: List<Art> = listOf()
)