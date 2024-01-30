package com.harishdevs.landeed_itest.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun TimerInputComponent(onDismiss: () -> Unit, onStart: (Int, Int, Int) -> Unit) {

    var hours by remember { mutableStateOf(TextFieldValue("00")) }
    var minutes by remember { mutableStateOf(TextFieldValue("00")) }
    var seconds by remember { mutableStateOf(TextFieldValue("00")) }

    Dialog(onDismissRequest = onDismiss) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            ) {
                Text(text = "Enter Time", style = MaterialTheme.typography.titleSmall)

                Row(modifier = Modifier.padding(top = 16.dp)) {
                    TimerInputField(label = "Hours", value = hours, onValueChange = { hours = it })

                    Text(
                        text = ":",
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    TimerInputField(label = "Minutes", value = minutes, onValueChange = { minutes = it })

                    Text(
                        text = ":",
                        style = MaterialTheme.typography.displaySmall,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )

                    TimerInputField(label = "Seconds", value = seconds, onValueChange = { seconds = it })
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp), horizontalArrangement = Arrangement.End
                ) {

                    TextButton(onClick = onDismiss) {
                        Text(text = "CANCEL")
                    }

                    TextButton(onClick = {
                        /* validate if required */
                        onStart(hours.text.toInt(), minutes.text.toInt(), seconds.text.toInt())
                    }) {
                        Text(text = "START")
                    }
                }
            }
        }
    }
}

@Composable
private fun TimerInputField(
    onValueChange: (TextFieldValue) -> Unit,
    value: TextFieldValue,
    label: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentSize()
    ) {
        BasicTextField(
            value = value,
            onValueChange = { onValueChange.invoke(it) },
            textStyle = MaterialTheme.typography.displaySmall,
            singleLine = true,
            maxLines = 1,
            modifier = Modifier
                .width(IntrinsicSize.Min)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = MaterialTheme.shapes.medium
                )
                .padding(horizontal = 8.dp, vertical = 4.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        Text(text = label, style = MaterialTheme.typography.bodyMedium)
    }
}