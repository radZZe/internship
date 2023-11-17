package com.example.internship.ui.theme.internship_card

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import com.example.internship.models.Internship

@Composable
fun InternshipCard(
    internshipId:String,
    viewModel: InternshipCardViewModel = hiltViewModel()
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
        Text(
            text = internship.value.name,
            fontWeight = FontWeight.Bold
        )
        Text(text = "${internship.value.salary} $")
        Text(text = internship.value.duration)
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = rememberVectorPainter( Icons.Default.Person),
                contentDescription = "people count image",
                modifier = Modifier
            )
            Text(
                text = internship.value.peopleCnt.toString(),
                color = Color.White
            )
        }
        Text(text = internship.value.description)
        Text(text = internship.value.department?:"")
        Text(text = internship.value.additionalInfo?:"")
    }
}