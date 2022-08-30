package com.sthomas.artsee.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
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
        val scrollState = rememberScrollState()
        with(state.art) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .verticalScroll(scrollState)
            ) {
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                )
                Spacer(Modifier.height(dimensionResource(id = R.dimen.padding)))
                Text(
                    modifier = Modifier.padding(
                        horizontal = dimensionResource(id = R.dimen.padding),
                    ),
                    text = title,
                    style = MaterialTheme.typography.h3,
                )
                if (artistName != null) {
                    Spacer(Modifier.height(dimensionResource(id = R.dimen.padding)))
                    Text(
                        modifier = Modifier.padding(
                            horizontal = dimensionResource(id = R.dimen.padding),
                        ),
                        text = "by $artistName"
                    )
                }
                if (year != null) {
                    Spacer(Modifier.height(dimensionResource(id = R.dimen.padding)))
                    Text(
                        modifier = Modifier.padding(
                            horizontal = dimensionResource(id = R.dimen.padding),
                        ),
                        text = "circa $year"
                    )
                }
                Spacer(Modifier.height(dimensionResource(id = R.dimen.padding)))
                val buttonText = stringResource(if (state.isSaved) R.string.unsave else R.string.save)
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .padding(
                            horizontal = dimensionResource(id = R.dimen.padding)
                        ),
                    onClick = {
                    artDetailViewModel.saveArt()
                }) {
                    Text(text = buttonText)
                }
                Spacer(Modifier.height(dimensionResource(id = R.dimen.padding)))
            }
        }
    }

}



