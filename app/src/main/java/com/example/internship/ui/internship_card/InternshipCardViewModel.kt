package com.example.internship.ui.internship_card

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internship.models.Internship
import com.example.internship.models.InternshipStatus
import com.example.internship.models.InternshipType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InternshipCardViewModel @Inject constructor(

):ViewModel() {

    val internship = MutableStateFlow(Internship(
        "test",
        "name",
        "dec",
        "dur",
        "dep",
        5,
        100,
        InternshipType.Internship,
        InternshipStatus.Preparing,
        null,
        null,
        "goal",
        "teamlead",
        specialities = listOf("mobile","backend","frontend")
    ))
    fun getCardInfo(internshipId:String){
        viewModelScope.launch (Dispatchers.IO) {
            //get Internship
        }
    }

}