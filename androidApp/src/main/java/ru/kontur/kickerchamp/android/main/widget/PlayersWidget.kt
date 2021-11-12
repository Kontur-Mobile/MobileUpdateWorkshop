package ru.kontur.kickerchamp.android.main.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.kontur.kickerchamp.BlueDefender
import ru.kontur.kickerchamp.BlueForward
import ru.kontur.kickerchamp.Player
import ru.kontur.kickerchamp.RedDefender
import ru.kontur.kickerchamp.RedForward
import ru.kontur.kickerchamp.android.theme.AppTheme

@Composable
fun PlayersWidget(
    players: List<Player>,
    onAddOrEditPlayer: (Player) -> Unit
) {
    val alignments = listOf(
        Alignment.TopStart,
        Alignment.BottomStart,
        Alignment.TopEnd,
        Alignment.BottomEnd,
    )

    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        players.forEachIndexed { index, player ->
            PlayerCard(player, onAddOrEditPlayer, Modifier.align(alignments[index]))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayersWidgetPreview() {
    AppTheme {
        PlayersWidget(
            listOf(BlueForward(), BlueDefender(), RedForward(), RedDefender()),
            onAddOrEditPlayer = {}
        )
    }
}