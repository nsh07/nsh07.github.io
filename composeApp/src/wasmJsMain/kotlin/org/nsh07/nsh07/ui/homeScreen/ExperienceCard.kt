package org.nsh07.nsh07.ui.homeScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.font.FontWeight
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

    Box(
        modifier
            .clip(shapes.large)
            .clickable { uriHandler.openUri(experience.companyUrl) }
    ) {
        Row(Modifier.fillMaxWidth().padding(cardPadding)) {
            Text(
                remember { "${experience.start} $mdash ${experience.end}".toUpperCase(Locale.current) },
                style = typography.labelMedium,
                color = colorScheme.outline,
                modifier = Modifier.padding(top = 4.dp, end = 16.dp).weight(1f)
            )
            Column(Modifier.weight(3f)) {
                FlowRow(itemVerticalAlignment = Alignment.CenterVertically) {
                    Text(experience.position, style = typography.bodyLarge, fontWeight = FontWeight.Medium)
                    Text(" $bullet ", style = typography.bodyLarge, fontWeight = FontWeight.Medium)
                    Text(experience.company, style = typography.bodyLarge, fontWeight = FontWeight.Medium)
                    Icon(
                        painterResource(Res.drawable.open_in_browser),
                        null,
                        modifier = Modifier.padding(start = 4.dp).size(16.dp)
                    )
                }
                Text(
                    experience.description,
                    style = typography.bodyMedium,
                    color = colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(top = 8.dp)
                )
                if (experience.skills.isNotEmpty()) LabelRow(experience.skills, Modifier.padding(top = 16.dp))
            }
        }
    }
}

@Composable
fun LabelRow(list: List<String>, modifier: Modifier = Modifier) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        list.fastForEach {
            Box(Modifier.clip(CircleShape).background(colorScheme.secondaryContainer)) {
                Text(
                    it,
                    style = typography.labelMedium,
                    color = colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
                )
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