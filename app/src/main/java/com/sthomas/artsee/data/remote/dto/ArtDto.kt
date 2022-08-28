package com.sthomas.artsee.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtDto(
    val id: Int,
    val title: String,
    @SerialName("image_id")
    val imageId: String?,
    @SerialName("artist_title")
    val artist: String?,
    @SerialName("style_title")
    val style: String?,
    @SerialName("date_end")
    val yearCreated: Int?,
    @SerialName("is_on_view")
    val isOnView: Boolean
)