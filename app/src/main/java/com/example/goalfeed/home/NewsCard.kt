package com.example.goalfeed.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import com.example.goalfeed.ui.theme.*
import androidx.compose.material3.Text
import coil3.compose.AsyncImage

@Composable
fun NewsCard(newsItem: NewsItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = paddingLarge)
            .clickable { onClick() },
        shape = RoundedCornerShape(heightLarge),
        elevation = CardDefaults.cardElevation(elevationLarge),
        colors = CardDefaults.cardColors(containerColor = LightCardBackground)
    ) {
        Column {
            AsyncImage(
                model = newsItem.urlToImage,
                contentDescription = "News image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height180)
                    .clip(RoundedCornerShape(topStart = heightLarge, topEnd = heightLarge))
            )
            Column(modifier = Modifier.padding(paddingLarge)) {
                Text(
                    text = newsItem.title,
                    style = TitleMediumBold,
                    color = CardTextPrimary
                )
                Spacer(modifier = Modifier.height(heightExtraSmall))
                Text(
                    text = newsItem.source.name,
                    style = LabelSmallMedium,
                    color = CardTextBlue
                )
                Spacer(modifier = Modifier.height(heightSmall))
                newsItem.description?.let {
                    Text(
                        text = it,
                        style = BodySmallRegular,
                        color = CardTextSecondary
                    )
                }
            }
        }
    }
}
