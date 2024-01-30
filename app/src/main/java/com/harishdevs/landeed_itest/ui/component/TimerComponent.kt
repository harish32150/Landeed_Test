package com.harishdevs.landeed_itest.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview
fun TimerComponent(viewModel: TimerViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    var addTimerInputState by remember { mutableStateOf(false) }
    val timerListState: List<TimerInfo> by viewModel.timerListState.collectAsState()

    if (addTimerInputState) {
        TimerInputComponent(
            onDismiss = { addTimerInputState = false },
            onStart = { hours, minutes, seconds ->
                viewModel.addTimer(hours, minutes, seconds)
                addTimerInputState = false
            })
    }

    Box(modifier = Modifier.fillMaxSize()) {

        LazyColumn(modifier = Modifier.padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            items(items = timerListState, key = { item: TimerInfo -> item.id }) {
                TimerItemComponent(it, viewModel::removeTimer)
            }
            if (timerListState.isEmpty()) {
                item {
                    Text(text = "No Timers added, start by adding one.", style = MaterialTheme.typography.bodyLarge, modifier = Modifier.fillMaxWidth().padding(top = 64.dp), textAlign = TextAlign.Center)
                }
            }
        }

        FloatingActionButton(
            onClick = { addTimerInputState = true },
            Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 24.dp, bottom = 48.dp)
        ) {

            Icon(imageVector = Icons.Outlined.Add, contentDescription = null)
        }
    }
}

@Composable
private fun TimerItemComponent(timerInfo: TimerInfo = TimerInfo(0, 2, 30), onRemove: (TimerInfo) -> Unit) {
    val ticker: Long by timerInfo.timerFlow.collectAsState(initial = 0)

    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.medium
            )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            when {
                ticker == 0L -> "Timed Out"
                else -> {
                    val seconds = ticker / 1000
                    val hours = seconds / 3600
                    val minutes = (seconds % 3600) / 60
                    val remainingSeconds = seconds % 60

                    String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds)
                }
            }.let {
                Text(text = it, style = MaterialTheme.typography.displaySmall)
            }

            IconButton(
                onClick = { onRemove(timerInfo) },
                modifier = Modifier.background(
                    color = MaterialTheme.colorScheme.error.copy(alpha = 0.1f),
                    shape = CircleShape
                )
            ) {
                Icon(
                    imageVector = Icons.Outlined.Delete,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}