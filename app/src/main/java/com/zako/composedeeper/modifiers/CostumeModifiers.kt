package com.zako.composedeeper.modifiers


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.ContentDrawScope
import androidx.compose.ui.node.DrawModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.drawText
import androidx.compose.ui.unit.dp

// combination of modifiers
fun Modifier.clip(shape: Shape) = graphicsLayer(shape = shape, clip = true)

// compose factory
/**
 * When creating a custom modifier using a composable modifier factory, composition locals take the value from the composition tree where they are created, not used. This can lead to unexpected results
 *
 * Composable factory modifiers are never skipped because composable functions that have return values cannot be skipped. This means your modifier function will be called on every recomposition, which may be expensive if it recomposes frequently.
 *
 */
@Composable
fun Modifier.fade(enable: Boolean): Modifier {
     var  modifier: Modifier? = null

    CompositionLocalProvider(LocalContentColor provides Color.Red ) {
        val color = LocalContentColor.current
        val alpha by animateFloatAsState(if (enable) 0.5f else 1.0f, label = "")
         modifier = this then Modifier
            .background(color = color)
            .graphicsLayer { this.alpha = alpha }
    }

    return modifier!!

}

// the lowes level composable Node

class Badge(var  color : Color , val number: Int ) : DrawModifierNode , Modifier.Node() {
    override fun ContentDrawScope.draw() {
        this.drawCircle(
            radius = 10f ,
            color = color ,
        )
    }
}
data class BadgeElement(val color : Color , val number: Int ) : ModifierNodeElement<Badge>() {
    override fun create(): Badge {
        return Badge(color , number)
    }

    override fun update(node: Badge) {
        node.color = color
    }
}

fun Modifier.badge() = this.then(BadgeElement(Color.Blue , 1))

@Preview(showBackground = true )
@Composable
private fun Prev() {

    CompositionLocalProvider(LocalContentColor provides Color.Blue ) {
        // here the background color will not be blue ( read the comment above)
        Column {
            Text(
                modifier = Modifier.clip(CircleShape).fade(true),
                text = "Hello, World!",
                color = Color.White
            )

            Box(
                Modifier.background(Color.Red).size(100.dp).badge(),
                contentAlignment = androidx.compose.ui.Alignment.Center
            ) {
                Text("centre text")
            }
        }
    }
}

