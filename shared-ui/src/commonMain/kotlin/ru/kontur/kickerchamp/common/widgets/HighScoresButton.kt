package ru.kontur.kickerchamp.common.widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HighScoresButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        border = BorderStroke(2.dp, MaterialTheme.colors.primary),
    ) {
        Text("High scores", Modifier.padding(vertical = 8.dp))
    }
}