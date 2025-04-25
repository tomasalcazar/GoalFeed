package com.example.goalfeed.navigation

import NewsDetail
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.goalfeed.MainMenu
import com.example.goalfeed.matches.Matches
import com.example.goalfeed.favorite.Favorite
import com.example.goalfeed.profile.User
import com.example.goalfeed.tabs.*
import com.example.goalfeed.view.models.NewsViewModel

@Composable
fun NavHostComposable(
    innerPadding: PaddingValues,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = GoalFeedScreen.Home.name,
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        composable(route = GoalFeedScreen.Home.name) {
            MainMenu(
                onClick = { index ->
                    navController.navigate("${GoalFeedScreen.Detail.name}/$index")
                }
            )
        }

        composable(route = GoalFeedScreen.Favorite.name) {
            Favorite()
        }

        composable(route = GoalFeedScreen.Matches.name) {
            Matches()
        }

        composable(route = GoalFeedScreen.Profile.name) {
            User()
        }

        composable(route = GoalFeedScreen.Basics.name) { Basic(onClick = { navController.navigate(it) }) }
        composable(route = GoalFeedScreen.Texts.name) { Texts() }
        composable(route = GoalFeedScreen.Buttons.name) { Buttons() }
        composable(route = GoalFeedScreen.Columns.name) { Columns() }
        composable(route = GoalFeedScreen.Rows.name) { Rows() }
        composable(route = GoalFeedScreen.Cards.name) { Cards() }
        composable(route = GoalFeedScreen.Icons.name) { Icons() }
        composable(route = GoalFeedScreen.Chips.name) { Chips() }
        composable(route = GoalFeedScreen.Switches.name) { Switches() }
        composable(route = GoalFeedScreen.Tabs.name) { Tabs() }
        composable(route = GoalFeedScreen.FABs.name) { FABs() }
        composable(route = GoalFeedScreen.Checkboxes.name) { Checkboxes() }

        composable(
            route = "${GoalFeedScreen.Detail.name}/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val viewModel = hiltViewModel<NewsViewModel>()
            val news by viewModel.news.collectAsState()
            val index = backStackEntry.arguments?.getInt("index") ?: 0
            news.getOrNull(index)?.let { newsItem ->
                NewsDetail(newsItem = newsItem) {
                    navController.popBackStack()
                }
            }
        }
    }
}
