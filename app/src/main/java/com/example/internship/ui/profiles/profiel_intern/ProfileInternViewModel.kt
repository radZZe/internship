package com.example.internship.ui.profiles.profiel_intern

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.internship.models.BaseInfo
import com.example.internship.models.Intern
import com.example.internship.models.InternPosition
import com.example.internship.util.internPositionMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileInternViewModel @Inject constructor():ViewModel() {
    val isEditMode = mutableStateOf(false)
    val aboutMeText = mutableStateOf("")
    fun onAboutMeChanged(newText: String) {
        aboutMeText.value = newText
    }

    val user = Intern(
        baseInfo = BaseInfo(
            name = "Данила",
            surname = "Раздобаров",
            lastName = "Андреевич",
            city = "Биробиджан",
            email = "mimozadr2@gmail.com",
            password = "Danila200342$",
            status = "Intern",
            contact = "89246491973",
            aboutUser = "Я очень люблю хакатоны",
            experience = "Нанимал двух ослов на работу"
        ),
        sex = "Мужчина",
        age = 20,
        speciality = "Android разработчик",
        preferences = mutableListOf(
            "Online",
            "Оплачиваемая",
            "В городе пребывания",
            "Длительная",
            "С последующим трудоустройством"
        ),
        position = internPositionMapper(InternPosition.INTERN),
        skills = mutableListOf(
            "Коммуникабельность",
            "Стрессоустойчивость",
            "Лояльность",
            "Умение работать в команде"
        ),
        activeInternship = mutableListOf(),
        archiveInternship = mutableListOf()
    )
    fun changeEditMode(){
        isEditMode.value = !isEditMode.value
    }
}
