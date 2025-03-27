package com.example.goalfeed.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Checkboxes() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        val checkboxState = remember { mutableStateOf(false) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checkboxState.value,
                onCheckedChange = { checkboxState.value = !checkboxState.value },
            )
            Text("I am a normal checkbox")
        }
        Spacer(Modifier.size(20.dp))

        val checkboxState2 = remember { mutableStateOf(false) }
        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checkboxState2.value,
                onCheckedChange = { checkboxState2.value = !checkboxState2.value },
                enabled = false,
            )
            Text("I am a disabled checkbox")
        }
    }
}

@Preview
@Composable
fun PreviewCheckboxes() {
    Checkboxes()
}