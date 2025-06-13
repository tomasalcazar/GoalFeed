package com.example.goalfeed.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import coil3.compose.AsyncImage
import com.example.goalfeed.ui.theme.*

@Composable
fun NewsCard(newsItem: NewsItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = paddingLarge)
            .clickable { onClick() },
        shape   = RoundedCornerShape(heightLarge),
        elevation = CardDefaults.cardElevation(elevationLarge),
        colors  = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            AsyncImage(
                model           = newsItem.urlToImage,
                contentDescription = "News image",
                contentScale    = ContentScale.Crop,
                modifier        = Modifier
                    .fillMaxWidth()
                    .height(height180)
                    .clip(RoundedCornerShape(topStart = heightLarge, topEnd = heightLarge))
            )
            Column(modifier = Modifier.padding(paddingLarge)) {
                Text(
                    text  = newsItem.title,
                    style = TitleMediumBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(heightExtraSmall))
                Text(
                    text  = newsItem.source.name,
                    style = LabelSmallMedium,
                    color = MaterialTheme.colorScheme.secondary
                )
                Spacer(Modifier.height(heightSmall))
                newsItem.description?.let {
                    Text(
                        text  = it,
                        style = BodySmallRegular,
                        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f)
                    )
                }
            }
        }
    }
}
