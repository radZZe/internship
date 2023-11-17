package com.example.internship.ui.internship_catalog.filter_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.internship.ui.registration.MainText

@Preview
@Composable
fun FilterInternshipsScreen() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(color = Color.Black, width = 1.dp)
        ) {
            MainText(text = "type")
            RadioItem(
                label = "project",
                onClick = {

                },
                selected = false)
            RadioItem(
                label = "Internship",
                onClick = {

                },
                selected = false
            )
            RadioItem(label = "Task", onClick = { /*TODO*/ }, selected = false)

        }
    }
}

@Composable
fun RadioItem(label:String, onClick:()->Unit,selected:Boolean){
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = selected, onClick = onClick)
        MainText(text = label)
    }
}
/*
    val duration:String,
    val department:String? = null,
    val peopleCnt:Int,
    val salary:Int? = null,
    val type:InternshipType,
    val status:InternshipStatus,
    val startDate: Date?=null,
   // val imageUrl:String
    val teamLead:String?,
    val specialities:List<String>,
    val additionalInfo: String?=null
 */