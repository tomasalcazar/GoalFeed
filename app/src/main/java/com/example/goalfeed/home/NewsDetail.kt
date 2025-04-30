import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import com.example.goalfeed.home.NewsItem
import com.example.goalfeed.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsDetail(newsItem: NewsItem, onBack: () -> Unit) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = { Text(text = newsItem.source.name, style = MaterialTheme.typography.bodyMedium) }
            )
        }
    ) { innerPadding ->
        val fullText = "${newsItem.description ?: ""}\n\n${newsItem.content?.substringBefore("[+") ?: ""}"

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(
                    top = paddingExtraSmall,
                    bottom = paddingSmall,
                    start = paddingMedium,
                    end = paddingMedium
                )
        ) {
            Text(
                text = newsItem.title,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(spacingMedium))

            newsItem.urlToImage?.let {
                AsyncImage(
                    model = it,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(height200)
                )
            }

            Spacer(modifier = Modifier.height(spacingMedium))

            Text(
                text = fullText.trim(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
