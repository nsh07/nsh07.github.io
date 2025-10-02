package org.nsh07.nsh07.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nsh07.composeapp.generated.resources.*
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.nsh07.nsh07.ui.theme.Nsh07Theme

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    var selectedItem by remember { mutableStateOf(0) }
    Row(
        modifier = Modifier
            .padding(start = 32.dp, end = 48.dp)
            .widthIn(max = 1200.dp)
            .then(modifier)
    ) {
        Column(Modifier.padding(vertical = 96.dp).weight(1f)) {
            Text(
                "Nishant Mishra",
                style = typography.displayLarge.copy(fontSize = 48.sp),
                color = colorScheme.onSurface,
                modifier = Modifier.padding(start = 20.dp)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                "Mobile app developer",
                style = typography.titleLarge,
                color = colorScheme.onSurface,
                modifier = Modifier.padding(start = 20.dp)
            )
            Spacer(Modifier.height(20.dp))
            Text(
                "I build performant, beautiful apps for mobile phones.",
                style = typography.bodyLarge,
                color = colorScheme.onSurfaceVariant,
                modifier = Modifier.padding(start = 20.dp)
            )

            Spacer(Modifier.height(72.dp))

            NavigationItem(
                selected = selectedItem == 0,
                onClick = { selectedItem = 0 },
                label = { Text("About", style = typography.bodyMedium) },
            )
            NavigationItem(
                selected = selectedItem == 1,
                onClick = { selectedItem = 1 },
                label = { Text("Experience", style = typography.bodyMedium) },
            )
            NavigationItem(
                selected = selectedItem == 2,
                onClick = { selectedItem = 2 },
                label = { Text("Projects", style = typography.bodyMedium) },
            )

            Spacer(Modifier.weight(1f))

            Spacer(Modifier.padding(top = 32.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(start = 20.dp)
            ) {
                IconButton(onClick = {}) {
                    Icon(
                        painterResource(Res.drawable.github),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painterResource(Res.drawable.gitlab),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painterResource(Res.drawable.linkedin),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                IconButton(onClick = {}) {
                    Icon(
                        painterResource(Res.drawable.email),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }

        Spacer(Modifier.width(16.dp))

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxHeight().padding(vertical = 96.dp).weight(1f)
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
}

@Preview(widthDp = 1920, heightDp = 1080)
@Composable
fun AppScreenPreview() {
    Nsh07Theme {
        AppScreen()
    }
}
