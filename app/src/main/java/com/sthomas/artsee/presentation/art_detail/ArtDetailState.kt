package com.sthomas.artsee.presentation.art_detail

import com.sthomas.artsee.domain.model.Art

data class ArtDetailState(
    val art: Art?,
    val isLoading: Boolean,
    val isSaving: Boolean,
    val isSaved: Boolean,
)