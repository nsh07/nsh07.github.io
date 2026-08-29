package org.nsh07.nsh07.ui.homeScreen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.motionScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import kotlinx.coroutines.launch
import kotlin.math.abs

@Composable
fun AppHomeScreen(
    projectState: ProjectsState,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    val scope = rememberCoroutineScope()
    val motionScheme = motionScheme

    val listState = rememberLazyListState()

    val paragraphs = remember {
        listOf(
            "Hi, I'm Nishant. I'm currently a hobbyist open-source software developer, a Software Engineer Intern at Regain App, and a computer science student at the Indian Institute of Information Technology Bhagalpur.",
            "I've written a variety of programs in multiple languages over my years as a hobbyist developer since back when I was in middle and high school (~2019) in Python and C++, spanning multiple areas like games, CLI tools, GUI tools and automation scripts. I'm continuing to work towards persuing my passion of software development as my career, now as a CS student."
        )
    }
    val paragraphCount = remember { paragraphs.size }

    val experienceGroups = remember {
        listOf(
            Experience(
                start = "Jul 2026",
                end = "Present",
                position = "Software Engineer Intern",
                description = "",
                company = "Regain App",
                companyUrl = "https://regainapp.ai/",
                skills = listOf(),
                location = "Remote"
            ),
            Experience(
                start = "May 2026",
                end = "Jul 2026",
                position = "Software Engineer Intern",
                description = "Implemented the Regain iOS app from scratch in Swift + SwiftUI using clean architecture principles and latest iOS and SwiftUI best practices (including Swift 6) and launched it into production on the Apple App Store for iPhone.",
                company = "Regain App",
                companyUrl = "https://regainapp.ai/",
                skills = listOf("iOS Development", "Swift", "SwiftUI"),
                location = "Bengaluru, Karnataka, India"
            ),
            Experience(
                start = "Aug 2025",
                end = "April 2026",
                position = "Open Source Lead",
                description = "Performed the role of Open Source Lead of the Development Club. Successfully organised OPCODE (Open Source Fest) 2025, while also contributing a project. Hosted and managed FOSS United's FOSS Meetup at the college, with a participation of ~250.",
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
        ).groupByCompany()
    }
    val experienceCount = remember { experienceGroups.size }

    val cardPadding = 16.dp

    val windowSizeClass = currentWindowAdaptiveInfo().windowSizeClass

    if (windowSizeClass.isWidthAtLeastBreakpoint(WindowSizeClass.WIDTH_DP_EXPANDED_LOWER_BOUND)) {
        val aboutSectionVisible by remember { derivedStateOf { listState.firstVisibleItemIndex < paragraphCount + 1 } }
        val experienceSectionVisible by remember { derivedStateOf { listState.firstVisibleItemIndex in paragraphCount + 1..<paragraphCount + experienceCount + 2 } }
        val projectsSectionVisible by remember { derivedStateOf { listState.firstVisibleItemIndex >= paragraphCount + experienceCount + 2 } }

        Row(
            modifier = Modifier
                .padding(horizontal = 48.dp)
                .widthIn(max = 1200.dp)
                .then(modifier)
        ) {
            Column(Modifier.padding(vertical = 96.dp).weight(1f)) {
                Column(Modifier.verticalScroll(rememberScrollState())) {
                    NameAndDesc()

                    Spacer(Modifier.height(72.dp))

                    NavigationItem(
                        selected = aboutSectionVisible,
                        onClick = {
                            scope.launch { listState.animateScrollToItem(0) }
                        },
                        label = { Text("About", style = typography.bodyMedium) },
                        modifier = Modifier.offset(x = (-20).dp)
                    )
                    NavigationItem(
                        selected = experienceSectionVisible,
                        onClick = {
                            scope.launch { listState.animateScrollToItem(paragraphCount + 1) }
                        },
                        label = { Text("Experience", style = typography.bodyMedium) },
                        modifier = Modifier.offset(x = (-20).dp)
                    )
                    NavigationItem(
                        selected = projectsSectionVisible,
                        onClick = {
                            scope.launch { listState.animateScrollToItem(paragraphCount + experienceCount + 2) }
                        },
                        label = { Text("Projects", style = typography.bodyMedium) },
                        modifier = Modifier.offset(x = (-20).dp)
                    )

                    Spacer(Modifier.height(32.dp))
                }

                Spacer(Modifier.weight(1f))

                SocialIcons()
            }

            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(vertical = 96.dp),
                modifier = Modifier.fillMaxHeight().weight(1.1f)
            ) {
                mainContent(
                    paragraphs,
                    experienceGroups,
                    projectState,
                    cardPadding,
                    uriHandler,
                    wide = true
                )
            }
        }
    } else {
        val showTopBar by remember { derivedStateOf { listState.firstVisibleItemIndex > 1 } }

        Scaffold(
            topBar = {
                AnimatedVisibility(
                    showTopBar,
                    enter = slideInVertically(motionScheme.slowSpatialSpec(), initialOffsetY = { -it }),
                    exit = slideOutVertically(motionScheme.slowSpatialSpec(), targetOffsetY = { -it })
                ) {
                    Box(
                        Modifier
                            .fillMaxWidth()
                            .height(64.dp)
                            .background(colorScheme.surfaceContainer)
                    ) {
                        ScrollSyncedTitle(
                            listState = listState,
                            titles = listOf("About", "Experience", "Projects"),
                            sectionStartIndices = listOf(
                                paragraphCount + 2,
                                paragraphCount + experienceCount + 3
                            ),
                            modifier = Modifier.fillMaxSize().padding(start = cardPadding + 8.dp)
                        )
                    }
                }
            }
        ) { innerPadding ->
            val layoutDirection = LocalLayoutDirection.current
            LazyColumn(
                state = listState,
                contentPadding = PaddingValues(
                    top = 48.dp,
                    start = innerPadding.calculateStartPadding(layoutDirection),
                    end = innerPadding.calculateEndPadding(layoutDirection),
                    bottom = 48.dp
                ),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                item { NameAndDesc(horizontalPadding = 16.dp) }
                item { SocialIcons(Modifier.padding(top = 32.dp, start = 12.dp, end = 12.dp)) }
                mainContent(
                    paragraphs,
                    experienceGroups,
                    projectState,
                    cardPadding,
                    uriHandler,
                    wide = false,
                    topPadding = 96.dp
                )
            }
        }
    }
}

