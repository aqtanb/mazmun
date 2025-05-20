package com.aqtanb.mazmun.feature.channel

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// Data classes for the channel screen
data class Post(
    val id: String,
    val content: String,
    val timeAgo: String,
    val likes: Int,
    val comments: Int
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChannelScreen(modifier: Modifier = Modifier) {
    // Sample posts
    val posts = listOf(
        Post(
            id = "1",
            content = "Stay curious—ask questions, challenge ideas, and never stop learning.",
            timeAgo = "2m ago",
            likes = 12,
            comments = 3,
        ),
        Post(
            id = "2",
            content = "Sometimes the best ideas come at 2 a.m. Share yours here.",
            timeAgo = "45m ago",
            likes = 8,
            comments = 0,
        ),
        Post(
            id = "3",
            content = "Welcome to my channel! Here, your identity stays private—just like mine.",
            timeAgo = "1d ago",
            likes = 22,
            comments = 5,
        ),
    )

    var postText by remember { mutableStateOf("") }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        // Channel header
        item {
            ChannelHeader()
        }

        // Post input
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    OutlinedTextField(
                        value = postText,
                        onValueChange = { postText = it },
                        placeholder = { Text("What's your anonymous") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp),
                        maxLines = 5,
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 8.dp),
                        horizontalArrangement = Arrangement.End,
                    ) {
                        Button(
                            onClick = {},
                            shape = RoundedCornerShape(8.dp),
                        ) {
                            Text("Post")
                        }
                    }
                }
            }
        }

        item {
            Text(
                text = "Recent Posts",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp),
            )
        }

        // Posts
        items(posts) { post ->
            PostItem(post = post)
        }

        item {
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun ChannelHeader(modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Channel icon
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color(0xFF2962FF)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(28.dp),
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                ) {
                    Text(
                        text = "Mystery Voice",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                    )

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Surface(
                            color = Color(0xFF00C853),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.padding(vertical = 4.dp),
                        ) {
                            Text(
                                text = "Anonymous",
                                style = MaterialTheme.typography.labelMedium,
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            )
                        }
                    }
                }
            }

            // Channel description
            Text(
                text = "Share your thoughts and updates with full anonymity.",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 8.dp),
            )

            // Channel tags
            Row(modifier = Modifier.padding(vertical = 8.dp)) {
                FilterChip(
                    selected = true,
                    onClick = {},
                    label = { Text("#thoughts") },
                    modifier = Modifier.padding(end = 8.dp),
                )

                FilterChip(
                    selected = true,
                    onClick = {},
                    label = { Text("#anon") },
                )

                TextButton(
                    onClick = {},
                    modifier = Modifier.padding(start = 8.dp),
                ) {
                    Text("+Tag")
                }
            }

            // Channel stats
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Text(
                    text = "1,024 followers",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp, end = 16.dp),
                )

                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Text(
                    text = "78 posts",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = 4.dp),
                )
            }
        }
    }
}

@Composable
fun PostItem(
    post: Post,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // User icon
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Icon(
                        imageVector = Icons.Filled.Person,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp),
                    )
                }

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp),
                ) {
                    Text(
                        text = "You (Anonymous)",
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                    )

                    Text(
                        text = post.timeAgo,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            // Post content
            Text(
                text = post.content,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(vertical = 12.dp),
            )

            // Post interactions
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(onClick = {}, modifier = Modifier.size(36.dp)) {
                    Icon(
                        imageVector = Icons.Filled.ThumbUp,
                        contentDescription = "Like",
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                Text(
                    text = post.likes.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(end = 16.dp),
                )

                IconButton(onClick = {}, modifier = Modifier.size(36.dp)) {
                    Icon(
                        imageVector = Icons.Filled.Email,
                        contentDescription = "Comment",
                        modifier = Modifier.size(18.dp),
                        tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }

                Text(
                    text = post.comments.toString(),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChannelScreenPreview() {
    MaterialTheme {
        ChannelScreen()
    }
}
