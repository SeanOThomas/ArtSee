package com.sthomas.artsee.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.sthomas.artsee.R
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sthomas.artsee.domain.model.Art
import com.sthomas.artsee.domain.repository.Resource
import com.sthomas.artsee.presentation.saved.SavedViewModel

@Composable
fun SavedList(
    onArtTap: (String) -> Unit
) {
    val savedViewModel = hiltViewModel<SavedViewModel>()
    val resource = savedViewModel.getSavedArtList().collectAsState(initial = Resource.Success(data = listOf())).value
    if (resource is Resource.Success) {
        val artList: List<Art> = checkNotNull(resource.data)
        if (artList.isEmpty()) {
            EmptyState()
        } else {
            LazyColumn(
                contentPadding = PaddingValues(10.dp),
                verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding)),
            ) {
                items(resource.data) {
                    SavedCard(it, onArtTap)
                }
            }
        }
    } else {
        // TODO show error state
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyState() {
    Column (
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(R.string.saved_art_empty_message))
    }
}

@Composable
fun SavedCard(
    art: Art,
    onArtTap: (String) -> Unit
) {
    Row(Modifier.clickable {
        onArtTap(art.id)
    }) {
        Box(
            Modifier
                .width(200.dp)
        ) {
            AsyncImage(model = art.imageUrl, contentDescription = null)
        }
        Spacer(
            Modifier.width(10.dp)
        )
        Column {
            Text(text = art.title)
            if (art.year.isNullOrEmpty().not()) {
                Text(text = art.year!!)
            }
        }
    }
}
