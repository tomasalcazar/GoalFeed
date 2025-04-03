package com.example.goalfeed.matches

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.goalfeed.ui.theme.*
import androidx.compose.material3.Text

@Composable
fun MatchCard(match: MatchItem) {
    val statusColor = when (match.status) {
        "Live" -> RedLive
        "FT" -> GrayFT
        else -> BlueUpcoming
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = LightCardBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = match.date,
                style = LabelSmallMedium,
                color = BlueUpcoming
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = match.homeTeam,
                        style = BodyLargeSemiBold,
                        color = CardTextPrimary
                    )
                    Text(
                        text = match.awayTeam,
                        style = BodyLargeSemiBold,
                        color = CardTextPrimary
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (match.homeScore != null && match.awayScore != null)
                            "${match.homeScore} - ${match.awayScore}"
                        else match.time,
                        style = TitleMediumBold,
                        color = CardTextPrimary
                    )
                    Text(
                        text = match.status,
                        style = LabelSmallMedium,
                        color = statusColor
                    )
                }
            }
        }
    }
}
