package com.sthomas.artsee.presentation.art_detail

import com.sthomas.artsee.domain.model.Art

data class ArtDetailState(
    val art: Art? = null,
    val isLoading: Boolean = false,
    val isSaving: Boolean = false,
    val isSaved: Boolean = false,
)