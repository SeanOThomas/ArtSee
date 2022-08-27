package com.sthomas.artsee.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtDto(
    val id: String,
    val title: String,
    @SerialName("artist_title")
    val artist: String,
    @SerialName("style_title")
    val style: String,
    @SerialName("date_end")
    val yearCreated: String,
    @SerialName("is_on_view")
    val isOnView: Boolean
)