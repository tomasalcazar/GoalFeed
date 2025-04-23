import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.example.goalfeed.home.NewsItem

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
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Text(
                text = newsItem.title,
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(12.dp))

            newsItem.urlToImage?.let {
                AsyncImage(
                    model = it,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = fullText.trim(),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
