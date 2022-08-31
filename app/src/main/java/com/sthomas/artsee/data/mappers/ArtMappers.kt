package com.sthomas.artsee.data.mappers

import com.sthomas.artsee.data.remote.dto.ArtDto
import com.sthomas.artsee.data.storage.dto.SavedArtDto
import com.sthomas.artsee.domain.model.Art
import com.sthomas.artsee.domain.model.ArtPreview

fun ArtDto.toArt() = Art(
    id = id.toString(),
    title = title,
    imageUrl = "https://www.artic.edu/iiif/2/${imageId}/full/1650,/0/default.jpg",
    artistName = artist,
    year = yearCreated?.toString()
)

fun ArtDto.toArtPreview() = ArtPreview(
    artId = id.toString(),
    thumbnail = "https://www.artic.edu/iiif/2/${imageId}/full/550,/0/default.jpg"
)