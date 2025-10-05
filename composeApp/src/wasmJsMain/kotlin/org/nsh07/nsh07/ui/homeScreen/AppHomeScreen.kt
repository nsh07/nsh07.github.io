package org.nsh07.nsh07.ui.homeScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import nsh07.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource

@Composable
fun AppHomeScreen(
    projectState: ProjectsState,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    val scope = rememberCoroutineScope()

    val listState = rememberLazyListState()
    val firstVisibleItem by derivedStateOf { listState.firstVisibleItemIndex }

    val paragraphs = remember {
        listOf(
            "Hi, I'm Nishant. I'm currently a hobbyist open-source software developer and a computer science student at the Indian Institute of Information Technology Bhagalpur.",
            "I've written a variety of programs in multiple languages over my years as a hobbyist developer since back when I was in middle and high school (~2019) in Python and C++, spanning multiple areas like games, CLI tools, GUI tools and automation scripts. I'm continuing to work towards persuing my passion of software development as my career, now as a CS student."
        )
    }
    val paragraphCount = remember { paragraphs.size }

    val experiences = remember {
        listOf(
            Experience(
                start = "Aug 2025",
                end = "Present",
                position = "Open Source Lead",
                description = "Perform the role of Open Source Lead of the Development Club. Helped successfully organise OPCODE (Open Source Fest) 2025.",
                company = "DevC, IIIT Bhagalpur",
                companyUrl = "https://gymkhana.iiitbh.ac.in/technical/",
                skills = listOf()
            ),
            Experience(
                start = "May",
                end = "June 2025",
                position = "Research Asst. (Android Dev)",
                description = "Created an Android app for collecting and compiling a validation dataset for a binary image classification model from scratch. Generated output from the model on-device for a fast, offline experience. Designed an intuitive UI, with options for viewing and editing entries in the dataset. Optimized the UX: the validation dataset can be exported to the device with the click of a button.",
                company = "AIIMS Guwahati",
                companyUrl = "https://aiimsguwahati.ac.in/",
                skills = listOf("Kotlin", "Jetpack Compose", "PyTorch Android", "Android SDK")
            )
        )
    }
    val experienceCount = remember { experiences.size }

    val cardPadding = remember { 16.dp }

    Row(
        modifier = Modifier
            .padding(horizontal = 48.dp)
            .widthIn(max = 1200.dp)
            .then(modifier)
    ) {
        Column(Modifier.padding(vertical = 96.dp).weight(1f).verticalScroll(rememberScrollState())) {
            Text(
                "Nishant Mishra",
                style = typography.displayLarge.copy(fontSize = 48.sp),
                color = colorScheme.onSurface
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "App developer",
                style = typography.titleLarge,
                color = colorScheme.onSurface
            )
            Spacer(Modifier.height(20.dp))
            Text(
                "I build performant, beautiful apps for mobile and desktop.",
                style = typography.bodyLarge,
                color = colorScheme.onSurfaceVariant,
                modifier = Modifier.widthIn(max = 320.dp)
            )

            Spacer(Modifier.height(72.dp))

            NavigationItem(
                selected = firstVisibleItem < paragraphCount + 1,
                onClick = {
                    scope.launch { listState.animateScrollToItem(0) }
                },
                label = { Text("About", style = typography.bodyMedium) },
                modifier = Modifier.offset(x = (-20).dp)
            )
            NavigationItem(
                selected = firstVisibleItem in paragraphCount + 1..<paragraphCount + experienceCount + 2,
                onClick = {
                    scope.launch { listState.animateScrollToItem(paragraphCount + 1) }
                },
                label = { Text("Experience", style = typography.bodyMedium) },
                modifier = Modifier.offset(x = (-20).dp)
            )
            NavigationItem(
                selected = firstVisibleItem >= paragraphCount + experienceCount + 2,
                onClick = {
                    scope.launch { listState.animateScrollToItem(paragraphCount + experienceCount + 2) }
                },
                label = { Text("Projects", style = typography.bodyMedium) },
                modifier = Modifier.offset(x = (-20).dp)
            )

            Spacer(Modifier.weight(1f))

            Spacer(Modifier.padding(top = 32.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
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
            state = listState,
            contentPadding = PaddingValues(vertical = 96.dp),
            modifier = Modifier.fillMaxHeight().weight(1.1f)
        ) {
            items(paragraphs, key = { it.substring(0, 16) }) {
                Text(
                    it,
                    style = typography.bodyLarge,
                    color = colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = cardPadding, bottom = 16.dp, end = cardPadding)
                )
            }
            item("experience spacer") { Spacer(Modifier.height(112.dp)) }
            items(experiences, key = { it.start }) {
                ExperienceCard(experience = it, cardPadding = cardPadding, modifier = Modifier.padding(bottom = 32.dp))
            }
            item("linkedin link text") {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(start = cardPadding)
                        .clickable { uriHandler.openUri("https://www.linkedin.com/in/nsh07/") }
                ) {
                    Text("View LinkedIn Profile ", style = typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                    Icon(painterResource(Res.drawable.open_in_browser), null, Modifier.size(16.dp))
                }
                Spacer(Modifier.height(112.dp))
            }
            items(projectState.projects, key = { it.id }) {
                ProjectCard(
                    project = it,
                    cardPadding = cardPadding,
                    projectImageUri = "",
                    modifier = Modifier.padding(bottom = 32.dp)
                )
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
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
    }
}