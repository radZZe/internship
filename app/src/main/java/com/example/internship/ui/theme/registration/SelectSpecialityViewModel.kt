package com.example.internship.ui.theme.registration

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.internship.data.DataStoreRepository
import com.example.internship.models.Speciality
import com.example.internship.ui.theme.registration.Constants.personalities
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectSpecialityViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {
    val selectedSpecialities = mutableStateListOf<MutableState<Speciality>>()
    val specialities = personalities.map {
        mutableStateOf(Speciality(it, mutableStateOf(false)))
    }.toMutableStateList()

    private var isSpecialitiesSelected by mutableStateOf(false)

    fun changeStateCategory(index: Int) {
        if (selectedSpecialities.size < 3){
            if(specialities[index].value.isSelected.value){
                specialities[index].value.isSelected.value = !specialities[index].value.isSelected.value
                selectedSpecialities.remove(specialities[index])
            }else{
                specialities[index].value.isSelected.value = !specialities[index].value.isSelected.value
                selectedSpecialities.add(specialities[index])
            }
        }
        else{
            if(specialities[index].value.isSelected.value){
                specialities[index].value.isSelected.value = !specialities[index].value.isSelected.value
                selectedSpecialities.remove(specialities[index])
            }
        }
    }
}

object Constants {
    val personalities = listOf<String>(
        "android dev",
        "web dev",
        "project manager",
        "ios dev",
        "fullstack dev",
        "backend dev",
        "ceo",
    )

    const val LIMIT_WINDOW_HEIGHT = 1920
}