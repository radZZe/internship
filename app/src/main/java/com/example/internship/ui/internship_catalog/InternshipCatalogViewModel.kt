package com.example.internship.ui.internship_catalog

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internship.data.api.InternshipApi
import com.example.internship.models.Internship
import com.example.internship.models.Category
import com.example.internship.models.InternshipFilter
import com.example.internship.models.InternshipStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InternshipCatalogViewModel @Inject constructor(
    private val internshipApi: InternshipApi
):ViewModel() {
    private val _internships = mutableStateListOf<Internship>()
    val internships = mutableStateOf<List<Internship>>(emptyList())
    val isLoading = mutableStateOf(false)

    val filter = mutableStateOf(InternshipFilter())

    val categories = mutableStateListOf(
        Category(InternshipStatus.Preparing,true),
        Category(InternshipStatus.InProgress,false),
        Category(InternshipStatus.Archive,false)
    )
    fun getInternships(){
        viewModelScope.launch (Dispatchers.IO) {
            isLoading.value = true
            _internships.addAll(internshipApi.fetchInternships())
            internships.value = _internships.toList()
            isLoading.value = false
        }
    }

    fun getCategory(selectedCategory: Category){
         internships.value  = _internships.filter {
            it.status.name==selectedCategory.title.name
        }.toList()
        val filtered = mutableListOf<Category>()
        for (category in categories){
            category.isSelected = selectedCategory.title.name == category.title.name
            filtered.add(category)
        }
        categories.clear()
        categories.addAll(filtered)
    }


}