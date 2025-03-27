package com.example.goalfeed.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.InputChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Chips() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        InputChip(
            selected = true,
            onClick = {},
            label = {
                Text("I am an input chip")
            }
        )

        InputChip(
            selected = true,
            onClick = {},
            label = {
                Text("I am a disabled input chip")
            },
            enabled = false,
        )

        InputChip(
            selected = true,
            onClick = {},
            label = {
                Text("I am an input chip with a leading and trailing icon")
            },
            leadingIcon = {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = null,
                )
            },
            trailingIcon = {
                Icon(
                    Icons.Default.Check,
                    contentDescription = null,
                )
            },
        )

        val filteredState = remember { mutableStateOf(true) }
        FilterChip(
            selected = filteredState.value,
            onClick = {
                filteredState.value = !filteredState.value
            },
            label = {
                Text("I am a filter chip")
            },
        )

        val filteredState2 = remember { mutableStateOf(true) }
        FilterChip(
            selected = filteredState2.value,
            onClick = {
                filteredState2.value = !filteredState2.value
            },
            label = {
                Text("I am a filter chip that if selected I show a leading icon else i show a trailing icon")
            },
            leadingIcon = {
                if(filteredState2.value) {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null,
                    )
                }
            },
            trailingIcon = {
                if(!filteredState2.value) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun PreviewChips() {
    Chips()
}