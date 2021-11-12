package ru.kontur.kickerchamp.android

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.kontur.kickerchamp.HighScoreStore
import ru.kontur.kickerchamp.MainScreenStore
import ru.kontur.kickerchamp.android.MainDestinations.MAIN_ROUTE

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
        // TODO: Not implemented
    }
}