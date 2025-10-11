package org.nsh07.nsh07.ui.homeScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
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
        Column(Modifier.padding(vertical = 96.dp).weight(1f)) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                NameAndDesc()

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
                uriHandler
            )
        }
    }
}