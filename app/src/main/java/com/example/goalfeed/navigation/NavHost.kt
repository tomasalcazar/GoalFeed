package com.example.goalfeed.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.goalfeed.MainMenu
import com.example.goalfeed.favourite.Favorite
import com.example.goalfeed.matches.Matches
import com.example.goalfeed.tabs.Basic
import com.example.goalfeed.tabs.Buttons
import com.example.goalfeed.tabs.Cards
import com.example.goalfeed.tabs.Checkboxes
import com.example.goalfeed.tabs.Chips
import com.example.goalfeed.tabs.Columns
import com.example.goalfeed.tabs.FABs
import com.example.goalfeed.tabs.Icons
import com.example.goalfeed.tabs.Rows
import com.example.goalfeed.tabs.Switches
import com.example.goalfeed.tabs.Tabs
import com.example.goalfeed.tabs.Texts
import com.example.goalfeed.profile.User


@Composable
fun NavHostComposable(innerPadding: PaddingValues, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = GoalFeedScreen.Home.name,
        modifier = Modifier.fillMaxSize().padding(innerPadding).padding(20.dp)
    ) {
        composable(route = GoalFeedScreen.Home.name) {
            MainMenu(
                onClick = { navController.navigate(it) }
            )
        }
        composable(route = GoalFeedScreen.Favorite.name) {
            Favorite()
        }
        composable(route = GoalFeedScreen.Profile.name) {
            User()
        }

        composable(route = GoalFeedScreen.Matches.name) {
            Matches()
        }

        composable(route = GoalFeedScreen.Basics.name) {
            Basic(
                onClick = { navController.navigate(it) }
            )
        }
        composable(route = GoalFeedScreen.Texts.name) {
            Texts()
        }
        composable(route = GoalFeedScreen.Buttons.name) {
            Buttons()
        }
            composable(route = GoalFeedScreen.Columns.name) {
            Columns()
        }
        composable(route = GoalFeedScreen.Rows.name) {
            Rows()
        }
        composable(route = GoalFeedScreen.Cards.name) {
            Cards()
        }
        composable(route = GoalFeedScreen.Icons.name) {
            Icons()
        }
        composable(route = GoalFeedScreen.Chips.name) {
            Chips()
        }
        composable(route = GoalFeedScreen.Switches.name) {
            Switches()
        }
        composable(route = GoalFeedScreen.Tabs.name) {
            Tabs()
        }
        composable(route = GoalFeedScreen.FABs.name) {
            FABs()
        }
        composable(route = GoalFeedScreen.Checkboxes.name) {
            Checkboxes()
        }
    }
}