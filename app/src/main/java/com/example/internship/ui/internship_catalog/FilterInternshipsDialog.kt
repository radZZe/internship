package com.example.internship.ui.internship_catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.internship.ui.internship_catalog.InternshipCatalogViewModel
import com.example.internship.ui.registration.CategoryItem
import com.example.internship.ui.registration.MainText
import com.example.internship.util.Constants

@Composable
fun FilterInternshipsScreen(
    viewModel:InternshipCatalogViewModel = hiltViewModel(),
    onDismiss:()->Unit,
    onConfirm:()->Unit
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(
            usePlatformDefaultWidth = false
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
                .background(Color.White)
        ) {
            Image(
                painter =  rememberVectorPainter(image = Icons.Default.ArrowBack),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .align(Alignment.Start)
                    .clickable {
                        onDismiss()
                    }
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                MainText(
                    text = "Специализация",
                    color = Color.Black
                )
                LazyRow {
                    items(viewModel.specialityList){ item->
                        CategoryItem(
                            text = item.title,
                            isChecked = item.isSelected
                        ) {
                            viewModel.selectSpeciality(item)
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }
                MainText(
                    text = "Длительность",
                    color = Color.Black
                )
                LazyRow {
                    items(viewModel.durationList){ item ->
                        CategoryItem(
                            text = item.title,
                            isChecked = item.isSelected
                        ) {
                            viewModel.selectDuration(item)
                        }
                        Spacer(modifier = Modifier.size(16.dp))

                    }
                }
                MainText(
                    text = "Количество рабочих позиций",
                    color = Color.Black
                )
                LazyRow {
                    items(viewModel.peopleCntList){ item ->
                        CategoryItem(
                            text = item.title,
                            isChecked = item.isSelected
                        ) {
                            viewModel.selectPeopleCnt(item)
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }
                MainText(text = "Тип", color = Color.Black)
                LazyRow {
                    items(viewModel.typeFilterList){ item ->
                        CategoryItem(
                            text = item.title,
                            isChecked = item.isSelected
                        ) {
                            viewModel.selectType(item)
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }
                Button(onClick = {
                    onConfirm()
                }) {
                    MainText(text = "Применить")
                }
            }
        }
    }

}