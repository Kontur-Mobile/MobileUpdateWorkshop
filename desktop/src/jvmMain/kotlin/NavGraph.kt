import org.koin.core.Koin
import ru.alexgladkov.odyssey.compose.extensions.screen
import ru.alexgladkov.odyssey.compose.navigation.RootComposeBuilder
import ru.kontur.kickerchamp.common.highscores.HighScoresScreen
import ru.kontur.kickerchamp.common.main.MainScreen
import ru.kontur.kickerchamp.common.theme.AppTheme

object NavGraphDestinations {
    const val Main = "main"
    const val HighScores = "high-scores"
}
fun buildComposeNavigationGraph(koin: Koin): RootComposeBuilder.() -> Unit {
    return { generateGraph(koin) }
}

fun RootComposeBuilder.generateGraph(koin: Koin) {
    screen(name = NavGraphDestinations.Main) {
        AppTheme {
            MainScreen(store = koin.get(), onHighScoresClick = {
                rootController.launch(NavGraphDestinations.HighScores)
            })
        }
    }
    screen(name = NavGraphDestinations.HighScores) {
        AppTheme {
            HighScoresScreen(store = koin.get(), onBackClick = {
                rootController.popBackStack()
            })
        }
    }
}