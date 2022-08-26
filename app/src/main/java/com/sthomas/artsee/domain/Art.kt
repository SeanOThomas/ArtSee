package com.sthomas.artsee.domain

data class Art(
    val id: String,
    val title: String,
    val imageUrl: String,
    val artistName: String,
    val description: String,
    val year: String
)