package org.nsh07.nsh07.ui.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nsh07.composeapp.generated.resources.Res
import nsh07.composeapp.generated.resources.menu
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(
    expandRail: () -> Unit,
    windowWidthClass: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    if (windowWidthClass == WindowWidthSizeClass.Compact) {
                        IconButton(onClick = expandRail) {
                            Icon(painterResource(Res.drawable.menu), "Open menu")
                        }
                    }
                },
                title = {}
            )
        },
        modifier = modifier.fillMaxSize()
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxHeight()
            ) {
                CircularProgressIndicator(trackColor = colorScheme.secondaryContainer)
                Spacer(Modifier.height(16.dp))
                Text(
                    "Coming Soon...",
                    fontSize = 64.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 68.sp
                )
            }
        }
    }
}