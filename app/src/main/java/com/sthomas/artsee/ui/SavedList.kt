package com.sthomas.artsee.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.sthomas.artsee.presentation.saved.SavedViewModel

@Composable
fun SavedList(
    onArtPreviewTap: (String) -> Unit
) {
    val savedViewModel = hiltViewModel<SavedViewModel>()
    val artList = savedViewModel.getSavedArtPreviews()
        .collectAsState(initial = listOf()).value
    if (artList.isEmpty()) {
        // TODO show empty list
    } else {
        // TODO show list
    }
}
