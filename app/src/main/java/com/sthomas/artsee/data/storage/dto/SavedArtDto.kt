package com.sthomas.artsee.data.storage.dto

import com.sthomas.artsee.domain.model.Art
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.serialization.Serializable

@Serializable
data class SavedArtDto(
    val artList: PersistentList<Art> = persistentListOf()
)