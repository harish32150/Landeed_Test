package com.harishdevs.landeed_itest.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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