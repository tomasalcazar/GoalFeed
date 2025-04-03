package com.example.goalfeed.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.goalfeed.R
import com.example.goalfeed.ui.theme.*
import androidx.compose.material3.Text

@Composable
fun NewsCard(newsItem: NewsItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(16.dp),
        colors = CardDefaults.cardColors(containerColor = LightCardBackground)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.juanfer),
                contentDescription = "News image",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = newsItem.title,
                    style = TitleMediumBold,
                    color = CardTextPrimary
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = newsItem.source,
                    style = LabelSmallMedium,
                    color = BlueUpcoming
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = newsItem.description,
                    style = BodySmallRegular,
                    color = CardTextSecondary
                )
            }
        }
    }
}
