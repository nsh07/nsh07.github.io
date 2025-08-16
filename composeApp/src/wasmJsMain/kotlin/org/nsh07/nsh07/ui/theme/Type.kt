package org.nsh07.nsh07.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import nsh07.composeapp.generated.resources.Res
import nsh07.composeapp.generated.resources.inter_variable
import org.jetbrains.compose.resources.Font

// Default Material 3 typography values
val baseline = Typography()

@Composable
fun AppTypography() = Typography().run {
    val fontFamily = FontFamily(
        Font(Res.font.inter_variable, weight = FontWeight.Light),
        Font(Res.font.inter_variable, weight = FontWeight.Normal),
        Font(Res.font.inter_variable, weight = FontWeight.Medium),
        Font(Res.font.inter_variable, weight = FontWeight.SemiBold),
        Font(Res.font.inter_variable, weight = FontWeight.Bold),
    )

    copy(
        displayLarge = baseline.displayLarge.copy(fontFamily = fontFamily),
        displayMedium = baseline.displayMedium.copy(fontFamily = fontFamily),
        displaySmall = baseline.displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = baseline.headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = baseline.headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = baseline.headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = baseline.titleLarge.copy(fontFamily = fontFamily),
        titleMedium = baseline.titleMedium.copy(fontFamily = fontFamily),
        titleSmall = baseline.titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = baseline.bodyLarge.copy(fontFamily = fontFamily),
        bodyMedium = baseline.bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = baseline.bodySmall.copy(fontFamily = fontFamily),
        labelLarge = baseline.labelLarge.copy(fontFamily = fontFamily),
        labelMedium = baseline.labelMedium.copy(fontFamily = fontFamily),
        labelSmall = baseline.labelSmall.copy(fontFamily = fontFamily)
    )
}