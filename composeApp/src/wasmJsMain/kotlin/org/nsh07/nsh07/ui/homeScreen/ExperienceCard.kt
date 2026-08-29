package org.nsh07.nsh07.ui.homeScreen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.shapes
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.util.fastForEach
import androidx.compose.ui.util.fastForEachIndexed
import nsh07.composeapp.generated.resources.Res
import nsh07.composeapp.generated.resources.open_in_browser
import org.jetbrains.compose.resources.painterResource
import kotlin.math.abs
import kotlin.text.Typography.bullet
import kotlin.text.Typography.mdash
import kotlin.text.Typography.nbsp

/** Width of the gutter holding the timeline dot and the line linking grouped roles. */
private val connectorWidth = 24.dp
private val connectorDotRadius = 4.dp
private val connectorStrokeWidth = 2.dp

/** Gap left between the line and the dot it runs to at either end. */
private val connectorDotGap = 4.dp

/** Top padding of the duration label, which the timeline dot is centred against. */
private val durationTopPadding = 4.dp

@OptIn(ExperimentalMaterial3ExpressiveApi::class)
@Composable
fun ExperienceCard(
    experiences: List<Experience>,
    cardPadding: Dp,
    wide: Boolean,
    modifier: Modifier = Modifier
) {
    val uriHandler = LocalUriHandler.current
    val company = experiences.first()

    Box(
        modifier
            .clip(shapes.largeIncreased)
            .clickable { uriHandler.openUri(company.companyUrl) }
    ) {
        if (experiences.size == 1) {
            if (wide) {
                Row(Modifier.fillMaxWidth().padding(cardPadding)) {
                    ExperienceCardDurationText(company, Modifier.weight(1f))
                    ExperienceCardMainContent(company, modifier = Modifier.weight(3f))
                }
            } else {
                Column(Modifier.fillMaxWidth().padding(cardPadding)) {
                    ExperienceCardDurationText(company)
                    ExperienceCardMainContent(company)
                }
            }
        } else {
            Column(Modifier.fillMaxWidth().padding(cardPadding)) {
                ExperienceCardCompanyHeader(company, wide)
                experiences.fastForEachIndexed { index, experience ->
                    ExperienceCardLinkedRole(
                        experience = experience,
                        wide = wide,
                        last = index == experiences.lastIndex
                    )
                }
            }
        }
    }
}

/**
 * The company name heading a multi-role card. On [wide] layouts it is indented past the timeline
 * gutter and the duration column so that it lines up with the roles' main content.
 */
@Composable
fun ExperienceCardCompanyHeader(
    experience: Experience,
    wide: Boolean,
    modifier: Modifier = Modifier
) {
    Row(Modifier.fillMaxWidth().padding(bottom = 16.dp).then(modifier)) {
        Spacer(Modifier.width(connectorWidth))
        if (wide) Spacer(Modifier.weight(1f))
        FlowRow(
            itemVerticalAlignment = Alignment.CenterVertically,
            modifier = if (wide) Modifier.weight(3f) else Modifier
        ) {
            Text(experience.company, style = typography.titleMedium)
            Icon(
                painterResource(Res.drawable.open_in_browser),
                null,
                modifier = Modifier.padding(start = 4.dp).size(16.dp)
            )
        }
    }
}

/**
 * A single role within a multi-role company card, preceded by a gutter holding its timeline dot
 * and the line running down to the next role.
 */
