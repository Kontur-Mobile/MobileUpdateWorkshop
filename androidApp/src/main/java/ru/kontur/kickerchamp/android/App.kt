package ru.kontur.kickerchamp.android

import android.app.Application
import org.koin.core.context.startKoin
import org.koin.dsl.module
import ru.kontur.kickerchamp.db.DatabaseDriverFactory
import ru.kontur.kickerchamp.di.sharedModule

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(platformModule, sharedModule)
        }
    }

    private val platformModule = module {
        single { DatabaseDriverFactory(this@App) }
    }
}