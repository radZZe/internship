package com.example.internship.ui.theme.internship_catalog

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internship.data.api.InternshipApi
import com.example.internship.models.Internship
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
    private val _internships = MutableStateFlow<List<Internship>>(emptyList())
    val internships = _internships.asStateFlow()
    val isLoading = mutableStateOf(false)
    fun getInternships(){
        viewModelScope.launch (Dispatchers.IO) {
            isLoading.value = true
            _internships.emit(internshipApi.fetchInternships())
            isLoading.value = false
        }
    }
}