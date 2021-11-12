package ru.kontur.kickerchamp.common

import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
internal actual fun IncrementIcon() {
    Icon(
        painterResource(R.drawable.ic_increment),
        contentDescription = null,
        modifier = Modifier.size(64.dp),
        tint = MaterialTheme.colors.onBackground
    )
}

@Composable
internal actual fun DefenderIcon() {
    Icon(
        painterResource(R.drawable.ic_defender),
        contentDescription = null
    )
}

@Composable
internal actual fun ForwardIcon() {
    Icon(
        painterResource(R.drawable.ic_forward),
        contentDescription = null
    )
}