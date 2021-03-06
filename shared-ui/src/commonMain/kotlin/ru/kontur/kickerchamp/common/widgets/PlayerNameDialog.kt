package ru.kontur.kickerchamp.common.widgets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import ru.kontur.kickerchamp.Player
import ru.kontur.kickerchamp.common.Dialog
import ru.kontur.kickerchamp.common.theme.AppTheme

@Composable
fun PlayerNameDialog(
    player: Player,
    onDone: (Player, newName: String) -> Unit,
    onDismiss: () -> Unit
) {
    var playerName by rememberSaveable { mutableStateOf(player.name) }

    Dialog(onCloseRequest = onDismiss) {
        PlayerNameDialogContent(
            name = playerName,
            onNameChange = { playerName = it },
            onDone = { onDone(player, playerName) }
        )
    }
}

@Composable
private fun PlayerNameDialogContent(
    name: String,
    onNameChange: (String) -> Unit,
    onDone: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        delay(100)
        focusRequester.requestFocus()
    }

    Card(Modifier.size(200.dp)) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Enter name", Modifier.weight(1f), style = MaterialTheme.typography.h6)
                IconButton(onClick = onDone) {
                    Icon(Icons.Default.Done, contentDescription = null)
                }
            }
            Spacer(Modifier.weight(1f))
            OutlinedTextField(
                value = name,
                onValueChange = onNameChange,
                singleLine = true,
                keyboardActions = KeyboardActions {
                    onDone()
                },
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
                modifier = Modifier.focusRequester(focusRequester)
            )
            Spacer(Modifier.weight(1f))
        }
    }
}
