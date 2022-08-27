package com.sthomas.artsee

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.sthomas.artsee.ui.theme.ArtSeeTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.runtime.setValue
import com.sthomas.artsee.data.storage.datasctore

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSeeTheme {
                val tabs = listOf("On View", "Saved")
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
//                    if (selectedTabIndex == 0) {
//                        val viewModel1 = hiltViewModel<HomeViewModel>()
//                    } else {
//                        val viewModel2 = hiltViewModel<HomeViewModel>()
//                    }
                }
            }
        }
    }
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