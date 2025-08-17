package org.nsh07.nsh07

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularWavyProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.nsh07.nsh07.ui.theme.Nsh07Theme

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun App() {
    Nsh07Theme {
        Scaffold {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier.fillMaxHeight()
                ) {
                    item {
                        CircularWavyProgressIndicator()
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
    }
}