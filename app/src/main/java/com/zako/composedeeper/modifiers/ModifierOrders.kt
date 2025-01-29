package com.zako.composedeeper.modifiers

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.zako.composedeeper.R
/**
 * Adding more than one modifier to a composable creates a chain of modifiers. When you chain multiple modifiers, each modifier node wraps the rest of the chain and the layout node within. For example, when you chain a clip and a size modifier, the clip modifier node wraps the size modifier node, which then wraps the Image layout node.
 *
 * In the layout phase, the algorithm that walks the tree stays the same, but each modifier node is visited as well. This way, a modifier can change the size requirements and placement of the modifier or layout node that it wraps.
 *
 * size
 *  blue background
 *   clip
 *    red background
 *     image
 *
 * and this the the reasone why we have no `margin` modifier in compose
 */

@Composable
fun ModifiersChain(modifier: Modifier = Modifier) {
    Image(
        // here the image background will not be clipead and this is the trick
        modifier = Modifier
            .background(Color.Red)
            .clip(CircleShape)
            .background(Color.Blue)
            .size(100.dp)

        ,
        contentDescription = "",
        painter = painterResource(R.drawable.ic_launcher_foreground)
    )
}


@Preview(showBackground = true )
@Composable
private fun Prev() {
    ModifiersChain()
}
