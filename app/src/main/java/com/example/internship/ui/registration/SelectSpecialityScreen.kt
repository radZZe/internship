package com.example.internship.ui.registration

import androidx.compose.animation.*
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.internship.ui.theme.ButtonColour
import com.example.internship.ui.theme.MainFont
import com.example.internship.util.Constants.LIMIT_WINDOW_HEIGHT

@Composable
fun CategorySelectionRegistrationScreen(
    viewModel: SelectSpecialityViewModel = hiltViewModel(),
) {
    val windowHeight =
        LocalConfiguration.current.screenHeightDp.toFloat() * LocalDensity.current.density
    val windowInfo = if (windowHeight > LIMIT_WINDOW_HEIGHT) 60.dp else 30.dp
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            HeaderCategoryScreen()
            Spacer(modifier = Modifier.height(24.dp))
            CategoriesGrid(viewModel)
        }
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = if (windowHeight > LIMIT_WINDOW_HEIGHT) 10.dp else 20.dp)
        ) {
            NextButton(viewModel, { /* TODO() */ })
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CategoriesGrid(
    viewModel: SelectSpecialityViewModel
) {
    val data = viewModel.specialities
    FlowRow(
        modifier = Modifier
            .fillMaxWidth(),
    ) {
        data.forEachIndexed { index, item ->
            Column(modifier = Modifier.wrapContentSize()) {
                Row(modifier = Modifier.wrapContentSize()) {
                    CategoryItem(
                        text = item.value.title,
                        isChecked = item.value.isSelected.value
                    ) {
                        viewModel.changeStateCategory(index)
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun CategoryItem(
    text: String,
    isChecked: Boolean = false,
    onClick: () -> Unit?
) {
    AnimatedContent(
        targetState = isChecked,
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
        modifier = Modifier.clickable(
            interactionSource = MutableInteractionSource(),
            indication = null
        ) {
            onClick()
        }, label = ""
    ) { state ->

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
                    MainText(text = text)
                }
            }
        }
    }
}

@Composable
fun NextButton(
    viewModel: SelectSpecialityViewModel,
    onCLick: () -> Unit
) {
    if (viewModel.selectedSpecialities.size > 0) {
        CustomButton(
            text = "Продолжить",
            color = ButtonColour,
            textColor = Color.Black,
            modifier = Modifier.padding(horizontal = 20.dp),
            onCLick = {
                onCLick()
            }
        )
    } else {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

        }
    }
}

@Composable
fun HeaderCategoryScreen() {
    Column(Modifier.fillMaxWidth()) {
        MainText(
            text = "Чем вы занимаетесь?",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(12.dp))
        MainText(
            text = "Выберите от одной до трёх специализаций",
            fontSize = 16.sp
        )
    }
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    text: String = "Text",
    textColor: Color = Color.White,
    color: Color = ButtonColour,
    onCLick: () -> Unit = {}
) {
    Button(
        onClick = { onCLick() },
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(50.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = color,
            contentColor = textColor
        ),

        contentPadding = PaddingValues(vertical = 13.dp)
    ) {
        MainText(text = text)
    }
}

@Composable
fun MainText(
    text: String,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = 14.sp,
    color: Color = Color.White
) {
    Text(
        color = color,
        text = text,
        fontFamily = MainFont,
        fontWeight = fontWeight,
        fontSize = fontSize
    )
}