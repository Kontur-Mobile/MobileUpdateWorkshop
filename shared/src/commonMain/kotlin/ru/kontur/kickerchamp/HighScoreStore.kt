package ru.kontur.kickerchamp

import kotlinx.coroutines.flow.MutableStateFlow

class HighScoreStore {
    // TODO: Not implemented
    val state = MutableStateFlow(HighScoreState())

    private fun createHighScoreState(playerScores: List<PlayerScore>): HighScoreState {
        val orderedPlayerScores = playerScores.mapIndexed { index, playerScore ->
            OrderedPlayerScores(
                position = index + 1,
                name = playerScore.name,
                wins = playerScore.wins,
                goalsDiff = playerScore.goalsDiff
            )
        }
        return HighScoreState(orderedPlayerScores)
    }
}