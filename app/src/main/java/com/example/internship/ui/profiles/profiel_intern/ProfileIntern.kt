package com.example.internship.ui.profiles.profiel_intern

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.internship.R
import com.example.internship.ui.registration.MainText
import com.example.internship.ui.theme.ButtonColour
import com.example.internship.ui.theme.SecondColour
import com.example.internship.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileIntern(
    viewModel: ProfileInternViewModel = hiltViewModel(),
    onBackNavigate: () -> Unit,
    onEditNavigate: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 12.dp, end = 12.dp)
            .verticalScroll(
                rememberScrollState()
            ),
    ) {
        NavProfileHeader(
            onEditClick = {
                onEditNavigate()
            },
            onBackClick = {
                onBackNavigate()
            })
        ProfileHeader(
            firstTitle = viewModel.user.baseInfo.name + " " + viewModel.user.baseInfo.surname,
            spec = viewModel.user.speciality,
        )
        ProfileTitle(text = "Основные сведения")
        ProfileTextField(
            label = "О себе",
            text = viewModel.user.baseInfo.aboutUser
        )
        if (viewModel.user.baseInfo.experience.isNotEmpty()) {
            ProfileTextField(
                label = "Опыт",
                text = if (viewModel.user.baseInfo.experience.isNotEmpty()) viewModel.user.baseInfo.experience else "-"
            )
        }
        DataAboutIntern(
            listOf(
                viewModel.user.sex,
                viewModel.user.age.toString() + " лет",
                viewModel.user.baseInfo.city,
                viewModel.user.position
            )
        )
        ProfileTitle(text = "Компетенции")
        DataAboutIntern(viewModel.user.skills)
        ProfileTitle(text = "Предпочтения")
        DataAboutIntern(viewModel.user.preferences)
        Spacer(modifier = Modifier.height(16.dp))
        ProfileTitle(text = "Активные заявки")
        if (viewModel.user.activeInternship.isNotEmpty()) {
            DataAboutIntern(viewModel.user.activeInternship)
        } else {
            Text("Пока ничего нет", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProfileTitle(text = "Архивные заявки")
        if (viewModel.user.archiveInternship.isNotEmpty()) {
            DataAboutIntern(viewModel.user.archiveInternship)
        } else {
            Text("Пока ничего нет", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }

    }


}

@Composable
fun NavProfileHeader(
    onBackClick:()->Unit,
    onEditClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            modifier = Modifier.clickable {
                onBackClick()
        },painter = painterResource(id = R.drawable.ic_back_arrow), contentDescription = null)
        Image(
            modifier = Modifier.clickable {
                onEditClick()
            },
            painter = painterResource(id = R.drawable.ic_edit_notactive),
            contentDescription = null
        )
    }
}


@Composable
fun ProfileHeader(
    firstTitle: String,
    spec: String
) {
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
        Text(text = "$firstTitle")
        Text(text = spec)
//        Row(
//            modifier = Modifier.fillMaxWidth(),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            SkillProgress(skillName = "UX", percentage = 0.7f, number = 100)
//            SkillProgress(skillName = "Python", percentage = 0.9f, number = 100)
//            SkillProgress(skillName = "Talk", percentage = 0.5f, number = 100)
//        }

    }
}

//@Composable
//fun SkillProgress(
//    skillName: String,
//    color: Color = Color.Blue,
//    percentage: Float,
//    number: Int,
//    fontSize: TextUnit = 28.sp,
//    radius: Dp = 50.dp,
//    strokeWidth: Dp = 8.dp,
//    animDuration: Int = 100,
//    animDelay: Int = 0,
//) {
//    var animationPlayed by remember {
//        mutableStateOf(false)
//    }
//    val curPercentage = animateFloatAsState(
//        targetValue = if (animationPlayed) percentage else 0f,
//        animationSpec = tween(
//            durationMillis = animDuration,
//            delayMillis = animDelay,
//        )
//    )
//    LaunchedEffect(key1 = true) {
//        animationPlayed = true
//    }
//    Column(
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Box(
//            contentAlignment = Alignment.Center,
//            modifier = Modifier.size(radius * 2f)
//        ) {
//            Canvas(modifier = Modifier.size(radius * 2f)) {
//                drawArc(
//                    color = color,
//                    startAngle = -90f,
//                    sweepAngle = 360 * curPercentage.value,
//                    useCenter = false,
//                    style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Round)
//                )
//            }
//            Text(
//                text = (curPercentage.value * number).toInt().toString(),
//                color = Color.Black, fontSize = fontSize, fontWeight = FontWeight.Bold
//            )
//        }
//        Text(text = skillName)
//    }
//
//}




@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DataAboutIntern(list: List<String>) {
    FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        list.forEach {
            Box(modifier = Modifier.padding(top = 12.dp)) {
                AboutInternItem(it)
            }

        }
    }
}

@Composable
fun AboutInternItem(text: String) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(35))
            .shadow(10.dp, RoundedCornerShape(35), true, Color.Black)
            .background(ButtonColour)
            .border(1.dp, ButtonColour, RoundedCornerShape(35))
            .padding(horizontal = 10.dp, vertical = 5.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(text = text, fontSize = 16.sp, fontWeight = FontWeight.Medium, color = Color.White)
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

@Composable
fun ProfileTextField(label: String, text: String) {

    Text(text = label, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15))
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {
        Text(text = text)
    }
}

@Composable
fun ProfileTitle(text: String) {
    Row(modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(35))
                .background(Color.White)
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {
            Text(
                textAlign = TextAlign.Center,
                text = text,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = ButtonColour
            )
        }
    }


}