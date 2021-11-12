package ru.kontur.kickerchamp.common.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.kontur.kickerchamp.BlueTeam
import ru.kontur.kickerchamp.RedTeam
import ru.kontur.kickerchamp.Team

@Composable
fun WinnerWidget(
    winnerTeam: Team,
    onRestartGameClicked: () -> Unit,
    onRevengeClicked: () -> Unit,
    onHighScoresClicked: () -> Unit
) {
    val (color, winner) = when (winnerTeam) {
        is BlueTeam -> MaterialTheme.colors.primary to "Blue wins"
        is RedTeam -> MaterialTheme.colors.secondary to "Red wins"
    }

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                winner,
                style = MaterialTheme.typography.h3,
                color = color,
            )
            Spacer(Modifier.height(16.dp))
            Button(
                modifier = Modifier.width(200.dp),
                onClick = onRestartGameClicked
            ) {
                Text("Start new game", Modifier.padding(vertical = 8.dp))
            }
            Spacer(Modifier.height(16.dp))
            Button(
                modifier = Modifier.width(200.dp),
                onClick = onRevengeClicked
            ) {
                Text("Revenge", Modifier.padding(vertical = 8.dp))
            }
            Spacer(Modifier.height(16.dp))
            HighScoresButton(
                modifier = Modifier.width(200.dp),
                onClick = onHighScoresClicked
            )
        }
    }
}