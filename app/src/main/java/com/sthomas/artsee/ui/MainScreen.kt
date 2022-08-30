package com.sthomas.artsee.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import com.sthomas.artsee.R

@Composable
fun MainScreen(navController: NavHostController) {
    val tabs = listOf(stringResource(R.string.explore), stringResource(R.string.saved))
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
        fun onArtPreviewTap(artId: String) {
            navController.navigate("${Routes.artDetail}/$artId")
        }
        if (selectedTabIndex == 0) {
            ExploreList(::onArtPreviewTap)
        } else {
            SavedList(::onArtPreviewTap)
        }
    }
}