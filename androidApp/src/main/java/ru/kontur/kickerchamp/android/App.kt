package ru.kontur.kickerchamp.android

import android.app.Application
import ru.kontur.kickerchamp.HighScoreStore
import ru.kontur.kickerchamp.MainScreenStore
import ru.kontur.kickerchamp.db.Database
import ru.kontur.kickerchamp.db.DatabaseDriverFactory

class App : Application() {
    val mainScreenStore by lazy {
        MainScreenStore(database)
    }

    val highScoreStore by lazy {
        HighScoreStore(database)
    }

    private val database by lazy {
        Database(DatabaseDriverFactory(this))
    }
}