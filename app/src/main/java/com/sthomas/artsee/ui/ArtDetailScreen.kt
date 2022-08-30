package com.sthomas.artsee.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sthomas.artsee.R
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
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                Box(modifier = Modifier.fillMaxWidth()) {
//                    AsyncImage(
//                        model = imageUrl,
//                        contentDescription = null
//                    )
//                }
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null
                )
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
                val buttonText =  stringResource(if (state.isSaved) R.string.unsave else R.string.save)
                Button(onClick = {
                    artDetailViewModel.saveArt()
                }) {
                    Text(text = buttonText)
                }
            }
        }
    }

}



