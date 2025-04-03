package com.example.goalfeed.matches

import androidx.compose.foundation.clickable
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
fun MatchCard(matchItem: MatchItem, onClick: () -> Unit) {
    val statusColor = when (matchItem.status) {
        "Live" -> RedLive
        "FT" -> GrayFT
        else -> BlueUpcoming
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .clickable { onClick() },
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = LightCardBackground)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(
                text = matchItem.date,
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
                        text = matchItem.homeTeam,
                        style = BodyLargeSemiBold,
                        color = CardTextPrimary
                    )
                    Text(
                        text = matchItem.awayTeam,
                        style = BodyLargeSemiBold,
                        color = CardTextPrimary
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (matchItem.homeScore != null && matchItem.awayScore != null)
                            "${matchItem.homeScore} - ${matchItem.awayScore}"
                        else matchItem.time,
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
