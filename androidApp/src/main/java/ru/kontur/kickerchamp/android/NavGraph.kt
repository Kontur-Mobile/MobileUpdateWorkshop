package ru.kontur.kickerchamp.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.kontur.kickerchamp.HighScoreStore
import ru.kontur.kickerchamp.MainScreenStore
import ru.kontur.kickerchamp.android.MainDestinations.MAIN_ROUTE
import ru.kontur.kickerchamp.android.highscores.HighScoresScreen
import ru.kontur.kickerchamp.android.main.MainScreen

object MainDestinations {
    const val MAIN_ROUTE = "main"
    const val HIGH_SCORES_ROUTE = "high-scores"
}

@Composable
fun NavGraph(
    mainScreenStore: MainScreenStore,
    highScoreStore: HighScoreStore,
    startDestination: String = MAIN_ROUTE
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(MAIN_ROUTE) {
            MainScreen(mainScreenStore, onHighScoresClick = {
                navController.navigate(MainDestinations.HIGH_SCORES_ROUTE)
            })
        }
        composable(MainDestinations.HIGH_SCORES_ROUTE) {
            HighScoresScreen(highScoreStore, onBackClick = {
                navController.popBackStack()
            })
        }
    }
}