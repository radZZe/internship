package com.example.internship.ui.internship_catalog.filter_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.internship.ui.registration.CategoryItem
import com.example.internship.ui.registration.MainText
import com.example.internship.util.Constants

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
            MainText(text = "Специализация")
            LazyRow {
                items(Constants.specialities){
                    CategoryItem(text = it) {
                        //todo
                    }
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
            MainText(text = "Длительность")
            Row {
                CategoryItem(text = "менее недели") {
                    //todo
                }
                Spacer(modifier = Modifier.size(16.dp))
                CategoryItem(text = "Менее месяца") {
                    //todo
                }
                Spacer(modifier = Modifier.size(16.dp))
                CategoryItem(text = "Более 3 месяцев") {
                    //todo
                }
            }
            MainText(text = "Количество рабочих позиций")
            Row {
                CategoryItem(text = "Менее 5") {
                    //todo
                }
                Spacer(modifier = Modifier.size(16.dp))
                CategoryItem(text = "5-15") {
                    //todo
                }
                Spacer(modifier = Modifier.size(16.dp))
                CategoryItem(text = "15-30") {
                    //todo
                }
                Spacer(modifier = Modifier.size(16.dp))
                CategoryItem(text = "30 и более") {
                    //todo
                }
            }
            MainText(text = "Тип")
            Row {
                CategoryItem(text = "Проект") {
                    //todo
                }
                Spacer(modifier = Modifier.size(16.dp))
                CategoryItem(text = "Стажировка") {
                    //todo
                }
                Spacer(modifier = Modifier.size(16.dp))
                CategoryItem(text = "Задание") {
                    //todo
                }
            }
            Button(onClick = { /*TODO*/ }) {
                MainText(text = "Применить")
            }
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