package com.aqtanb.mazmun.feature.search

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
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

// Data class for feed items
data class SearchItem(
    val id: String,
    val name: String,
    val description: String,
    val followerCount: Int,
    val status: String,
    val iconColor: Color,
    val following: Boolean = false
)

@Composable
fun SearchScreen(modifier: Modifier = Modifier) {
    // Sample feed data
    val searchItems = listOf(
        SearchItem(
            id = "1",
            name = "Startup Founders",
            description = "Chat, learn, and share stories with founders and early employees",
            followerCount = 6100,
            status = "Trending",
            iconColor = Color(0xFF5D5FEF),
            following = false,
        ),
        SearchItem(
            id = "2",
            name = "Wellness Chat",
            description = "Daily motivation, fitness, and self-care tips",
            followerCount = 4800,
            status = "Popular",
            iconColor = Color(0xFFFF4081),
            following = true,
        ),
        SearchItem(
            id = "3",
            name = "Photo Creators",
            description = "Share, review, and discuss creative photography",
            followerCount = 5200,
            status = "Active",
            iconColor = Color(0xFFFFB300),
            following = false,
        ),
        SearchItem(
            id = "4",
            name = "Book Lovers",
            description = "Discuss novels, non-fiction, and literary classics",
            followerCount = 7900,
            status = "Featured",
            iconColor = Color(0xFF2196F3),
            following = false,
        ),
        SearchItem(
            id = "5",
            name = "Eco Warriors",
            description = "Green tech, eco-initiatives, and sustainable living",
            followerCount = 2400,
            status = "Gaining",
            iconColor = Color(0xFF8BC34A),
            following = false,
        ),
    )

    // Tab state
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Most Active", "Newest")

    Column(modifier = modifier.fillMaxSize()) {
        // Tabs at the top
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    text = { Text(title) },
                )
            }
        }

        // Feed items
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(searchItems) { item ->
                SearchItemCard(item = item)
            }
        }
    }
}

@Composable
fun SearchItemCard(
    item: SearchItem,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(item.iconColor),
                contentAlignment = Alignment.Center,
            ) {
                // Use first letter of name as icon
                Text(
                    text = item.name.first().toString(),
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }

            // Channel info
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp),
            ) {
                Text(
                    text = item.name,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                )

                Text(
                    text = item.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "${formatFollowerCount(item.followerCount)} followers",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = "•",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )

                    Spacer(modifier = Modifier.width(8.dp))

                    Text(
                        text = item.status,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }

            // Follow button
            Button(
                onClick = {},
                shape = RoundedCornerShape(4.dp),
                modifier = Modifier.padding(start = 8.dp),
                colors = if (item.following) {
                    MaterialTheme.colorScheme.primary.let { primary ->
                        ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = primary,
                        )
                    }
                } else {
                    ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF00C853),
                    )
                },
            ) {
                Text(
                    text = if (item.following) "Following" else "Follow",
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }
    }
}

private fun formatFollowerCount(count: Int): String {
    return when {
        count >= 1000 -> String.format("%.1fk", count / 1000.0)
        else -> count.toString()
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    MaterialTheme {
        SearchScreen()
    }
}
