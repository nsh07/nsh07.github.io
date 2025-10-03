package org.nsh07.nsh07.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import nsh07.composeapp.generated.resources.Res
import nsh07.composeapp.generated.resources.open_in_browser
import org.jetbrains.compose.resources.painterResource
import kotlin.text.Typography.bullet
import kotlin.text.Typography.mdash

@Composable
fun ExperienceCard(
    experience: Experience,
    cardPadding: Dp,
    modifier: Modifier = Modifier
) {
    val colorScheme = colorScheme
    val uriHandler = LocalUriHandler.current

    val interactionSource = remember { MutableInteractionSource() }
    val hovered by interactionSource.collectIsHoveredAsState()

    val backgroundColor by animateColorAsState(
        if (hovered) colorScheme.surfaceContainerLowest else colorScheme.surface,
        animationSpec = tween()
    )
    Box(
        modifier
            .clip(shapes.large)
            .background(backgroundColor)
            .clickable(
                onClick = { uriHandler.openUri(experience.companyUrl) },
                interactionSource = interactionSource
            )
    ) {
        Row(Modifier.fillMaxWidth().padding(cardPadding)) {
            Text(
                remember { "${experience.start} $mdash ${experience.end}".toUpperCase(Locale.current) },
                style = typography.labelMedium,
                color = colorScheme.outline,
                modifier = Modifier.padding(vertical = 4.dp).weight(1f)
            )
            Column(Modifier.weight(3f)) {
                FlowRow(itemVerticalAlignment = Alignment.CenterVertically) {
                    Text("${experience.position} $bullet ${experience.company} ")
                    Icon(painterResource(Res.drawable.open_in_browser), null, modifier = Modifier.size(16.dp))
                }
                Text(
                    experience.description,
                    style = typography.bodyMedium,
                    color = colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp)
                )
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    experience.skills.fastForEach {
                        Box(Modifier.clip(CircleShape).background(colorScheme.secondaryContainer)) {
                            Text(
                                it,
                                style = typography.labelMedium,
                                color = colorScheme.onSecondaryContainer,
                                modifier = Modifier.padding(vertical = 4.dp, horizontal = 12.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

data class Experience(
    val start: String,
    val end: String,
    val position: String,
    val description: String,
    val company: String,
    val companyUrl: String,
    val skills: List<String>
)