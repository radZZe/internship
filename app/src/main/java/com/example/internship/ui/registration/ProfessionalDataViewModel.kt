package com.example.internship.ui.registration

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internship.data.DataStoreRepository
import com.example.internship.data.DataStoreRepository.PreferenceKeys.userStatus
import com.example.internship.models.BaseInfo
import com.example.internship.models.Company
import com.example.internship.models.Intern
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfessionalDataViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {
    init {
        viewModelScope.launch(Dispatchers.IO) {
            status = dataStoreRepository.getPref(userStatus).first()
        }
    }

    var status by mutableStateOf("")

    // Uni
    var baseInfo by mutableStateOf("") // Have to be gotten from network

    // Intern
    var speciality by mutableStateOf("")
    var age by mutableStateOf(0)
    var sex by mutableStateOf("")



    fun onSpecialityChanged(newText: String) {
        speciality = newText
    }

    fun onAgeChanged(newText: Int) {
        age = newText
    }

    fun onSexChanged(newText: String) {
        sex = newText
    }



    fun sendUserData() {
        if (status == "STUDENT") {
//            val intern = Intern(
//                baseInfo = baseInfo,
//                speciality = speciality,
//                sex = sex,
//                age = age,
//            )
        } else {
//            val company = Company(
//                baseInfo = baseInfo,
//                organization = organization
//            )
        }
    }
}