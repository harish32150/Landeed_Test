package com.harishdevs.landeed_itest.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun HomeComponent() {

    val titles = listOf("Timer", "World Clock")
    var selectedIdx by remember { mutableIntStateOf(1) }

    Scaffold(
        topBar = {
                 TabRow(selectedTabIndex = selectedIdx) {
                     titles.forEachIndexed { idx, title ->
                         Tab(selected = selectedIdx == idx, onClick = { selectedIdx = idx }) {
                             Text(modifier = Modifier.padding(16.dp), text = title)
                         }
                     }
                 }
        },
        content = { paddingValues ->
            Box(modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(), content = {
                    when(selectedIdx) {
                        0 -> TimerComponent()
                        1 -> WorldClockComponent()
                        else -> throw IllegalStateException("unimplemented index: $selectedIdx")
                    }
            })
        }
    )
}