package com.sthomas.artsee.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtResponseDto(
    @SerialName("data")
    val art: ArtDto
)