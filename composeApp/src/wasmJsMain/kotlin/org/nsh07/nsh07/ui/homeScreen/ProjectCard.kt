package org.nsh07.nsh07.ui.homeScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
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
import nsh07.composeapp.generated.resources.fork
import nsh07.composeapp.generated.resources.open_in_browser
import nsh07.composeapp.generated.resources.star
import org.jetbrains.compose.resources.painterResource
import org.nsh07.nsh07.network.Repo

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ProjectCard(
    project: Repo,
    cardPadding: Dp,
    modifier: Modifier = Modifier,
    imageUri: String? = null,
    description: String? = null,
) {
    val colorScheme = colorScheme
    val uriHandler = LocalUriHandler.current

    Box(
        modifier
            .clip(shapes.largeIncreased)
            .clickable { uriHandler.openUri(project.htmlUrl) }
    ) {
        Row(Modifier.fillMaxWidth().padding(cardPadding)) {
            SubcomposeAsyncImage(
                model = imageUri
                    ?: "https://raw.githubusercontent.com/${project.fullName}/refs/heads/main/fastlane/metadata/android/en-US/images/featureGraphic.png",
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.padding(top = 4.dp, end = 16.dp).weight(1f).clip(shapes.large),
                loading = {
                    Box(Modifier.fillMaxWidth().aspectRatio(2f)) {
                        CircularWavyProgressIndicator(Modifier.align(Alignment.Center))
                    }
                },
                error = {
                    Box(Modifier.fillMaxWidth().aspectRatio(2f).border(1.dp, colorScheme.outline, shapes.large))
                }
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
                    description ?: project.description,
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
                    Spacer(Modifier.width(8.dp))
                    Icon(
                        painterResource(Res.drawable.fork),
                        null,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(project.forksCount.toString(), style = typography.labelLarge)
                }
                LabelRow(project.topics, Modifier.padding(top = 16.dp))
            }
        }
    }
}