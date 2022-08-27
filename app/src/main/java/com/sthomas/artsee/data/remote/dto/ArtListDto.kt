package com.sthomas.artsee.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArtListDto(
    @SerialName("data")
    val artDtoList: List<ArtDto>
)