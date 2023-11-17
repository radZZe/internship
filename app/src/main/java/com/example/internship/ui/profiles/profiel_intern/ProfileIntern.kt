package com.example.internship.ui.profiles.profiel_intern

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@Composable
fun ProfileIntern(
    viewModel: ProfileInternViewModel = hiltViewModel()
) {
    Column(modifier = Modifier.padding(start = 12.dp, end = 12.dp)) {
        InternProfileHeader()
        Text(text = "Основные требования")
        ProfileOutlinedTextField(
            label = "О себе",
            value = viewModel.aboutMeText.value,
            onClickTrailingIcon = {},
            onValueChanged = {
                viewModel.onAboutMeChanged(it)
            }
        )
        DataAboutIntern(viewModel.userData)
        Text(text = "Компетенции")
        Text(text = "Предпочтения")
    }

}

@Composable
fun InternProfileHeader() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .clip(CircleShape),
            model = "https://icdn.lenta.ru/images/2022/07/14/13/20220714133344014/square_320_2144f43b9da1fd38c196a1dfc0feffe5.jpg",
            contentDescription = null,
        )
        Text(text = "Name LastName")
        Text(text = "UI/UX Designer")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SkillProgress(skillName = "UX", percentage = 0.7f, number = 100)
            SkillProgress(skillName = "Python", percentage = 0.9f, number = 100)
            SkillProgress(skillName = "Talk", percentage = 0.5f, number = 100)
        }

    }
}

@Composable
fun SkillProgress(
    skillName: String,
    color: Color = Color.Blue,
    percentage: Float,
    number: Int,
    fontSize: TextUnit = 28.sp,
    radius: Dp = 50.dp,
    strokeWidth: Dp = 8.dp,
    animDuration: Int = 100,
    animDelay: Int = 0,
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }
    val curPercentage = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        animationSpec = tween(
            durationMillis = animDuration,
            delayMillis = animDelay,
        )
    )
    LaunchedEffect(key1 = true) {
        animationPlayed = true
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(radius * 2f)
        ) {
            Canvas(modifier = Modifier.size(radius * 2f)) {
                drawArc(
                    color = color,
                    startAngle = -90f,
                    sweepAngle = 360 * curPercentage.value,
                    useCenter = false,
                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
                )
            }
            Text(
                text = (curPercentage.value * number).toInt().toString(),
                color = Color.Black, fontSize = fontSize, fontWeight = FontWeight.Bold
            )
        }
        Text(text = skillName)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutIntern(
    value: String,
    onValueChanged: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
    ) {
        Text(text = "О себе:")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(8))
                .border(1.dp, Color.Black, RoundedCornerShape(8))
                .padding(5.dp)
        ) {
            Text(
                maxLines = 6,
                text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum."
            )
        }
    }
}


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DataAboutIntern(list: List<String>) {
    FlowRow {
        list.forEach {
            AboutInternItem(it)
        }
    }
}

@Composable
fun AboutInternItem(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(25))
            .border(0.5.dp, Color(107, 107, 107, 255), RoundedCornerShape(25))
            .padding(horizontal = 10.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 19.sp)
    }

}

@Composable
fun ProfileOutlinedTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    paddingStart: Dp = 5.dp,
    height: Dp = 130.dp,
    trailingIcon: Int? = null,
    onClickTrailingIcon: () -> Unit,
    labelFontSize: Int = 14,
    valueFontSize: Int = 14,
) {
    val lineHeightSp: TextUnit = labelFontSize.sp
    val lineHeightDp: Dp = with(LocalDensity.current) {
        lineHeightSp.toDp()
    }
    Box(
        Modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .zIndex(3f)
        ) {
            Spacer(modifier = Modifier.width(paddingStart))
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .zIndex(4f)
                    .padding(horizontal = 2.dp)
            ) {
                Text(text = label, color = Color.Black, fontSize = labelFontSize.sp)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height - lineHeightDp.div(1.5f))
                .clip(
                    RoundedCornerShape(9.dp)
                )
                .border(
                    1.dp, Color.Gray, RoundedCornerShape(9.dp)
                )
                .background(Color.White)
                .align(Alignment.BottomCenter)
                .zIndex(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = paddingStart, top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = { onValueChanged(it) },
                    modifier = Modifier.padding(start = paddingStart),
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = valueFontSize.sp,
//                        fontFamily = FontFamily(
//                            Font(R.font.roboto_regular)
//                        )
                    )
                )
                if (trailingIcon != null) Image(
                    painter = painterResource(id = trailingIcon),
                    contentDescription = null,
                    modifier = Modifier.clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) { onClickTrailingIcon() })
            }

        }
    }
}