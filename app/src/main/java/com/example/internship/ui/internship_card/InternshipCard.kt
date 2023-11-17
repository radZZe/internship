package com.example.internship.ui.internship_card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.internship.models.InternshipStatus
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
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Image(
            painter =  rememberVectorPainter(image = Icons.Default.ArrowBack),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.Start)
                .clickable {
                    onNavigateBack()
                }
        )
        if (internship.value.type.name==InternshipType.Internship.name){
            /*
           стажировка: элементы-наименование, продолжительность, количество набираемых участников,
           специализация стажировки, функционал, вложенные файлы, оплата (есть/нет), фото,
           дополнительно о стажировке, свободное поле.
            */

            MainText(
                text = internship.value.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            MainText(
                text = "Продолжительность",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            MainText(
                text = internship.value.duration,
                fontSize = 20.sp,
            )
            MainText(
                text = "Необходимое количество участников",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            MainText(
                text = internship.value.peopleCnt.toString(),
                fontSize = 20.sp
            )
            MainText(
                text = "Описание",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            MainText(
                text = internship.value.description,
                fontSize = 20.sp
            )


        }
        else if (internship.value.type.name==InternshipType.Project.name){
        /*
            проект: элементы раздела- название, цель проекта (продукт), продолжительность,
            количество набираемых людей, в рамках какого подразделения компании реализуется,
            кто руководитель проекта со стороны организации, вложенные файлы, оплата (есть/нет),
            документация по проекту (паспорт, бюджет и тд), фото, дополнительно о проекте, свободное поле.
             */

            MainText(
                text = "\"${internship.value.name}\"",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            MainText(
                text = "Цель проекта",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            MainText(
                text = internship.value.projectGoal!!,
                fontSize = 20.sp
            )
            MainText(
                text = "Продолжительность",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            MainText(
                text = internship.value.duration,
                fontSize = 20.sp
            )
        }
        else if (internship.value.type.name==InternshipType.Task.name){
            /*
            -задача: элементы: описание задачи, продолжительность,
            необходимое количество набираемых участников, отдел, организации,
            в котором проводится стажировка, возможно вложенные файлы с документами,
             необходимыми для ознакомлений потенциальных участников с информацией о стажировке),
              оплата (есть/нет), фото, дополнительно о проекте, свободное поле.
             */
            MainText(
                text = "Описание задачи:",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            MainText(
                text = internship.value.name,
                fontSize = 20.sp
            )
            MainText(
                text = "Продолжительность",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            MainText(
                text = internship.value.duration,
                fontSize = 20.sp,
            )
            MainText(
                text = "Необходимое количество участников",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            MainText(
                text = internship.value.peopleCnt.toString(),
                fontSize = 20.sp
            )
            MainText(
                text = "Отдел",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            MainText(
                text = internship.value.department.toString(),
                fontSize = 20.sp
            )
            MainText(
                text = "Организации",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            LazyRow {
                items(internship.value.organizations!!){
                    MainText(text = it)
                    Spacer(modifier = Modifier.size(16.dp))
                }
            }
            MainText(
                text = "Оплата",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            MainText(
                text = "${internship.value.salary} $",
                fontSize = 20.sp
            )

//            Row(
//                horizontalArrangement = Arrangement.spacedBy(8.dp),
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Image(
//                    painter = rememberVectorPainter( Icons.Default.Person),
//                    contentDescription = "people count image",
//                    modifier = Modifier
//                )
//                MainText(
//                    text = internship.value.peopleCnt.toString(),
//                    color = Color.White,
//                    fontSize = 20.sp,
//                )
//            }
        }
        MainText(
            text = "Дополнительная информация",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        MainText(
            text = internship.value.additionalInfo?:"Отсутсвует",
            fontSize = 20.sp
        )
        Button(
            onClick = {
                    //todo
            },
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .padding(8.dp)
        ) {
            Text(text = "Откликнуться")
        }
    }
}