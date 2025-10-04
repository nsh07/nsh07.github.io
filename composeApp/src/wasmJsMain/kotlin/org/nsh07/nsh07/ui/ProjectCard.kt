package org.nsh07.nsh07.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import nsh07.composeapp.generated.resources.Res
import nsh07.composeapp.generated.resources.open_in_browser
import nsh07.composeapp.generated.resources.star
import org.jetbrains.compose.resources.painterResource
import org.nsh07.nsh07.ui.network.Repo

@Composable
fun ProjectCard(
    project: Repo,
    cardPadding: Dp,
    projectImageUri: String,
    projectDescription: String,
    modifier: Modifier = Modifier
) {
    val colorScheme = colorScheme
    val uriHandler = LocalUriHandler.current

    Box(
        modifier
            .clip(shapes.large)
            .clickable { uriHandler.openUri(project.htmlUrl) }
    ) {
        Row(Modifier.fillMaxWidth().padding(cardPadding)) {
            SubcomposeAsyncImage(
                model = projectImageUri,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(end = 16.dp).weight(1f)
            )
            Column(Modifier.weight(3f)) {
                FlowRow(itemVerticalAlignment = Alignment.CenterVertically) {
                    Text(project.name, style = typography.bodyLarge, fontWeight = FontWeight.Medium)
                    Icon(
                        painterResource(Res.drawable.open_in_browser),
                        null,
                        modifier = Modifier.padding(start = 4.dp).size(16.dp)
                    )
                }
                Text(
                    projectDescription,
                    style = typography.bodyMedium,
                    color = colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Icon(
                        painterResource(Res.drawable.star),
                        null
                    )
                    Text(project.stargazersCount.toString(), style = typography.labelLarge)
                }
                LabelRow(project.topics, Modifier.padding(top = 16.dp))
            }
        }
    }
}