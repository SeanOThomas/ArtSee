package com.sthomas.artsee.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.sthomas.artsee.domain.model.ArtPreview
import com.sthomas.artsee.presentation.explore.ExploreViewModel

@Composable
fun ExploreList(
    onArtPreviewTap: (String) -> Unit
) {
    val exploreViewModel = hiltViewModel<ExploreViewModel>()
    val pagingArtPreviews = exploreViewModel.exploreArtPager.collectAsLazyPagingItems()
    println(pagingArtPreviews.itemCount)
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 145.dp),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(pagingArtPreviews.itemCount) { index ->
            pagingArtPreviews[index]?.let {
                PreviewCard(it, onArtPreviewTap)
            }
        }
        if (pagingArtPreviews.loadState.append is LoadState.Loading) {
            item {
                LoadingSpinner()
            }
        }
    }
}

@Composable
fun PreviewCard(artPreview: ArtPreview, onArtPreviewTap: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onArtPreviewTap(artPreview.artId)
            }
    ) {
        AsyncImage(
            model = artPreview.thumbnail,
            contentDescription = null,
        )
    }
}

@Composable
fun LoadingSpinner() {
    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        CircularProgressIndicator(
            Modifier
                .width(42.dp)
                .height(42.dp)
                .padding(8.dp),
            strokeWidth = 5.dp
        )
    }
}