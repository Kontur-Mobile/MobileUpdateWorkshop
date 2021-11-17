package ru.kontur.kickerchamp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import ru.kontur.kickerchamp.db.Database

class HighScoreStore(database: Database) {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    val state = database.observePlayerScores()
        .map { createHighScoreState(it) }
        .stateIn(coroutineScope, SharingStarted.Eagerly, HighScoreState())

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