@Composable
private fun ScrollSyncedTitle(
    listState: LazyListState,
    titles: List<String>,
    sectionStartIndices: List<Int>,
    modifier: Modifier = Modifier
) {
    val bandPx = with(LocalDensity.current) { 56.dp.toPx() }

    val position by remember(listState, sectionStartIndices, bandPx) {
        derivedStateOf {
            val layoutInfo = listState.layoutInfo
            sectionStartIndices.fold(0f) { acc, index ->
                val item = layoutInfo.visibleItemsInfo.firstOrNull { it.index == index }
                acc + if (item == null) {
                    if (listState.firstVisibleItemIndex > index) 1f else 0f
                } else {
                    val top = (item.offset - layoutInfo.viewportStartOffset).toFloat()
                    ((bandPx - top) / bandPx).coerceIn(0f, 1f)
                }
            }
        }
    }

    BoxWithConstraints(modifier.clipToBounds()) {
        // Slide by the full height of the bar so titles enter and leave at its very edges
        val slideDistance = constraints.maxHeight.toFloat()
        titles.forEachIndexed { index, title ->
            Text(
                title,
                style = typography.titleLarge,
                color = colorScheme.onSurface,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .graphicsLayer {
                        val distance = index - position
                        translationY = distance * slideDistance
                        alpha = (1f - abs(distance)).coerceIn(0f, 1f)
                    }
            )
        }
    }
}
