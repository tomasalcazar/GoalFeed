package com.example.goalfeed.tabs

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Tabs() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        val tabItems = listOf("Food", "Gas", "Dates")
        var tabIndex by remember { mutableIntStateOf(0) }

        Spacer(Modifier.size(20.dp))
        TabRow(
            selectedTabIndex = tabIndex,
        ) {
            tabItems.forEachIndexed { index, tab ->
                androidx.compose.material3.Tab(
                    selected = index == tabIndex,
                    onClick = {
                        tabIndex = index
                    }
                ) {
                    Text(tab)
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (tabIndex) {
                0 -> Icon(Icons.Filled.ShoppingCart, contentDescription = null)
                1 -> Icon(Icons.Filled.LocationOn, contentDescription = null)
                2 -> Icon(Icons.Filled.Favorite, contentDescription = null)
            }
        }
    }
}

@Preview
@Composable
fun PreviewTabs() {
    Tabs()
}