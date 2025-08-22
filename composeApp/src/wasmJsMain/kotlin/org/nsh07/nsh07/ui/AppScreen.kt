package org.nsh07.nsh07.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.ModalWideNavigationRail
import androidx.compose.material3.Text
import androidx.compose.material3.WideNavigationRail
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import kotlinx.coroutines.launch
import nsh07.composeapp.generated.resources.Res
import nsh07.composeapp.generated.resources.filled_home
import nsh07.composeapp.generated.resources.filled_info
import nsh07.composeapp.generated.resources.menu
import nsh07.composeapp.generated.resources.menu_open
import nsh07.composeapp.generated.resources.outline_home
import nsh07.composeapp.generated.resources.outline_info
import org.jetbrains.compose.resources.painterResource
import org.nsh07.nsh07.ui.about.AboutScreen
import org.nsh07.nsh07.ui.home.HomeScreen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    var currentPortfolioScreen by remember { mutableStateOf(PortfolioScreen.HOME) }

    val windowSizeClass = calculateWindowSizeClass()
    val state = rememberWideNavigationRailState(WideNavigationRailValue.Collapsed)
    val scope = rememberCoroutineScope()

    val railItems = remember {
        listOf(
            RailItem(
                "Home",
                Res.drawable.outline_home,
                Res.drawable.filled_home,
                PortfolioScreen.HOME
            ),
            RailItem(
                "About me",
                Res.drawable.outline_info,
                Res.drawable.filled_info,
                PortfolioScreen.ABOUT
            ),
        )
    }

    Row(modifier.fillMaxWidth()) {
        when (windowSizeClass.widthSizeClass) {
            WindowWidthSizeClass.Expanded -> {
                LaunchedEffect(Unit) {
                    state.expand()
                }
                WideNavigationRail(
                    state = state
                ) {
                    railItems.fastForEach {
                        val selected = it.portfolioScreen == currentPortfolioScreen
                        WideNavigationRailItem(
                            selected = selected,
                            onClick = { currentPortfolioScreen = it.portfolioScreen },
                            icon = {
                                Crossfade(selected) { targetState ->
                                    when (targetState) {
                                        true -> Icon(painterResource(it.selectedIcon), null)
                                        else -> Icon(painterResource(it.unselectedIcon), null)
                                    }
                                }
                            },
                            label = { Text(it.name, style = typography.labelLarge) },
                            railExpanded = state.targetValue == WideNavigationRailValue.Expanded
                        )
                    }
                }
            }

            WindowWidthSizeClass.Medium -> {
                WideNavigationRail(
                    state = state,
                    header = {
                        val rotation by animateFloatAsState(if (state.targetValue == WideNavigationRailValue.Expanded) 0f else 180f)
                        IconButton(
                            modifier =
                                Modifier
                                    .padding(start = 24.dp)
                                    .rotate(rotation)
                                    .semantics {
                                        stateDescription =
                                            if (state.currentValue == WideNavigationRailValue.Expanded) {
                                                "Expanded"
                                            } else {
                                                "Collapsed"
                                            }
                                    },
                            onClick = {
                                scope.launch {
                                    if (state.targetValue == WideNavigationRailValue.Expanded)
                                        state.collapse()
                                    else state.expand()
                                }
                            },
                        ) {
                            Crossfade(state.targetValue) {
                                when (it) {
                                    WideNavigationRailValue.Expanded -> Icon(
                                        painterResource(Res.drawable.menu_open),
                                        "Collapse rail"
                                    )

                                    WideNavigationRailValue.Collapsed -> Icon(
                                        painterResource(Res.drawable.menu),
                                        "Expand rail"
                                    )
                                }
                            }
                        }
                    }
                ) {
                    railItems.fastForEach {
                        val selected = it.portfolioScreen == currentPortfolioScreen
                        WideNavigationRailItem(
                            selected = selected,
                            onClick = {
                                scope.launch {
                                    currentPortfolioScreen = it.portfolioScreen
                                    state.collapse()
                                }
                            },
                            icon = {
                                Crossfade(selected) { targetState ->
                                    when (targetState) {
                                        true -> Icon(painterResource(it.selectedIcon), null)
                                        else -> Icon(painterResource(it.unselectedIcon), null)
                                    }
                                }
                            },
                            label = { Text(it.name, style = typography.labelLarge) },
                            railExpanded = state.targetValue == WideNavigationRailValue.Expanded
                        )
                    }
                }
            }

            WindowWidthSizeClass.Compact -> {
                ModalWideNavigationRail(
                    state = state,
                    hideOnCollapse = true
                ) {
                    railItems.fastForEach {
                        val selected = it.portfolioScreen == currentPortfolioScreen
                        WideNavigationRailItem(
                            selected = selected,
                            onClick = {
                                scope.launch {
                                    currentPortfolioScreen = it.portfolioScreen
                                    state.collapse()
                                }
                            },
                            icon = {
                                Crossfade(selected) { targetState ->
                                    when (targetState) {
                                        true -> Icon(painterResource(it.selectedIcon), null)
                                        else -> Icon(painterResource(it.unselectedIcon), null)
                                    }
                                }
                            },
                            label = { Text(it.name, style = typography.labelLarge) },
                            railExpanded = state.targetValue == WideNavigationRailValue.Expanded
                        )
                    }
                }
            }
        }

        AnimatedContent(
            currentPortfolioScreen,
            transitionSpec = { fadeIn().togetherWith(fadeOut()) }
        ) {
            when (it) {
                PortfolioScreen.HOME -> HomeScreen(
                    expandRail = { scope.launch { state.expand() } },
                    windowWidthClass = windowSizeClass.widthSizeClass
                )

                PortfolioScreen.ABOUT -> AboutScreen(
                    expandRail = { scope.launch { state.expand() } },
                    windowWidthClass = windowSizeClass.widthSizeClass
                )

                PortfolioScreen.PROJECTS -> {}
            }
        }
    }
}
