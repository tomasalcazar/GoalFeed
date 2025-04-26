package com.example.goalfeed.matches

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.goalfeed.ui.theme.*

@Composable
fun MatchCard(matchItem: Match) {
    val statusColor = when (matchItem.status.uppercase()) {
        "LIVE" -> RedLive
        "FT", "FINISHED" -> GrayFT
        else -> BlueUpcoming
    }

    val dateText = matchItem.utcDate.substringBefore("T")
    val timeText = matchItem.utcDate
        .substringAfter("T")
        .substringBefore("Z")
        .take(5)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = paddingLarge),
        elevation = CardDefaults.cardElevation(defaultElevation = elevationLarge),
        colors = CardDefaults.cardColors(containerColor = LightCardBackground)
    ) {
        Column(modifier = Modifier.padding(paddingLarge)) {
            Text(
                text = dateText,
                style = LabelSmallMedium,
                color = BlueUpcoming
            )

            Spacer(modifier = Modifier.height(heightSmall))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = matchItem.homeTeam.name,
                        style = BodyLargeSemiBold,
                        color = CardTextPrimary
                    )
                    Text(
                        text = matchItem.awayTeam.name,
                        style = BodyLargeSemiBold,
                        color = CardTextPrimary
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (matchItem.score.fullTime.home != null && matchItem.score.fullTime.away != null)
                            "${matchItem.score.fullTime.home} - ${matchItem.score.fullTime.away}"
                        else timeText,
                        style = TitleMediumBold,
                        color = CardTextPrimary
                    )
                    Text(
                        text = matchItem.status,
                        style = LabelSmallMedium,
                        color = statusColor
                    )
                }
            }
        }
    }
}