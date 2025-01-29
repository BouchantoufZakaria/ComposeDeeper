package com.zako.composedeeper.constumLayout

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout

fun Modifier.paddingFromFirstBaseLine() = layout { measurable, constraints ->
    val placable = measurable.measure(constraints)

    // Check if the composable has a first baseline
    check(placable[FirstBaseline] != AlignmentLine.Unspecified) {
        "The composable being padded must provide a first baseline"
    }

    layout(placable.width, placable.height) {
        val firstBaseLine = placable[FirstBaseline]
        placable.placeRelative(0, firstBaseLine)
    }
}