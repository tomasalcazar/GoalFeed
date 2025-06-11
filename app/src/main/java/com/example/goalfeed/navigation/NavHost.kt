package com.example.goalfeed.navigation

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.goalfeed.home.NewsDetail
import com.example.goalfeed.matches.Matches
import com.example.goalfeed.matches.MatchDetail
//import com.example.goalfeed.favorite.Favorite
import com.example.goalfeed.user.User
import com.example.goalfeed.matches.MatchesViewModel
import com.example.goalfeed.home.NewsViewModel

@RequiresApi(Build.VERSION_CODES.UPSIDE_DOWN_CAKE)
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
        composable(GoalFeedScreen.Home.name) {
            MainMenu { index ->
                navController.navigate("${GoalFeedScreen.NewsDetail.name}/$index")
            }
        }

        composable(GoalFeedScreen.Matches.name) {

            Matches { index ->
                    navController.navigate("${GoalFeedScreen.MatchDetail.name}/$index")
                }
        }

        composable(
            route = "${GoalFeedScreen.NewsDetail.name}/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val vm: NewsViewModel = hiltViewModel()
            val news by vm.news.collectAsState()
            val idx = backStackEntry.arguments?.getInt("index") ?: 0
            news.getOrNull(idx)?.let { item ->
                NewsDetail(item)
            }
        }

        composable(
            route = "${GoalFeedScreen.MatchDetail.name}/{index}",
            arguments = listOf(navArgument("index") { type = NavType.IntType })
        ) { backStackEntry ->
            val vm: MatchesViewModel = hiltViewModel()
            val matches by vm.matches.collectAsState()
            val idx = backStackEntry.arguments?.getInt("index") ?: 0
            matches.getOrNull(idx)?.let { match ->
                MatchDetail(match)
            }
        }

//        composable(GoalFeedScreen.Favorite.name) {
//            Favorite()
//        }

        composable(GoalFeedScreen.User.name) {
            User()
        }
    }
}
