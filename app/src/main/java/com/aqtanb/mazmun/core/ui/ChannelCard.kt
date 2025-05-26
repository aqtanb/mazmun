package com.aqtanb.mazmun.core.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ChannelCard(
    channelPhoto: Color,
    channelName: String,
    lastMessage: String,
    lastTextTime: String,
    unreadMessageCount: Int,
    modifier: Modifier = Modifier
) {
    Card {
        Row {
            Box(
                modifier = modifier
                    .size(8.dp)
                    .clip(shape = CircleShape)
                    .background(color = channelPhoto),
            )
            Column {
                Text(text = channelName)
                Text(text = lastMessage)
            }
            Column {
                Text(text = lastTextTime)
                Text(text = unreadMessageCount.toString())
            }
        }
    }
}

@Preview
@Composable
fun ChannelCardPreview() {
    ChannelCard(
        channelPhoto = Color.Blue,
        channelName = "Channel Name",
        lastMessage = "Last Message",
        lastTextTime = "10:00",
        unreadMessageCount = 5,
    )
}
