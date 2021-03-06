package ru.kontur.kickerchamp.common.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import ru.kontur.kickerchamp.MainScreenStore
import ru.kontur.kickerchamp.common.widgets.GameStateWidget
import ru.kontur.kickerchamp.common.widgets.PlayerNameDialog
import ru.kontur.kickerchamp.common.widgets.PlayersWidget

@Composable
fun MainScreen(
    store: MainScreenStore,
    onHighScoresClick: () -> Unit
) {
    val state by store.state.collectAsState()

    state.dialogState?.let {
        PlayerNameDialog(
            player = it.player,
            onDone = store::onPlayerNameChanged,
            onDismiss = store::onDialogDismiss
        )
    }

    PlayersWidget(
        state.players,
        onAddOrEditPlayer = store::onAddPlayerClicked
    )

    GameStateWidget(state.gameState, store, onHighScoresClick)
}