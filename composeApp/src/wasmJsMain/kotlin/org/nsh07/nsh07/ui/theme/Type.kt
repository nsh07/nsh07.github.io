package org.nsh07.nsh07.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontFamily

import nsh07.composeapp.generated.resources.Res
import nsh07.composeapp.generated.resources.google_sans_flex_400
import nsh07.composeapp.generated.resources.google_sans_flex_600
import org.jetbrains.compose.resources.Font

// Default Material 3 typography values
val baseline = Typography()

val AppTypography
    @Composable get() = Typography().run {
        val bodyFontFamily = FontFamily(Font(Res.font.google_sans_flex_400))

        val displayFontFamily = FontFamily(Font(Res.font.google_sans_flex_600))

        copy(
            displayLarge = baseline.displayLarge.copy(
                fontFamily = displayFontFamily,
                fontFeatureSettings = "ss02, dlig"
            ),
            displayMedium = baseline.displayMedium.copy(
                fontFamily = displayFontFamily,
                fontFeatureSettings = "ss02, dlig"
            ),
            displaySmall = baseline.displaySmall.copy(
                fontFamily = displayFontFamily,
                fontFeatureSettings = "ss02, dlig"
            ),
            headlineLarge = baseline.headlineLarge.copy(
                fontFamily = displayFontFamily,
                fontFeatureSettings = "ss02, dlig"
            ),
            headlineMedium = baseline.headlineMedium.copy(
                fontFamily = displayFontFamily,
                fontFeatureSettings = "ss02, dlig"
            ),
            headlineSmall = baseline.headlineSmall.copy(
                fontFamily = displayFontFamily,
                fontFeatureSettings = "ss02, dlig"
            ),
            titleLarge = baseline.titleLarge.copy(fontFamily = displayFontFamily, fontFeatureSettings = "ss02, dlig"),
            titleMedium = baseline.titleMedium.copy(fontFamily = displayFontFamily, fontFeatureSettings = "ss02, dlig"),
            titleSmall = baseline.titleSmall.copy(fontFamily = displayFontFamily, fontFeatureSettings = "ss02, dlig"),
            bodyLarge = baseline.bodyLarge.copy(fontFamily = displayFontFamily, fontFeatureSettings = "ss02, dlig"),
            bodyMedium = baseline.bodyMedium.copy(fontFamily = bodyFontFamily, fontFeatureSettings = "ss02, dlig"),
            bodySmall = baseline.bodySmall.copy(fontFamily = bodyFontFamily, fontFeatureSettings = "ss02, dlig"),
            labelLarge = baseline.labelLarge.copy(fontFamily = bodyFontFamily, fontFeatureSettings = "ss02, dlig"),
            labelMedium = baseline.labelMedium.copy(fontFamily = bodyFontFamily, fontFeatureSettings = "ss02, dlig"),
            labelSmall = baseline.labelSmall.copy(fontFamily = bodyFontFamily, fontFeatureSettings = "ss02, dlig")
        )
    }