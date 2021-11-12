package ru.kontur.kickerchamp.common.highscores

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import ru.kontur.kickerchamp.HighScoreStore
import ru.kontur.kickerchamp.OrderedPlayerScores

@Composable
fun HighScoresScreen(store: HighScoreStore, onBackClick: () -> Unit) {
    val state by store.state.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Default.ArrowBack, contentDescription = null)
                }
                Spacer(Modifier.width(16.dp))
                Text("High Scores", style = MaterialTheme.typography.h6)
            }
        }
    ) {
        LazyColumn(Modifier.fillMaxSize()) {
            item {
                HighScoreTitles()
            }
            items(state.playerScores) { item ->
                HighScoreItem(item = item)
                Divider(Modifier.fillMaxWidth())
            }
        }
    }
}

@Composable
private fun HighScoreTitles() {
    Row(Modifier.padding(16.dp)) {
        Text(
            "№",
            style = MaterialTheme.typography.body1,
            color = LocalContentColor.current.copy(alpha = 0.5f),
            modifier = Modifier.width(40.dp)
        )
        Text(
            "Name",
            style = MaterialTheme.typography.body1,
            color = LocalContentColor.current.copy(alpha = 0.5f),
            modifier = Modifier.weight(1f)
        )
        Text(
            "Wins",
            style = MaterialTheme.typography.body1,
            color = LocalContentColor.current.copy(alpha = 0.5f),
            modifier = Modifier.width(64.dp),
            textAlign = TextAlign.End
        )
        Text(
            "G±",
            style = MaterialTheme.typography.body1,
            color = LocalContentColor.current.copy(alpha = 0.5f),
            modifier = Modifier.width(80.dp),
            textAlign = TextAlign.End
        )
    }
}

@Composable
private fun HighScoreItem(item: OrderedPlayerScores) {
    Row(Modifier.padding(16.dp)) {
        Text(
            "${item.position}.",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.width(40.dp)
        )
        Text(
            item.name,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.weight(1f),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            item.wins.toString(),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.width(64.dp),
            textAlign = TextAlign.End
        )
        Text(
            item.goalsDiff.toString(),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.width(80.dp),
            textAlign = TextAlign.End
        )
    }
}