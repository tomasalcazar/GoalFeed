package com.example.goalfeed.tabs

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goalfeed.tabs.data.ListItemCategory
import com.example.goalfeed.tabs.data.items

@Composable
fun Basic(
    onClick: (String) -> Unit,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        item {
            Text(
                "Basic Components",
                fontSize = 34.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(vertical = 20.dp)
            )
        }
        items(items.filter { it.category == ListItemCategory.Basic }) { tab ->
            ListItem(
                listItem = tab,
            ) {
                onClick(tab.title)
            }
        }
    }
}

@Preview
@Composable
fun PreviewBasic() {
    Basic {}
}