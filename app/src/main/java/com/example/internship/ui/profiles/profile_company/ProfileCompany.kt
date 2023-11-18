package com.example.internship.ui.profiles.profile_company

import android.widget.RatingBar
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.internship.R
import com.example.internship.ui.profiles.profiel_intern.DataAboutIntern
import com.example.internship.ui.profiles.profiel_intern.NavProfileHeader
import com.example.internship.ui.profiles.profiel_intern.ProfileHeader
import com.example.internship.ui.profiles.profiel_intern.ProfileTextField
import com.example.internship.ui.profiles.profiel_intern.ProfileTitle

@Composable
fun ProfileCompany(
    viewModel: ProfileCompanyViewModel = hiltViewModel(),
    onBackNavigate: () -> Unit,
    onEditNavigate: () -> Unit,
){
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
            spec = viewModel.user.organization,
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()){
            RatingBar(
                rate = viewModel.user.rating,
                enabled = false,
                onClick = {},
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProfileTitle(text = "Основные сведения")
        Spacer(modifier = Modifier.height(8.dp))
        ProfileTextField(
            label = "О себе",
            text = viewModel.user.baseInfo.aboutUser
        )
        Spacer(modifier = Modifier.height(8.dp))
        if (viewModel.user.baseInfo.experience.isNotEmpty()) {
            ProfileTextField(
                label = "Опыт",
                text = if (viewModel.user.baseInfo.experience.isNotEmpty()) viewModel.user.baseInfo.experience else "-"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProfileTitle(text = "Активные вакансии")
        Spacer(modifier = Modifier.height(8.dp))
        if (viewModel.user.activeVacancies.isNotEmpty()) {
            DataAboutIntern(viewModel.user.activeVacancies)
        } else {
            Text("Пока ничего нет", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProfileTitle(text = "Архивные вакансии")
        Spacer(modifier = Modifier.height(8.dp))
        if (viewModel.user.archiveVacancies.isNotEmpty()) {
            DataAboutIntern(viewModel.user.archiveVacancies)
        } else {
            Text("Пока ничего нет", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        }
    }

}

@Composable
fun RatingBar(
    rate:Double = 0.0,
    enabled: Boolean,
    numberOfSelectedStarsDefault: Int = 0,
    starSize: Int = 40,
    onClick: (rate: Int) -> Unit,
) {
    val numberOfStars = 5;
    val numberOfSelectedStars = remember { mutableStateOf(numberOfSelectedStarsDefault) }
    Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
        Text(text = rate.toString(),fontSize = 16.sp, fontWeight = FontWeight.Medium)
        for (i in 1..rate.toInt()) {
            Image(
                painter = painterResource(id = R.drawable.star_selected),
                contentDescription = null,
                modifier = Modifier
                    .clickable(
                        enabled = enabled, interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        onClick(i)
                    }
                    .size(starSize.dp)
            )
        }
        for (i in (rate.toInt() + 1)..numberOfStars) {
            Image(
                painter = painterResource(id = R.drawable.star_unselected),
                contentDescription = null,
                modifier = Modifier
                    .clickable(
                        enabled = enabled, interactionSource = MutableInteractionSource(),
                        indication = null
                    ) {
                        numberOfSelectedStars.value = i
                        onClick(i)
                    }
                    .size(starSize.dp)
            )
        }
    }

}