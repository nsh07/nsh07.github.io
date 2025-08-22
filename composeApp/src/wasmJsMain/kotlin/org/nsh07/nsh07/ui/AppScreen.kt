package org.nsh07.nsh07.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.WideNavigationRail
import androidx.compose.material3.WideNavigationRailItem
import androidx.compose.material3.WideNavigationRailState
import androidx.compose.material3.WideNavigationRailValue
import androidx.compose.material3.rememberWideNavigationRailState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.util.fastForEach
import nsh07.composeapp.generated.resources.Res
import nsh07.composeapp.generated.resources.filled_home
import nsh07.composeapp.generated.resources.filled_info
import nsh07.composeapp.generated.resources.outline_home
import nsh07.composeapp.generated.resources.outline_info
import org.jetbrains.compose.resources.painterResource
import org.nsh07.nsh07.ui.about.AboutScreen
import org.nsh07.nsh07.ui.home.HomeScreen

@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    val state: WideNavigationRailState =
        rememberWideNavigationRailState(WideNavigationRailValue.Expanded)

    var currentPortfolioScreen by remember { mutableStateOf(PortfolioScreen.HOME) }

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
        WideNavigationRail(state = state) {
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

        AnimatedContent(currentPortfolioScreen) {
            when (it) {
                PortfolioScreen.HOME -> HomeScreen()
                PortfolioScreen.ABOUT -> AboutScreen()
                PortfolioScreen.PROJECTS -> {}
            }
        }
    }
}
