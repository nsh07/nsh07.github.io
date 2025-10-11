package org.nsh07.nsh07.ui.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.platform.UriHandler
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nsh07.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun NameAndDesc(horizontalPadding: Dp = 0.dp) {
    Text(
        "Nishant Mishra",
        style = typography.displayLarge.copy(fontSize = 48.sp),
        color = colorScheme.onSurface,
        modifier = Modifier.padding(horizontal = horizontalPadding)
    )
    Spacer(Modifier.height(4.dp))
    Text(
        "App developer",
        style = typography.titleLarge,
        color = colorScheme.onSurface,
        modifier = Modifier.padding(horizontal = horizontalPadding)
    )
    Spacer(Modifier.height(20.dp))
    Text(
        "I build performant, beautiful apps for mobile and desktop.",
        style = typography.bodyLarge,
        color = colorScheme.onSurfaceVariant,
        modifier = Modifier.widthIn(max = 320.dp).padding(horizontal = horizontalPadding)
    )
}

@Composable
fun SocialIcons(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        IconButton(onClick = { uriHandler.openUri("https://github.com/nsh07") }) {
            Icon(
                painterResource(Res.drawable.github),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        IconButton(onClick = { uriHandler.openUri("https://gitlab.com/nsh07") }) {
            Icon(
                painterResource(Res.drawable.gitlab),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        IconButton(onClick = { uriHandler.openUri("https://www.linkedin.com/in/nsh07/") }) {
            Icon(
                painterResource(Res.drawable.linkedin),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
        IconButton(onClick = { uriHandler.openUri("mailto:nishant.28@outlook.com") }) {
            Icon(
                painterResource(Res.drawable.email),
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
fun LazyListScope.mainContent(
    paragraphs: List<String>,
    experiences: List<Experience>,
    projectState: ProjectsState,
    cardPadding: Dp,
    uriHandler: UriHandler,
    wide: Boolean,
    topPadding: Dp = 0.dp
) {
    items(paragraphs, key = { it.substring(0, 16) }) {
        Spacer(Modifier.height(topPadding))
        Text(
            it,
            style = typography.bodyLarge,
            color = colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = cardPadding, bottom = 16.dp, end = cardPadding)
        )
    }
    item("experience spacer") { Spacer(Modifier.height(112.dp)) }
    items(experiences, key = { it.start }) {
        ExperienceCard(
            experience = it,
            cardPadding = cardPadding,
            wide = wide,
            modifier = Modifier.padding(bottom = 32.dp)
        )
    }
    item("linkedin link text") {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = cardPadding)
                .clickable { uriHandler.openUri("https://www.linkedin.com/in/nsh07/") }
        ) {
            Text("View LinkedIn profile ", style = typography.bodyLarge, fontWeight = FontWeight.SemiBold)
            Icon(painterResource(Res.drawable.open_in_browser), null, Modifier.size(16.dp))
        }
        Spacer(Modifier.height(112.dp))
    }
    if (!projectState.isLoading)
        items(projectState.projects, key = { it.id }) {
            ProjectCard(
                project = it,
                cardPadding = cardPadding,
                wide = wide,
                modifier = Modifier.padding(bottom = 32.dp)
            )
        }
    else item {
        LinearWavyProgressIndicator(
            Modifier.fillMaxWidth().padding(cardPadding).padding(bottom = 32.dp)
        )
    }
    item("github link text") {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = cardPadding)
                .clickable { uriHandler.openUri("https://github.com/nsh07") }
        ) {
            Text("View all projects on GitHub ", style = typography.bodyLarge, fontWeight = FontWeight.SemiBold)
            Icon(painterResource(Res.drawable.open_in_browser), null, Modifier.size(16.dp))
        }
        Spacer(Modifier.height(112.dp))
    }
    item("work in progress") {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(vertical = 56.dp)
        ) {
            Icon(
                painterResource(Res.drawable.forklift),
                null,
                modifier = Modifier.size(64.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text("Work in progress", style = typography.headlineSmall)
        }
    }
    item("tech stack spacer") { Spacer(Modifier.height(112.dp)) }
    item("tech stack") {
        FlowRow(
            itemVerticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = cardPadding)
        ) {
            Text(
                buildAnnotatedString {
                    append("Built with ")
                    withLink(
                        LinkAnnotation.Url(
                            url = "https://www.jetbrains.com/compose-multiplatform/",
                            styles = TextLinkStyles(SpanStyle(color = colorScheme.onSurface))
                        )
                    ) {
                        append("Compose Multiplatform ")
                    }
                },
                style = typography.bodyMedium,
                color = colorScheme.outline
            )
            Icon(
                painterResource(Res.drawable.compose),
                null,
                modifier = Modifier.clickable { uriHandler.openUri("https://www.jetbrains.com/compose-multiplatform/") }
            )
            Text(" in Kotlin. ", style = typography.bodyMedium, color = colorScheme.outline)
            Text(
                buildAnnotatedString {
                    append("Deployed with ")
                    withLink(
                        LinkAnnotation.Url(
                            url = "https://www.jetbrains.com/compose-multiplatform/",
                            styles = TextLinkStyles(SpanStyle(color = colorScheme.onSurface))
                        )
                    ) {
                        append("GitHub Pages.")
                    }
                },
                style = typography.bodyMedium,
                color = colorScheme.outline
            )
            Text(".", style = typography.bodyMedium, color = colorScheme.outline)
        }
        Text(
            buildAnnotatedString {
                append("Text is set in ")
                withLink(
                    LinkAnnotation.Url(
                        url = "https://fonts.google.com/specimen/DM+Serif+Text",
                        styles = TextLinkStyles(
                            SpanStyle(
                                color = colorScheme.onSurface,
                                fontFamily = typography.displayLarge.fontFamily
                            )
                        )
                    )
                ) {
                    append("DM Serif Text")
                }
                append(" and ")
                withLink(
                    LinkAnnotation.Url(
                        url = "https://rsms.me/inter/",
                        styles = TextLinkStyles(SpanStyle(color = colorScheme.onSurface))
                    )
                ) {
                    append("Inter")
                }
                append('.')
            },
            color = colorScheme.outline,
            style = typography.bodyMedium,
            modifier = Modifier.padding(horizontal = cardPadding)
        )
        Text(
            buildAnnotatedString {
                append("Layout inspired by ")
                withLink(
                    LinkAnnotation.Url(
                        url = "https://brittanychiang.com/",
                        styles = TextLinkStyles(SpanStyle(color = colorScheme.onSurface))
                    )
                ) {
                    append("Brittany Chiang")
                }
                append("'s website.")
            },
            color = colorScheme.outline,
            style = typography.bodyMedium,
            modifier = Modifier.padding(horizontal = cardPadding)
        )
    }
}
