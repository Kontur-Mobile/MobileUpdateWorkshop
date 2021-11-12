package ru.kontur.kickerchamp.android.main.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.kontur.kickerchamp.android.R
import ru.kontur.kickerchamp.android.theme.AppTheme

@Composable
fun ScoreWidget(
    blueScore: Int,
    redScore: Int,
    onIncrementBlue: () -> Unit,
    onIncrementRed: () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        Row(
            Modifier.align(Alignment.Center),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(Modifier.weight(1f))
            IncrementIcon()
            Spacer(Modifier.weight(1f))
            ScoreCard(
                shape = CutCornerShape(topStart = 64.dp, bottomStart = 64.dp),
                score = blueScore,
                color = MaterialTheme.colors.primary
            )
            ScoreCard(
                shape = CutCornerShape(topEnd = 64.dp, bottomEnd = 64.dp),
                score = redScore,
                color = MaterialTheme.colors.secondary
            )
            Spacer(Modifier.weight(1f))
            IncrementIcon()
            Spacer(Modifier.weight(1f))
        }
        Row {
            Box(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable { onIncrementBlue() }
            )
            Box(
                Modifier
                    .fillMaxHeight()
                    .weight(1f)
                    .clickable { onIncrementRed() }
            )
        }
    }
}

@Composable
private fun IncrementIcon() {
    Icon(
        painterResource(R.drawable.ic_increment),
        contentDescription = null,
        tint = MaterialTheme.colors.onBackground,
        modifier = Modifier.size(64.dp)
    )
}

@Composable
private fun ScoreCard(
    shape: CutCornerShape,
    score: Int,
    color: Color
) {
    Card(
        shape = shape,
        backgroundColor = color
    ) {
        Box(
            Modifier
                .width(80.dp)
                .height(200.dp)
        ) {
            Text(
                score.toString(),
                style = MaterialTheme.typography.h3,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun ScoreWidgetPreview() {
    AppTheme {
        ScoreWidget(0, 0, {}, {})
    }
}