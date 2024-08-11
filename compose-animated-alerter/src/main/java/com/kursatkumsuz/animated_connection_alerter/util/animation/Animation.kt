package com.kursatkumsuz.animated_connection_alerter.util.animation

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset

@Composable
fun animateMessageBarColor(isVisible: Boolean, messageBarBackgroundColorSuccess: Color, messageBarBackgroundColorError: Color): State<Color> {
    return animateColorAsState(
        targetValue = if (isVisible) messageBarBackgroundColorError else messageBarBackgroundColorSuccess,
        animationSpec = tween(durationMillis = 500), label = ""
    )
}

fun noInternetCardSlideInAnimation() = slideInVertically(
    spring(
        dampingRatio = Spring.DampingRatioHighBouncy,
        stiffness = Spring.StiffnessLow
    )
)

fun noInternetCardSlideOutAnimation() = slideOutVertically(
    targetOffsetY = { it },
    animationSpec = keyframes {
        durationMillis = 500
        IntOffset(0, -200) atFraction 0.5f using EaseOut
        IntOffset(0, 0) at 100
    }
)