package com.zako.composedeeper.modifiers

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


/**
 * those data here are not accessed from the box scope
 *
 *  if you want a child layout to be the same size as a parent Box without affecting the `Box` size, use the `matchParentSize` modifier.
 */

@Composable
fun ScopedModifiers() {

    Box (
        Modifier.size(200.dp).background(Color.White)
    ){
        Text(
            modifier = Modifier.matchParentSize().background(Color.Red) ,
            text = "this text  red  will fill the parent ( white ) "
        )
        Text(
            modifier = Modifier.align(Alignment.BottomEnd).background(Color.Blue) ,
            text = " bottom end  "
        )
    }
}

@Preview(showBackground = true )
@Composable
private fun Preview() {
    ScopedModifiers()
}