package org.nsh07.nsh07.ui

import org.jetbrains.compose.resources.DrawableResource

enum class PortfolioScreen {
    HOME, ABOUT, PROJECTS
}

data class RailItem(
    val name: String,
    val unselectedIcon: DrawableResource,
    val selectedIcon: DrawableResource,
    val portfolioScreen: PortfolioScreen
)