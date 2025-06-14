package com.example.goalfeed.matches

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.goalfeed.ui.theme.*

@Composable
fun MatchCard(matchItem: Match) {
    // Status color (se mantiene constante para light & dark)
    val statusColor = when (matchItem.status.uppercase()) {
        "LIVE"        -> RedLive
        "FT", "FINISHED" -> GrayFT
        else          -> BlueUpcoming
    }

    // Fecha y hora
    val dateText = matchItem.utcDate.substringBefore("T")
    val timeText = matchItem.utcDate
        .substringAfter("T")
        .substringBefore("Z")
        .take(5)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = paddingLarge),
        shape = RoundedCornerShape(heightLarge),
        elevation = CardDefaults.cardElevation(elevationLarge),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface   // dinámico según theme
        )
    ) {
        Column(modifier = Modifier.padding(paddingLarge)) {
            Text(
                text  = dateText,
                style = LabelSmallMedium,
                color = MaterialTheme.colorScheme.secondary        // en lugar de BlueUpcoming
            )

            Spacer(modifier = Modifier.height(heightSmall))

            Row(
                modifier            = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text  = matchItem.homeTeam.name,
                        style = BodyLargeSemiBold,
                        color = MaterialTheme.colorScheme.onSurface      // dinámico
                    )
                    Text(
                        text  = matchItem.awayTeam.name,
                        style = BodyLargeSemiBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }

                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = if (
                            matchItem.score.fullTime.home != null &&
                            matchItem.score.fullTime.away != null
                        ) "${matchItem.score.fullTime.home} - ${matchItem.score.fullTime.away}"
                        else timeText,
                        style = TitleMediumBold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text  = matchItem.status,
                        style = LabelSmallMedium,
                        color = statusColor
                    )
                }
            }
        }
    }
}
