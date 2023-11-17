package com.example.internship.ui.profiles.profiel_intern

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileInternViewModel @Inject constructor():ViewModel() {
    val aboutMeText = mutableStateOf("")
    fun onAboutMeChanged(newText:String){
        aboutMeText.value = newText
    }
    val userData = listOf<String>(
        "18 лет",
        "Бироиджан",
        "Мужчина",
        "Android разработка",
        "ДВФУ",
        "Есть опыт",
    )
    val userPreferences = listOf<String>(
        "Онлайн",
        "Оплачиваемая",
        "В другом городе",
        "Длительная",
        "С последующим трудоустройством",
    )
    val userCompetencies = listOf<String>(
        "18 лет",
        "Бироиджан",
        "Мужчина",
        "Android разработка",
        "ДВФУ",
        "Есть опыт",
    )
}

enum class userDataType{
    SEX,AGE,CITY,SPECIALIZATION,UNIVER,EXPERIENCE
}