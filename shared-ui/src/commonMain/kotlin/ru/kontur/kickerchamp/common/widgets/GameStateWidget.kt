package ru.kontur.kickerchamp.common.widgets

import androidx.compose.runtime.Composable
import ru.kontur.kickerchamp.GameState
import ru.kontur.kickerchamp.MainScreenStore

@Composable
fun GameStateWidget(
    gameState: GameState,
    store: MainScreenStore,
    onHighScoresClick: () -> Unit
) {
    when (gameState) {
        is GameState.Started -> {
            ScoreWidget(
                blueScore = gameState.blueScore,
                redScore = gameState.redScore,
                onIncrementBlue = store::onIncrementBlue,
                onIncrementRed = store::onIncrementRed
            )
        }
        is GameState.NonStarted -> {
            StartGameWidget(
                isStartButtonEnabled = gameState.isStartButtonEnabled,
                onHighScoresClicked = onHighScoresClick,
                onStartGameClicked = store::onStartGameClicked
            )
        }
        is GameState.Finished -> {
            WinnerWidget(
                winnerTeam = gameState.winnerTeam,
                onRestartGameClicked = store::onRestartGameClicked,
                onRevengeClicked = store::onRevengeClicked,
                onHighScoresClicked = onHighScoresClick
            )
        }
    }
}