package ru.kontur.kickerchamp.common

import androidx.compose.runtime.Composable

@Composable
internal expect fun Dialog(
    onCloseRequest: () -> Unit,
    content: @Composable () -> Unit
)