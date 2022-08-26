package com.sthomas.artsee.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtworkResponse(
    @SerialName("data")
    val artwork: List<Artwork>
)