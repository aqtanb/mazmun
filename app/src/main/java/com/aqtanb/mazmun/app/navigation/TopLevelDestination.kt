package com.aqtanb.mazmun.app.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.aqtanb.mazmun.R
import com.aqtanb.mazmun.feature.channel.navigation.ChannelRoute
import com.aqtanb.mazmun.feature.feed.navigation.FeedRoute
import com.aqtanb.mazmun.feature.profile.navigation.ProfileRoute
import com.aqtanb.mazmun.feature.search.navigation.SearchRoute
import kotlin.reflect.KClass

enum class TopLevelDestination(
    val route: KClass<*>,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    @StringRes val iconTextId: Int,
    @StringRes val titleTextId: Int
) {
    FEED(
        route = FeedRoute::class,
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        iconTextId = R.string.feature_feed_title,
        titleTextId = R.string.app_name,
    ),
    SEARCH(
        route = SearchRoute::class,
        selectedIcon = Icons.Filled.Search,
        unselectedIcon = Icons.Outlined.Search,
        iconTextId = R.string.feature_search_title,
        titleTextId = R.string.feature_search_title,
    ),
    CHANNEL(
        route = ChannelRoute::class,
        selectedIcon = Icons.Filled.Person,
        unselectedIcon = Icons.Outlined.Person,
        iconTextId = R.string.feature_channel_title,
        titleTextId = R.string.feature_channel_title,
    ),
    PROFILE(
        route = ProfileRoute::class,
        selectedIcon = Icons.Filled.Menu,
        unselectedIcon = Icons.Outlined.Menu,
        iconTextId = R.string.feature_profile_title,
        titleTextId = R.string.feature_profile_title,
    )
}
