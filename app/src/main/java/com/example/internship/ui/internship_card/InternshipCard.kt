package com.example.internship.ui.internship_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.internship.models.InternshipType
import com.example.internship.ui.registration.MainText


@Composable
fun InternshipCard(
    internshipId:String,
    viewModel: InternshipCardViewModel = hiltViewModel(),
    onNavigateBack:()->Unit
) {
    LaunchedEffect(key1 = null){
        viewModel.getCardInfo(internshipId)
    }
    val internship = viewModel.internship.collectAsState()
    Column(
        modifier = Modifier
        .fillMaxSize(),
       horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter =  rememberVectorPainter(image = Icons.Default.ArrowBack),
            contentDescription = null,
            modifier = Modifier
                .size(10.dp)
                .clickable {
                    onNavigateBack()
                }
        )
        MainText(
            text = internship.value.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
//        when(viewModel.internship.value.type.name){
//
//        }
        MainText(
            text = "${internship.value.salary} $"

        )

        MainText(text = internship.value.duration)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberVectorPainter( Icons.Default.Person),
                contentDescription = "people count image",
                modifier = Modifier
            )
            MainText(
                text = internship.value.peopleCnt.toString(),
                color = Color.White
            )
        }
        MainText(text = internship.value.description)
        MainText(text = internship.value.department?:"")
        MainText(text = internship.value.additionalInfo?:"")
    }
}