@Composable
fun ExperienceCardLinkedRole(
    experience: Experience,
    wide: Boolean,
    last: Boolean,
    modifier: Modifier = Modifier
) {
    val dotColor = colorScheme.outline
    val dotColorInactive = colorScheme.outlineVariant
    val lineColor = colorScheme.outline
    val lineColorInactive = colorScheme.outlineVariant
    val labelLineHeight = typography.labelMedium.lineHeight

    val fraction = remember { Animatable(if (last) 1f else 0f) }

    LaunchedEffect(Unit) {
        if (!last) fraction.animateTo(1f, tween(1000))
    }

    Row(
        Modifier
            .fillMaxWidth()
            .drawWithCache {
                val x = connectorWidth.toPx() / 2f
                val lineHeight =
                    if (labelLineHeight.isSpecified) labelLineHeight.toPx() else 16.dp.toPx()
                val dotCenterY = durationTopPadding.toPx() + lineHeight / 2f

                val linePath = Path().apply {
                    moveTo(
                        x,
                        size.height + dotCenterY - connectorDotRadius.toPx() - connectorDotGap.toPx()
                    )
                    lineTo(x, dotCenterY + connectorDotRadius.toPx() + connectorDotGap.toPx())
                }
                val pathLength = abs(
                    dotCenterY + connectorDotRadius.toPx() + connectorDotGap.toPx() -
                            (size.height + dotCenterY - connectorDotRadius.toPx() - connectorDotGap.toPx())
                )

                onDrawBehind {
                    val fraction = fraction.value
                    drawCircle(
                        color = if (fraction != 1f) dotColorInactive else dotColor,
                        radius = connectorDotRadius.toPx(),
                        center = Offset(x, dotCenterY)
                    )
                    if (!last)
                        drawPath(
                            path = linePath,
                            color = if (fraction != 1f) lineColorInactive else lineColor,
                            style = Stroke(
                                width = connectorStrokeWidth.toPx(),
                                cap = StrokeCap.Round,
                                pathEffect = PathEffect.dashPathEffect(
                                    intervals = floatArrayOf(
                                        pathLength * fraction,
                                        pathLength
                                    )
                                )
                            )
                        )
                }
            }
            .padding(bottom = if (last) 0.dp else 24.dp)
            .then(modifier)
    ) {
        Spacer(Modifier.width(connectorWidth))
        if (wide) {
            ExperienceCardDurationText(experience, Modifier.weight(1f))
            ExperienceCardMainContent(
                experience,
                showCompany = false,
                modifier = Modifier.weight(3f)
            )
        } else {
            Column(Modifier.weight(1f)) {
                ExperienceCardDurationText(experience)
                ExperienceCardMainContent(experience, showCompany = false)
            }
        }
    }
}

@Composable
fun ExperienceCardDurationText(experience: Experience, modifier: Modifier = Modifier) {
    Column(
        Modifier.padding(top = durationTopPadding, bottom = 8.dp, end = 16.dp).then(modifier)
    ) {
        Text(
            remember {
                "${experience.start.replace(' ', nbsp)} $mdash ${
                    experience.end.replace(
                        ' ',
                        nbsp
                    )
                }".toUpperCase(Locale.current)
            },
            style = typography.labelMedium,
            color = colorScheme.outline
        )
        if (experience.location != null) Text(
            experience.location,
            style = typography.labelMedium,
            color = colorScheme.outline,
            modifier = Modifier.padding(top = 2.dp)
        )
    }
}

@Composable
fun ExperienceCardMainContent(
    experience: Experience,
    showCompany: Boolean = true,
    modifier: Modifier = Modifier
) {
    Column(modifier) {
        FlowRow(itemVerticalAlignment = Alignment.CenterVertically) {
            Text(experience.position, style = typography.bodyLarge)
            if (showCompany) {
                Text(" $bullet ", style = typography.bodyLarge)
                Text(experience.company, style = typography.bodyLarge)
                Icon(
                    painterResource(Res.drawable.open_in_browser),
                    null,
                    modifier = Modifier.padding(start = 4.dp).size(16.dp)
                )
            }
        }
        if (experience.description.isNotEmpty()) Text(
            experience.description,
            style = typography.bodyMedium,
            color = colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(top = 8.dp)
        )
        if (experience.skills.isNotEmpty()) LabelRow(
            experience.skills,
            Modifier.padding(top = 16.dp)
        )
    }
}

@Composable
fun LabelRow(list: List<String>, modifier: Modifier = Modifier) {
    FlowRow(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        list.fastForEach {
            Box(Modifier.clip(CircleShape).background(colorScheme.secondaryContainer)) {
                Text(
                    it,
                    style = typography.labelMedium,
                    color = colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(vertical = 6.dp, horizontal = 12.dp)
                )
            }
        }
    }
}

data class Experience(
    val start: String,
    val end: String,
    val position: String,
    val description: String,
    val company: String,
    val companyUrl: String,
    val skills: List<String>,
    val location: String? = null
)

/**
 * Groups consecutive roles at the same company into a single card's worth of experiences.
 */
fun List<Experience>.groupByCompany(): List<List<Experience>> =
    fold(mutableListOf<MutableList<Experience>>()) { acc, experience ->
        val last = acc.lastOrNull()
        if (last != null && last.first().companyUrl == experience.companyUrl) last.add(experience)
        else acc.add(mutableListOf(experience))
        acc
    }
