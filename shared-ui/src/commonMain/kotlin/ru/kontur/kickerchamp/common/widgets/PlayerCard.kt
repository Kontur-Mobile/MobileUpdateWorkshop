package ru.kontur.kickerchamp.common.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.kontur.kickerchamp.BlueDefender
import ru.kontur.kickerchamp.BlueForward
import ru.kontur.kickerchamp.BlueTeam
import ru.kontur.kickerchamp.Defender
import ru.kontur.kickerchamp.Forward
import ru.kontur.kickerchamp.Player
import ru.kontur.kickerchamp.RedDefender
import ru.kontur.kickerchamp.RedForward
import ru.kontur.kickerchamp.RedTeam
import ru.kontur.kickerchamp.Team
import ru.kontur.kickerchamp.common.DefenderIcon
import ru.kontur.kickerchamp.common.ForwardIcon

@Composable
fun PlayerCard(
    player: Player,
    onAddOrEditPlayer: (Player) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = when (player) {
        is BlueDefender -> RoundedCornerShape(topEnd = 100.dp)
        is BlueForward -> RoundedCornerShape(bottomEnd = 100.dp)
        is RedDefender -> RoundedCornerShape(topStart = 100.dp)
        is RedForward -> RoundedCornerShape(bottomStart = 100.dp)
    }

    Card(
        modifier = Modifier.size(150.dp).then(modifier),
        shape = shape,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(8.dp)
        ) {
            if (player is Forward) {
                PlayerTitle("Forward", icon = { ForwardIcon() })
            }
            Spacer(Modifier.weight(1f))
            AddOrEditPlayerButton(player, onAddOrEditPlayer)
            Spacer(Modifier.weight(1f))
            if (player is Defender) {
                PlayerTitle("Defender", icon = { DefenderIcon() })
            }
        }
    }
}

@Composable
private fun PlayerTitle(
    title: String,
    icon: @Composable () -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(title, style = MaterialTheme.typography.h6)
        Spacer(Modifier.width(8.dp))
        icon()
    }
}

@Composable
private fun AddOrEditPlayerButton(
    player: Player,
    onClick: (Player) -> Unit
) {
    val color = when (player as Team) {
        is BlueTeam -> MaterialTheme.colors.primary
        is RedTeam -> MaterialTheme.colors.secondary
    }

    if (player.name.isNotEmpty()) {
        Text(
            player.name,
            style = MaterialTheme.typography.h5,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = color,
            modifier = Modifier.clickable { onClick(player) }
        )
    } else {
        Button(
            onClick = { onClick(player) },
            shape = CircleShape,
            modifier = Modifier.size(64.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = color)
        ) {
            Icon(Icons.Default.Add, contentDescription = null)
        }
    }
}