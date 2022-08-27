package com.sthomas.artsee.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Art(
    val id: String,
    val title: String,
    val imageUrl: String,
    val artistName: String,
    val year: String
)