import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.alexgladkov.odyssey.compose.DesktopScreenHost
import ru.alexgladkov.odyssey.compose.extensions.setupWithRootController
import ru.kontur.kickerchamp.db.Database
import ru.kontur.kickerchamp.db.DatabaseDriverFactory
import ru.kontur.kickerchamp.di.sharedModule
import javax.swing.JFrame
import javax.swing.SwingUtilities

fun main() = SwingUtilities.invokeLater {
    val koin = startKoin { modules(sharedModule, desktopModule) }.koin
    val window = JFrame()
    window.title = "Kicker Champ"
    window.setSize(800, 600)

    DesktopScreenHost(window)
        .setupWithRootController(
            startScreen = NavGraphDestinations.Main,
            block = buildComposeNavigationGraph(koin)
        )
}

private val desktopModule = module {
    single { Database(DatabaseDriverFactory()) }
}