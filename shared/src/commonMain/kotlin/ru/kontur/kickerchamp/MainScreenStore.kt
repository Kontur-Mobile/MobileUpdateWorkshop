package ru.kontur.kickerchamp

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kontur.kickerchamp.db.Database

class MainScreenStore(private val database: Database) {
    private val coroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    fun onIncrementRed() {
        onIncrementScore(RedTeam)
    }

    fun onIncrementBlue() {
        onIncrementScore(BlueTeam)
    }

    fun onRevengeClicked() {
        if (state.value.gameState is GameState.Finished) {
            _state.value = state.value.copy(gameState = GameState.Started())
        }
    }

    fun onRestartGameClicked() {
        if (state.value.gameState is GameState.Finished) {
            _state.value = MainScreenState()
        }
    }

    fun onStartGameClicked() {
        if (state.value.gameState is GameState.NonStarted) {
            _state.value = state.value.copy(gameState = GameState.Started())
        }
    }

    fun onPlayerNameChanged(player: Player, name: String) {
        if (state.value.gameState !is GameState.NonStarted) return
        if (validatePlayerName(name).not()) return

        when (player) {
            is BlueDefender -> _state.value = state.value.copy(blueDefender = BlueDefender(name), dialogState = null)
            is BlueForward -> _state.value = state.value.copy(blueForward = BlueForward(name), dialogState = null)
            is RedDefender -> _state.value = state.value.copy(redDefender = RedDefender(name), dialogState = null)
            is RedForward -> _state.value = state.value.copy(redForward = RedForward(name), dialogState = null)
        }
        checkStartButtonEnabled()
    }

    fun onAddPlayerClicked(player: Player) {
        if (state.value.gameState !is GameState.NonStarted) return

        _state.value = state.value.copy(dialogState = DialogState(player))
    }

    fun onDialogDismiss() {
        _state.value = state.value.copy(dialogState = null)
    }

    private fun onIncrementScore(team: Team) {
        val gameState = state.value.gameState
        if (gameState is GameState.Started) {
            val newGameState = when (team) {
                is BlueTeam -> gameState.copy(blueScore = gameState.blueScore + 1)
                is RedTeam -> gameState.copy(redScore = gameState.redScore + 1)
            }
            _state.value = state.value.copy(gameState = newGameState)
            checkGameEnded(newGameState)
        }
    }

    private fun checkGameEnded(gameState: GameState.Started) {
        val winnerTeam = when {
            gameState.blueScore == WIN_SCORE -> BlueTeam
            gameState.redScore == WIN_SCORE -> RedTeam
            else -> return
        }

        val (winnerPlayers, goalsDiff) = when (winnerTeam) {
            is BlueTeam -> listOf(state.value.blueDefender, state.value.blueForward) to WIN_SCORE - gameState.redScore
            is RedTeam -> listOf(state.value.redDefender, state.value.redForward) to WIN_SCORE - gameState.blueScore
        }

        saveScores(winnerPlayers, goalsDiff)
        _state.value = state.value.copy(
            gameState = GameState.Finished(
                winnerTeam = winnerTeam
            )
        )
    }

    private fun saveScores(
        winnerPlayers: List<Player>,
        goalsDiff: Int
    ) {
        coroutineScope.launch {
            val playerScores = database.getPlayerScores()
            val newPlayerScores = state.value.players.map { player ->
                val score = playerScores.find { it.name == player.name }
                val previousWins = score?.wins ?: 0
                val previousGoalsDiff = score?.goalsDiff ?: 0
                PlayerScore(
                    name = player.name,
                    wins = if (player in winnerPlayers) {
                        previousWins + 1
                    } else {
                        previousWins
                    },
                    goalsDiff = if (player in winnerPlayers) {
                        previousGoalsDiff + goalsDiff
                    } else {
                        previousGoalsDiff - goalsDiff
                    }
                )
            }
            database.savePlayerScores(newPlayerScores)
        }
    }

    private fun validatePlayerName(name: String): Boolean {
        if (name.isBlank()) return false

        return state.value.players.none { it.name.equals(name, ignoreCase = true) }
    }

    private fun checkStartButtonEnabled() {
        val isEnabled = state.value.players.all { it.name.isNotEmpty() }
        _state.value = state.value.copy(gameState = GameState.NonStarted(isEnabled))
    }

    companion object {
        private const val WIN_SCORE = 10
    }
}