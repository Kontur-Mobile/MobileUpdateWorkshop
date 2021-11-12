package ru.kontur.kickerchamp

data class MainScreenState(
    val blueDefender: BlueDefender = BlueDefender(""),
    val blueForward: BlueForward = BlueForward(""),
    val redDefender: RedDefender = RedDefender(""),
    val redForward: RedForward = RedForward(""),
    val gameState: GameState = GameState.NonStarted(),
    val dialogSate: DialogState? = null
) {
    val players: List<Player>
        get() = listOf(blueForward, blueDefender, redForward, redDefender)
}

data class DialogState(
    val player: Player
)

sealed class GameState {
    data class NonStarted(
        val isStartButtonEnabled: Boolean = false
    ) : GameState()

    data class Started(
        val blueScore: Int = 0,
        val redScore: Int = 0
    ) : GameState()

    data class Finished(
        val winnerTeam: Team
    ) : GameState()
}