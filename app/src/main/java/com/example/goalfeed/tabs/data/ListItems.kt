package com.example.goalfeed.tabs.data

import com.example.goalfeed.navigation.GoalFeedScreen

val items = listOf(
    ListItem(
        GoalFeedScreen.Basics.name,
        category = ListItemCategory.Initial,
    ),
    ListItem(
        GoalFeedScreen.Texts.name,
    ),
    ListItem(
        GoalFeedScreen.Buttons.name,
    ),
    ListItem(
        GoalFeedScreen.Columns.name,
    ),
    ListItem(
        GoalFeedScreen.Rows.name,
    ),
    ListItem(
        GoalFeedScreen.Cards.name,
    ),
    ListItem(
        GoalFeedScreen.Icons.name,
    ),
    ListItem(
        GoalFeedScreen.Chips.name,
    ),
    ListItem(
        GoalFeedScreen.Switches.name,
    ),
    ListItem(
        GoalFeedScreen.Tabs.name,
    ),
    ListItem(
        GoalFeedScreen.FABs.name,
    ),
    ListItem(
        GoalFeedScreen.Checkboxes.name,
    ),
)

data class ListItem(
    val title: String,
    val category: ListItemCategory = ListItemCategory.Basic,
)

enum class ListItemCategory {
    Initial,
    Basic,
}