package org.nsh07.nsh07.ui.homeScreen

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.motionScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.TopAppBarDefaults.pinnedScrollBehavior
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import androidx.window.core.layout.WindowSizeClass
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.hazeEffect
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalMaterial3Api::class)
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
                description = "Perform the role of Open Source Lead of the Development Club. Successfully organised OPCODE (Open Source Fest) 2025, while also contributing a project.",
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
                    experiences,
                    projectState,
                    cardPadding,
                    uriHandler,
                    wide = true
                )
            }
        }
    } else {
        val scrolledUp by derivedStateOf { listState.lastScrolledForward }

        val showTopBar by remember { derivedStateOf { listState.firstVisibleItemIndex > 1 } }
        val experienceSectionVisible by remember { derivedStateOf { listState.firstVisibleItemIndex in paragraphCount + 2..<paragraphCount + experienceCount + 3 } }
        val projectsSectionsVisible by remember { derivedStateOf { listState.firstVisibleItemIndex in paragraphCount + experienceCount + 3..1000 } }

        val scrollBehavior = pinnedScrollBehavior()
        val hazeState = rememberHazeState(true)
        val topAppBarColors = topAppBarColors(
            containerColor = colorScheme.surface,
            scrolledContainerColor = colorScheme.surface
        )

        Scaffold(
            topBar = {
                AnimatedVisibility(
                    showTopBar,
                    enter = slideInVertically(motionScheme.slowSpatialSpec(), initialOffsetY = { -it }),
                    exit = slideOutVertically(motionScheme.slowSpatialSpec(), targetOffsetY = { -it })
                ) {
                    val topBarContent = when {
                        experienceSectionVisible -> 1
                        projectsSectionsVisible -> 2
                        else -> 0
                    }
                    TopAppBar(
                        title = {
                            AnimatedContent(
                                topBarContent,
                                transitionSpec = {
                                    slideInVertically(
                                        animationSpec = motionScheme.fastSpatialSpec(),
                                        initialOffsetY = {
                                            if (scrolledUp) (it * 1.25).toInt() else (-it * 1.25).toInt()
                                        }
                                    ).togetherWith(
                                        slideOutVertically(
                                            animationSpec = motionScheme.fastSpatialSpec(),
                                            targetOffsetY = {
                                                if (scrolledUp) (-it * 1.25).toInt() else (it * 1.25).toInt()
                                            }
                                        )
                                    )
                                },
                                modifier = Modifier.width(200.dp).padding(start = 8.dp)
                            ) {
                                when (it) {
                                    1 -> Text("Experience")
                                    2 -> Text("Projects")
                                    else -> Text("About")
                                }
                            }
                        },
                        scrollBehavior = scrollBehavior,
                        colors = topAppBarColors.copy(
                            scrolledContainerColor = topAppBarColors.scrolledContainerColor.copy(
                                0.7f
                            )
                        ),
                        modifier = Modifier
                            .hazeEffect(
                                hazeState,
                                style = HazeStyle(
                                    backgroundColor = colorScheme.surface,
                                    tint = null,
                                    blurRadius = 20.dp,
                                    noiseFactor = 0f
                                )
                            )
                    )
                }
            },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
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
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).hazeSource(hazeState)
            ) {
                item { NameAndDesc(horizontalPadding = 16.dp) }
                item { SocialIcons(Modifier.padding(top = 32.dp, start = 12.dp, end = 12.dp)) }
                mainContent(
                    paragraphs,
                    experiences,
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