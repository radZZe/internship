//package com.example.internship.ui.registration
//
//import androidx.compose.animation.*
//import androidx.compose.animation.core.keyframes
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.interaction.MutableInteractionSource
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalConfiguration
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.IntSize
//import androidx.compose.ui.unit.TextUnit
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.example.internship.ui.theme.ButtonColour
//import com.example.internship.ui.theme.MainFont
//import com.example.internship.util.Constants.LIMIT_WINDOW_HEIGHT
//
//@Composable
//fun CategorySelectionRegistrationScreen(
//    viewModel: SelectSpecialityViewModel = hiltViewModel(),
//) {
//    val windowHeight =
//        LocalConfiguration.current.screenHeightDp.toFloat() * LocalDensity.current.density
//    val windowInfo = if (windowHeight > LIMIT_WINDOW_HEIGHT) 60.dp else 30.dp
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White),
//        contentAlignment = Alignment.Center
//    ) {
//        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
//            HeaderCategoryScreen()
//            Spacer(modifier = Modifier.height(24.dp))
//            CategoriesGrid(viewModel)
//        }
//        Box(
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(bottom = if (windowHeight > LIMIT_WINDOW_HEIGHT) 10.dp else 20.dp)
//        ) {
//            NextButton(viewModel, { /* TODO() */ })
//        }
//
//    }
//}
//
//
//
//@Composable
//fun HeaderCategoryScreen() {
//    Column(Modifier.fillMaxWidth()) {
//        MainText(
//            text = "Чем вы занимаетесь?",
//            fontSize = 22.sp,
//            fontWeight = FontWeight.Bold,
//        )
//        Spacer(modifier = Modifier.height(12.dp))
//        MainText(
//            text = "Выберите от одной до трёх специализаций",
//            fontSize = 16.sp
//        )
//    }
//}
//
