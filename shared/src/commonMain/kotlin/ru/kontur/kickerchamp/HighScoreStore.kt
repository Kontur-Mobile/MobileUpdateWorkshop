package ru.kontur.kickerchamp

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kontur.kickerchamp.db.Database

class HighScoreStore(private val database: Database) {
    val state = MutableStateFlow(HighScoreState()).asStateFlow()

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