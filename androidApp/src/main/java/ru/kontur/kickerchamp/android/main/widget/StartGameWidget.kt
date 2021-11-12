package ru.kontur.kickerchamp.android.main.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kontur.kickerchamp.android.theme.AppTheme

@Composable
fun StartGameWidget(
    isStartButtonEnabled: Boolean,
    onStartGameClicked: () -> Unit,
    onHighScoresClicked: () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                modifier = Modifier.width(200.dp),
                onClick = onStartGameClicked,
                enabled = isStartButtonEnabled
            ) {
                Text("Start game", Modifier.padding(vertical = 8.dp))
            }
            Spacer(Modifier.height(16.dp))
            HighScoresButton(
                modifier = Modifier.width(200.dp),
                onClick = onHighScoresClicked
            )
        }
    }
}

@Preview
@Composable
fun StartGameWidgetPreview() {
    AppTheme {
        StartGameWidget(true, {}) {}
    }
}