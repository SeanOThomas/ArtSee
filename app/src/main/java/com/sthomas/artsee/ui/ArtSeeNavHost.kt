package com.sthomas.artsee.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

object Routes {
    const val main = "main"
    const val artDetail = "art_detail"
}

object Args {
    const val artId = "artId"
}

@Composable
fun ArtSeeNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = Routes.main
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Routes.main) { MainScreen(navController) }
        composable(
            "${Routes.artDetail}/{${Args.artId}}",
            arguments = listOf(navArgument(Args.artId) { type = NavType.StringType })
        ) {
            ArtDetailScreen()
        }
    }
}