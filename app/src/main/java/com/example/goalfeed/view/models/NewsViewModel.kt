package com.example.goalfeed.view.models

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.goalfeed.home.NewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor() : ViewModel() {

    private val _news = MutableStateFlow<List<NewsItem>>(emptyList())
    val news: StateFlow<List<NewsItem>> = _news.asStateFlow()

    init {
        loadNews()
    }

    private fun loadNews() {
        val mock = listOf(
            NewsItem(
                title = "Messi Scores Stunning Free Kick in MLS Clash",
                source = "ESPN",
                imageUrl = "https://e0.365dm.com/23/07/1600x900/skysports-messi-lionel-inter_6226518.jpg?20230722070304",
                description = "Lionel Messi led Inter Miami to a dramatic win with a free kick goal in the 89th minute."
            ),
            NewsItem(
                title = "Real Madrid and Manchester City Draw in Champions League Thriller",
                source = "UEFA.com",
                imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6v9WhhSJHWksO0S4-PajBcj5jsptDvuZ1zA&s",
                description = "The two European giants played out a thrilling 3-3 draw in the quarterfinals first leg."
            ),
            NewsItem(
                title = "Boca Juniors to Face River in Superclásico This Weekend",
                source = "TyC Sports",
                imageUrl = "https://fotos.perfil.com/2023/05/11/trim/1140/641/river-plate-boca-game-1566106.jpg",
                description = "The iconic Argentine rivalry returns with both teams fighting for playoff spots."
            ),
            NewsItem(
                title = "Barcelona Confirms Xavi Will Leave at End of Season",
                source = "Goal.com",
                imageUrl = "https://www.irvinetimes.com/resources/images/17689663/?type=responsive-gallery-fullscreen",
                description = "Xavi Hernández announced his decision to step down as Barcelona head coach this summer."
            ),
            NewsItem(
                title = "Cristiano Ronaldo Sets New Record in Saudi Pro League",
                source = "BBC Sport",
                imageUrl = "https://uzalendonews.co.ke/wp-content/uploads/2024/05/GettyImages-2154409480.webp",
                description = "The Portuguese striker continues to break records, scoring his 35th goal of the season."
            ),
            NewsItem(
                title = "UEFA Reveals Euro 2024 Official Ball and Match Schedule",
                source = "UEFA.com",
                imageUrl = "https://editorial.uefa.com/resources/0287-19768b8cb92a-0101d7ec2f45-1000/1190881-231010_omb_0358_fullframe.jpeg",
                description = "UEFA presented the new 'FUSSBALLLIEBE' ball and the full schedule for this summer’s tournament."
            )
        )
        viewModelScope.launch {
            _news.emit(mock)
        }
    }
}
