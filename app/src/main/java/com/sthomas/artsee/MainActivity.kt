package com.sthomas.artsee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.sthomas.artsee.domain.model.ArtPreview
import com.sthomas.artsee.presentation.explore.ExploreViewModel
import com.sthomas.artsee.presentation.saved.SavedViewModel
import com.sthomas.artsee.ui.theme.ArtSeeTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSeeTheme {
                val tabs = listOf("Explore", "Saved")
                var selectedTabIndex by remember { mutableStateOf(0) }
                Column {
                    TabRow(selectedTabIndex = selectedTabIndex) {
                        tabs.forEachIndexed { index, text ->
                            Tab(
                                selected = selectedTabIndex == index,
                                onClick = { selectedTabIndex = index },
                                text = { Text(text = text) }
                            )
                        }

                    }
                    if (selectedTabIndex == 0) {
                        val exploreViewModel = hiltViewModel<ExploreViewModel>()
                        val pagingArtPreviews = exploreViewModel.exploreArtPager.collectAsLazyPagingItems()
                        // TODO grid
                        LazyVerticalGrid(
                            columns = GridCells.Adaptive(minSize = 128.dp)
                        ) {
                            items(pagingArtPreviews.itemCount) { index ->
                                pagingArtPreviews[index]?.let {
                                    PreviewCard(it)
                                }
                            }
                        }
                    } else {
                        val savedViewModel = hiltViewModel<SavedViewModel>()
                        val artList = savedViewModel.getSavedArtPreviews().collectAsState(initial = listOf()).value
                        if (artList.isEmpty()) {
                            // TODO show empty list
                        } else {
                            // TODO show list
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun PreviewCard(artPreview: ArtPreview) {
    AsyncImage(
        model = artPreview.thumbnail,
        contentDescription = null
    )
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSeeTheme {
        Greeting("Android")
    }
}