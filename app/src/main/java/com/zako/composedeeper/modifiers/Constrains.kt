package com.zako.composedeeper.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * At a high level, the algorithm works in the following way:
 *
 * 1. To decide the size it actually wants to occupy, the root node in the UI tree measures its children and forwards the same constraints to its first child.
 * 2. If the child is a modifier that does not impact measurement, it forwards the constraints to the next modifier. The constraints are passed down the modifier chain as-is unless a modifier that impacts measurement is reached. The constraints are then re-sized accordingly.
 * 3. Once a node is reached that doesn't have any children (referred to as a "leaf node"), it decides its size based on the constraints that were passed in, and returns this resolved size to its parent.
 * 4. The parent adapts its constraints based on this child's measurements, and **calls its next child with these adjusted constraints**.
 * 5. Once all children of a parent are measured, the parent node decides on its own size and communicates that to its own parent.
 * 6. This way, the whole tree is traversed depth-first. Eventually, all the nodes have decided on their sizes, and the measurement step is completed.
 *
 */

@Composable
fun Constrains1(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .widthIn(50.dp, 300.dp)
            .heightIn(50.dp, 200.dp)

    ) {
         Row (
             Modifier
                 .fillMaxSize()
                 .background(Color.Red)
                 .size(25.dp) // this will not work at all as it is less then the minim constraints set by the fill max size modifier ( 300 by 200 )



        ){

        }
    }
}


@Composable
fun Constrains2(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .widthIn(50.dp, 300.dp)
            .heightIn(50.dp, 200.dp)

    ) {
        Row (
            Modifier
                .fillMaxSize()
                .padding()
                .wrapContentSize() // Allow the content to measure at its desired size without regard for the incoming measurement minimum width or minimum height constraints
                .background(Color.Yellow)
                .size(50.dp) // this will work now



        ){

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Prev() {
Column {
    Constrains1()
    Constrains2()
}

}