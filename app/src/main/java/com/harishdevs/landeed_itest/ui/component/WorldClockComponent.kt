package com.harishdevs.landeed_itest.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(backgroundColor = 0xfff, showBackground = true)
fun WorldClockComponent(viewModel: WorldClockViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val selectedTimeZone: WCTimeZone by viewModel.timezone.collectAsState()
    val time: String by viewModel.time.collectAsState("--:--:--")

    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            FilterChip(
                selected = selectedTimeZone == WCTimeZone.IST,
                onClick = { viewModel.timezone.value = WCTimeZone.IST },
                label = { Text("IST") })
            FilterChip(
                selected = selectedTimeZone == WCTimeZone.PST,
                onClick = { viewModel.timezone.value = WCTimeZone.PST },
                label = { Text("PST") },
                modifier = Modifier.padding(start = 16.dp)
            )
        }

        Text(text = time, modifier = Modifier.padding(top = 14.dp))
    }
}