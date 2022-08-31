package com.sthomas.artsee.ui

import android.widget.Space
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.sthomas.artsee.domain.model.Art
import com.sthomas.artsee.presentation.saved.SavedViewModel

@Composable
fun SavedList(
    onArtTap: (String) -> Unit
) {
    val savedViewModel = hiltViewModel<SavedViewModel>()
    val artList = savedViewModel.getSavedArtList().collectAsState(initial = listOf()).value
    if (artList.isEmpty()) {
        EmptyPage()
    } else {
        LazyColumn(
            contentPadding = PaddingValues(10.dp),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.padding)),
            ) {
            items(artList) {
                SavedCard(it, onArtTap)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmptyPage() {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Your saved art lives here.")
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
//            if (art.artistName.isNullOrEmpty().not()) {
//                Text(text = art.artistName!!)
//            }
            if (art.year.isNullOrEmpty().not()) {
                Text(text = art.year!!)
            }
        }
    }
}
