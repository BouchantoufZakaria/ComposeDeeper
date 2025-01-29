package com.zako.avatarcountdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * the styling data class of the [AvatarCountDownTimer] component
 * @param avatarTextStyle the compose text style of the avatar text
 * @param counterTextStyle the compose text style of the counter text
 * @param avatarBackgroundColor the background color of the avatar circle
 * @param counterBackgroundColor the background color of the counter text itself
 * @param counterIndicatorBackgroundColor the background color of the counter indicator ( the circle stroke color )
 * @param innerSpaceSize the space between the avatar circle and the counter indicator
 * @param counterStrokeSize the stroke size of the counter indicator ( the stroke width )
 * @param counterTextRounds the rounded corner of the counter text background
 * @param counterTextInnerPadding the padding of the counter text from it's background
 * @param counterIndicatorCap the cap of the counter indicator stroke , default is [StrokeCap.Round] , that's mean how the indicator look at the end of the arc
 *
 * @see AvatarCountDownTimerDefaults.default
 * @author Bouchentouf Zakaria
 *
 */
data class AvatarCountDownTimerStyling (
    val avatarTextStyle: TextStyle,
    val counterTextStyle : TextStyle,
    val avatarBackgroundColor: Color,
    val counterBackgroundColor: Color,
    val counterIndicatorBackgroundColor : Color,
    val innerSpaceSize : Dp,
    val counterStrokeSize : Float,
    val counterTextRounds : Dp,
    val counterTextInnerPadding : Dp,
    val counterIndicatorCap : StrokeCap
)


/**
 * the default styling of the [AvatarCountDownTimer] component
 */
object AvatarCountDownTimerDefaults {

    @androidx.compose.runtime.Composable
    fun default() = AvatarCountDownTimerStyling(
        avatarTextStyle = TextStyle(
            color = androidx.compose.material3.MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
        ),
        counterTextStyle =
        TextStyle(
            color = androidx.compose.material3.MaterialTheme.colorScheme.onPrimary,
        ),
        avatarBackgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.surfaceVariant ,
        counterBackgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.primary ,
        counterIndicatorBackgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.primary ,
        innerSpaceSize = 4.dp,
        counterStrokeSize = 6f ,
        counterTextRounds = 2.dp,
        counterTextInnerPadding  = 2.dp,
        counterIndicatorCap = StrokeCap.Round
    )
}

/**
 * the [AvatarCountDownTimer] component is a custom compose component that represent a circle avatar with a counter text and a counter percentage indicator over it
 * @param avatarText the text of the avatar circle , this text should not be more than 3 or 2 ( recommended ) characters
 * @param counterText the text of the counter text , this text should be a short text like "3 days" or "2 h"
 * @param percentage the percentage of the counter indicator , this should be a number between 0 and 100
 * @author Bouchentouf Zakaria
 * @see PreviewAvatarWithCounter
 */
@androidx.compose.runtime.Composable
fun AvatarCountDownTimer(
    modifier: Modifier = Modifier,
    avatarText : String,
    counterText : String,
    percentage : Int,
    style : AvatarCountDownTimerStyling = AvatarCountDownTimerDefaults.default()
) {

    var textHeight by androidx.compose.runtime.remember {
        androidx.compose.runtime.mutableStateOf(0.dp)
    }

    androidx.compose.foundation.layout.Box(
        modifier = modifier
    ) {

        androidx.compose.foundation.Canvas(
            modifier = Modifier.Companion
                .padding(textHeight)
                .fillMaxSize()
                .align(Alignment.Companion.Center)
        ) {
            // draw the  border fo the circle avatar
            drawArc(
                color = style.counterIndicatorBackgroundColor,
                startAngle = 90f,
                sweepAngle = -((360 * percentage) / 100).toFloat(),
                useCenter = false,
                style = Stroke(
                    width = style.counterStrokeSize,
                    cap = style.counterIndicatorCap
                )
            )
        }

        androidx.compose.foundation.layout.Column(
            modifier = Modifier.Companion
                .padding(textHeight + style.innerSpaceSize)
                .fillMaxSize()
                .clip(androidx.compose.foundation.shape.CircleShape)
                .background(style.avatarBackgroundColor),

            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
            horizontalAlignment = Alignment.Companion.CenterHorizontally
        ) {
            androidx.compose.material3.Text(
                text = avatarText,
                style = style.avatarTextStyle
            )
        }

        androidx.compose.material3.Text(
            modifier = Modifier.Companion
                .align(Alignment.Companion.BottomCenter)
                .clip(androidx.compose.foundation.shape.RoundedCornerShape(style.counterTextRounds))
                .background(style.counterBackgroundColor)
                .padding(style.counterTextInnerPadding)
                .onGloballyPositioned { coordinates ->
                    textHeight = coordinates.size.height.dp / 10
                },

            text = counterText,
            style = style.counterTextStyle
        )
    }
}


@Preview
@androidx.compose.runtime.Composable
private fun PreviewAvatarWithCounter() {
    AvatarCountDownTimer(
        modifier = Modifier.Companion.size(100.dp) ,
        avatarText = "B.Z" ,
        counterText = "3 days" ,
        percentage = 50
    )
}