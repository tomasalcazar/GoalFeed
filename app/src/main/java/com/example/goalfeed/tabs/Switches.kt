package com.example.goalfeed.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Switches() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text("I am a normal switch")
        val switchState = remember { mutableStateOf(false) }
        Switch(
            switchState.value,
            onCheckedChange = {
                switchState.value = !switchState.value
            },
        )
        Spacer(Modifier.size(20.dp))

        Text("I am a disabled switch")
        Switch(
            false,
            onCheckedChange = {},
            enabled = false,
        )
        Spacer(Modifier.size(20.dp))

        Text("I am a switch with thumb content")
        val switchState2 = remember { mutableStateOf(false) }
        Switch(
            switchState2.value,
            onCheckedChange = {
                switchState2.value = !switchState2.value
            },
            thumbContent = {
                if(switchState2.value) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = "",
                    )
                } else {
                    Icon(
                        Icons.Default.Add,
                        contentDescription = "",
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewSwitches() {
    Switches()
}