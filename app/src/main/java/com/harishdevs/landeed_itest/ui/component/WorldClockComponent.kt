package com.harishdevs.landeed_itest.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(backgroundColor = 0xfff, showBackground = true)
fun WorldClockComponent() {

    Column {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
            FilterChip(selected = true, onClick = { /*TODO*/ }, label = { Text("IST") })
            FilterChip(selected = false, onClick = { /*TODO*/ }, label = { Text("PST") }, modifier = Modifier.padding(start = 16.dp))
        }
    }
}