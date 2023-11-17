package com.example.internship.ui.internship_catalog

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.internship.models.Internship
import com.example.internship.models.Category
import com.example.internship.models.InternshipStatus
import com.example.internship.ui.theme.ButtonColour

@Composable
fun InternshipCatalog(
    viewModel: InternshipCatalogViewModel = hiltViewModel(),
    onNavigateToCard:(internshipId:String)->Unit,
    onNavigateToSearch:()->Unit
) {
    LaunchedEffect(key1 = null){
        viewModel.getInternships()
    }
    val internships = viewModel.internships
    if(viewModel.isLoading.value){
        CircularProgressIndicator(modifier = Modifier.size(10.dp))
    }
    else{
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ){
            item {
                SearchWidget(
                    modifier = Modifier
                        .padding(16.dp)
                ) {
                   onNavigateToSearch()
                }
            }
            item {
                LazyRow (modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                ){
                    items(viewModel.categories){category->
                        CategoryItem(
                            text = category.title.name,
                            isChecked = category.isSelected
                        ) {
                            //todo logic for click
                            viewModel.getCategory(category)
                        }
                        Spacer(modifier = Modifier.size(16.dp))
                    }
                }
            }
            items(viewModel.internships.value){item: Internship ->
                Spacer(modifier = Modifier.size(16.dp))
                InternshipItem(item){
                    onNavigateToCard(it)
                }
            }
        }
    }

}

@Composable
fun SearchWidget(modifier: Modifier,onClick:()->Unit){
    Box(
        modifier
            .clip(shape = RoundedCornerShape(12.dp))
            .fillMaxWidth()
            .background(Color.Gray)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) { onClick() }
    ){
        Text(
            text = "Search",
            fontSize = 18.sp,
            modifier = Modifier.padding(16.dp)
        )
        Image(
            painter = rememberVectorPainter(Icons.Default.Search),
            contentDescription = "SearchIcon",
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(16.dp)
        )
    }
}

@Composable
fun InternshipItem(
    internship: Internship,
    onClick:(internshipId:String)->Unit
){
    Column(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .background(Color.Gray)
            //.clip(RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.End)) {
                if(internship.status.name==InternshipStatus.Preparing.name){
                    Text(text = "20/12/2023")
                }
            }
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
            Button(
                onClick = {
                    onClick(internship.id)
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