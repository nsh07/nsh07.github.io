package org.nsh07.nsh07.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nsh07.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    val uriHandler = LocalUriHandler.current
    var selectedItem by remember { mutableStateOf(0) }
    val paragraphs = remember {
        listOf(
            "Hi, I'm Nishant. I'm currently a hobbyist software developer and a computer science student at the Indian Institute of Information Technology Bhagalpur.",
            "I've written a variety of programs in multiple languages over my years as a hobbyist developer since back when I was in middle and high school (~2019) in Python and C++, spanning multiple areas like games, CLI tools, GUI tools and automation scripts. I'm continuing to work towards persuing my passion of software development as my career, now as a CS student."
        )
    }

    Row(
        modifier = Modifier
            .padding(horizontal = 48.dp)
            .widthIn(max = 1200.dp)
            .then(modifier)
    ) {
        Column(Modifier.padding(vertical = 96.dp).weight(1f)) {
            Text(
                "Nishant Mishra",
                style = typography.displayLarge.copy(fontSize = 48.sp),
                color = colorScheme.onSurface,
//                modifier = Modifier.padding(start = 20.dp)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "Mobile app developer",
                style = typography.titleLarge,
                color = colorScheme.onSurface,
//                modifier = Modifier.padding(start = 20.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text(
                "I build performant, beautiful apps for mobile phones.",
                style = typography.bodyLarge,
                color = colorScheme.onSurfaceVariant,
                modifier = Modifier.widthIn(max = 320.dp)
            )

            Spacer(Modifier.height(72.dp))

            NavigationItem(
                selected = selectedItem == 0,
                onClick = { selectedItem = 0 },
                label = { Text("About", style = typography.bodyMedium) },
                modifier = Modifier.offset(x = (-20).dp)
            )
            NavigationItem(
                selected = selectedItem == 1,
                onClick = { selectedItem = 1 },
                label = { Text("Experience", style = typography.bodyMedium) },
                modifier = Modifier.offset(x = (-20).dp)
            )
            NavigationItem(
                selected = selectedItem == 2,
                onClick = { selectedItem = 2 },
                label = { Text("Projects", style = typography.bodyMedium) },
                modifier = Modifier.offset(x = (-20).dp)
            )

            Spacer(Modifier.weight(1f))

            Spacer(Modifier.padding(top = 32.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.padding(start = 20.dp)
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

        LazyColumn(
            contentPadding = PaddingValues(vertical = 96.dp),
            modifier = Modifier.fillMaxHeight().weight(1f)
        ) {
            items(paragraphs) {
                Text(
                    it,
                    style = typography.bodyLarge,
                    color = colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            item {
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
            item {
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
                        append("'s website")
                    },
                    color = colorScheme.outline,
                    style = typography.bodyMedium
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
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
                    Text(" in Kotlin. Deployed with ", style = typography.bodyMedium, color = colorScheme.outline)
                    Icon(
                        painterResource(Res.drawable.githubpages),
                        null,
                        modifier = Modifier
                            .height(20.dp)
                            .clickable { uriHandler.openUri("https://pages.github.com/") }
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
                    color = colorScheme.outline, style = typography.bodyMedium
                )
            }
        }
    }
}
