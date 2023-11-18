package com.example.internship.ui.profiles.profile_company

import androidx.lifecycle.ViewModel
import com.example.internship.models.BaseInfo
import com.example.internship.models.Company
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileCompanyViewModel @Inject constructor():ViewModel() {
    val user = Company(
        organization = "ОРАЙН",
        archiveVacancies = mutableListOf(),
        activeVacancies = mutableListOf(),
        baseInfo = BaseInfo(
            name = "Данила",
            surname = "Раздобаров",
            lastName = "Андреевич",
            city = "Биробиджан",
            email = "mimozadr2@gmail.com",
            password = "Danila200342$",
            status = "Company",
            contact = "89246491973",
            aboutUser = "Я очень люблю хакатоны там бесплатная рабочая сила",
            experience = "Нанимал двух ослов на работу работали хорошо , долго и упорно"
        ),
        rating = 4.0,
    )
}