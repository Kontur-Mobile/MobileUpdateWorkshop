package ru.kontur.kickerchamp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import ru.kontur.kickerchamp.MainScreenStore
import ru.kontur.kickerchamp.android.main.MainScreen
import ru.kontur.kickerchamp.android.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AppTheme {
                MainScreen(MainScreenStore()) {}
            }
        }
    }
}