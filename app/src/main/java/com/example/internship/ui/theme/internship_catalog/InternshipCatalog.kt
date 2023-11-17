package com.example.internship.ui.theme.internship_catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.internship.models.Internship

@Composable
fun InternshipCatalog(
    viewModel: InternshipCatalogViewModel = hiltViewModel(),
    onNavigateToCard:(internshipId:String)->Unit
) {
    LaunchedEffect(key1 = null){
        viewModel.getInternships()
    }
    val internships = viewModel.internships.collectAsState()
    if(viewModel.isLoading.value){
        CircularProgressIndicator(modifier = Modifier.size(10.dp))
    }
    else{
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            items(viewModel.internships.value){item: Internship ->
                Spacer(modifier = Modifier.size(16.dp))
                InternshipItem(item){
                    onNavigateToCard(it)
                }
            }
        }
    }

}

//@Composable
//fun filterBar(filters:List<String>){//todo
//    LazyRow (modifier = Modifier.fillMaxWidth()){
//        items(filters){filter->
//            CategoryItem(text = filter) {
//                //todo logic for click
//            }
//        }
//    }
//}
@Composable
fun InternshipItem(
    internship: Internship,
    onClick:(internshipId:String)->Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .background(Color.LightGray)
            .clickable {
                onClick(internship.id)
            }
    ) {
        Text(text = internship.name)
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
                text = internship.peopleCnt.toString(),
                color = Color.White
            )
        }
        Text(text = "specialities:")
        LazyRow {
            items(internship.specialities){ name->
                Specialization(name = name)
                Spacer(modifier = Modifier.size(8.dp))
            }
        }
    }
}

@Composable
fun Specialization(name:String){
    Box(modifier = Modifier
        .clip(RoundedCornerShape(10.dp))
        .background(Color.Blue)
    ){
        Text(text = name)
    }
}