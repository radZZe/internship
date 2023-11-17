package com.example.internship.ui.internship_catalog

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CategoryItem(
    text: String,
    isChecked: Boolean = false,
    onClick: () -> Unit?
) {
    AnimatedContent(targetState = isChecked,
        transitionSpec = {
            fadeIn(animationSpec = tween(durationMillis = 150)) with
                    fadeOut(animationSpec = tween(durationMillis = 150)) using
                    SizeTransform { initialSize, targetSize ->
                        if (targetState) {
                            keyframes {
                                IntSize(initialSize.width, initialSize.height) at 150
                                durationMillis = 300
                            }
                        } else {
                            keyframes {
                                IntSize(targetSize.width, targetSize.height) at 150
                                durationMillis = 300
                            }
                        }
                    }
        },
        modifier = Modifier.clickable(interactionSource =  MutableInteractionSource(),
            indication = null) {
            onClick()
        }, label = ""
    ) { state ->

        val fontSize =  15
        val rowPadding = mutableMapOf<String, Int>()

            rowPadding["start"] = 16
            rowPadding["end"] = 16
            rowPadding["top"] = 5
            rowPadding["bottom"] = 5


        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(25.dp))
                .background(if (state) Color(57, 57, 57) else Color(134, 134, 134)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(
                        start = rowPadding["start"]!!.dp,
                        end = rowPadding["end"]!!.dp,
                        top = rowPadding["top"]!!.dp,
                        bottom = rowPadding["bottom"]!!.dp
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row() {
                    Text(
                        text = text,
                        color = Color.White,
                        fontSize = fontSize.sp
                    )
                }
            }
        }
    }
}
