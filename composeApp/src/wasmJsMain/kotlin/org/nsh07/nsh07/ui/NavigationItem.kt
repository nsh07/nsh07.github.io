package org.nsh07.nsh07.ui

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NavigationItem(
    selected: Boolean,
    onClick: () -> Unit,
    label: @Composable (() -> Unit)?,
    modifier: Modifier = Modifier,
    railExpanded: Boolean = true,
    enabled: Boolean = true,
    iconPosition: NavigationItemIconPosition = WideNavigationRailItemDefaults.iconPositionFor(railExpanded),
    colors: NavigationItemColors = WideNavigationRailItemDefaults.colors(),
    interactionSource: MutableInteractionSource? = null
) {
    val lineWidth by animateDpAsState(if (selected) 64.dp else 32.dp)

    WideNavigationRailItem(
        selected = selected,
        onClick = onClick,
        icon = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.height(24.dp)
            ) {
                HorizontalDivider(modifier = Modifier.width(lineWidth), color = colors.iconColor(selected, enabled))
            }
        },
        label = label,
        railExpanded = railExpanded,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = colors,
        iconPosition = iconPosition
    )
}