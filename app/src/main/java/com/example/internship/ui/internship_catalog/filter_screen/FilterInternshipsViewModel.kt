package com.example.internship.ui.internship_catalog.filter_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.internship.models.InternshipType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FilterInternshipsViewModel @Inject constructor():ViewModel(){
    val type = mutableStateOf(InternshipType.Internship)
}