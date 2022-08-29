package com.sthomas.artsee.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sthomas.artsee.presentation.art_detail.ArtDetailViewModel

@Composable
fun ArtDetailScreen(
    artDetailViewModel: ArtDetailViewModel = hiltViewModel()
) {
    val state = artDetailViewModel.state
    if (state.isLoading) {
        CircularProgressIndicator()
    } else {
        requireNotNull(state.art)
        with(state.art) {
            Column {
                Box(modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(
                        model = imageUrl,
                        contentDescription = null
                    )
                }
                Spacer(Modifier.height(10.dp))
                Text(text = title)
                if (artistName != null) {
                    Spacer(Modifier.height(10.dp))
                    Text(text = artistName)
                }
                if (year != null) {
                    Spacer(Modifier.height(10.dp))
                    Text(text = year)
                }
            }
        }
    }

}



