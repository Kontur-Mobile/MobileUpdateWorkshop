package ru.kontur.kickerchamp.common.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import ru.kontur.kickerchamp.Player

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