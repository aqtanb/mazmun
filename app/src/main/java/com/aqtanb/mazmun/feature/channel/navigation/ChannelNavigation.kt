package com.aqtanb.mazmun.feature.channel.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.aqtanb.mazmun.app.navigation.Screen
import com.aqtanb.mazmun.feature.channel.ChannelScreen

fun NavGraphBuilder.channelScreen() {
    composable(Screen.NavigationBarScreen.Channel.route) {
        ChannelScreen()
    }
}
