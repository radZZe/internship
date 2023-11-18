package com.example.internship.ui.profiles.profiel_intern

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import com.example.internship.models.BaseInfo
import com.example.internship.models.DropDownItem
import com.example.internship.models.Intern
import com.example.internship.models.InternPosition
import com.example.internship.util.Constants
import com.example.internship.util.internPositionMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileInternViewModel @Inject constructor() :ViewModel() {
    var name by mutableStateOf("")
    var lastname by mutableStateOf("")
    var surname by mutableStateOf("")
    var city by mutableStateOf("")
    var contact by mutableStateOf("")
    var photo by mutableStateOf<Uri?>(null)
    var aboutUser by mutableStateOf("")
    var experience by mutableStateOf("")

    // Company
    var organization by mutableStateOf("")


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
    val сompetenciesList = Constants.сompetencies.map {
        DropDownItem(it, false)
    }.toMutableStateList()
    val specialityList = Constants.specialities.map {
        DropDownItem(it, false)
    }.toMutableStateList()
    var selectedSpeciality: Int? = null
    var countCompetencies:Int = 0
    var isExpandedSpeciality = mutableStateOf(false)
    var isExpandedCompetencies = mutableStateOf(false)

    fun changeExpandedSpeciality(){
        isExpandedSpeciality.value =!isExpandedSpeciality.value
    }
    fun changeExpandedCompetencies(){
        isExpandedCompetencies.value =!isExpandedCompetencies.value
    }

    fun onDropDownSpecialitySelect(index: Int) {
        if (selectedSpeciality != null) {
            specialityList[selectedSpeciality!!] =
                specialityList[selectedSpeciality!!].copy(isSelect = !specialityList[selectedSpeciality!!].isSelect)
        }
        specialityList[index] =
            specialityList[index].copy(isSelect = !specialityList[index].isSelect)
        selectedSpeciality = index
    }

    fun onDropDownCompetenciesSelect(index: Int) {
        if(countCompetencies < 5){
            if(сompetenciesList[index].isSelect){
                countCompetencies-=1
            }else{
                countCompetencies+=1
            }
            сompetenciesList[index] =
                сompetenciesList[index].copy(isSelect = !сompetenciesList[index].isSelect)

        }else if(сompetenciesList[index].isSelect){
            countCompetencies-=1
            сompetenciesList[index] =
                сompetenciesList[index].copy(isSelect = !сompetenciesList[index].isSelect)
        }

    }

    fun onPhotoChanged(newUri: Uri) {
        photo = newUri
    }

    fun onOrganizationChanged(newText: String) {
        organization = newText
    }

    fun onNameChanged(newText: String) {
        name = newText
    }

    fun onSurnameChanged(newText: String) {
        surname = newText
    }

    fun onLastnameChanged(newText: String) {
        lastname = newText
    }

    fun onCityChanged(newText: String) {
        city = newText
    }

    fun onContactChanged(newText: String) {
        contact = newText
    }

    fun onAboutChanged(newText: String) {
        aboutUser = newText
    }

    fun onExperienceChanged(newText: String) {
        experience = newText

    }

    val isPositionExpanded = mutableStateOf(false)
    fun changeExpandedPosition(){
        isPositionExpanded.value =!isPositionExpanded.value
    }
    var selectedPosition: Int? = null

    fun onDropDownPositionSelect(index: Int) {
        if (selectedPosition != null) {
            positions[selectedPosition!!] =
                positions[selectedPosition!!].copy(isSelect = !positions[selectedPosition!!].isSelect)
        }
        positions[index] =
            positions[index].copy(isSelect = !positions[index].isSelect)
        selectedPosition = index
    }

    val positions =
        listOf("Стажёр", "Помощник стажера", "Менеджер", "Администратор", "Член проектной команды").map {
            DropDownItem(it,false)
        }.toMutableStateList()

    val workFormats = listOf("Онлайн", "Оффлайн")
    var workFormat by mutableStateOf<String>("")
    fun onWorkFormatChanged(newText: String) {
        workFormat = newText
    }

    val remunerations = listOf("Оплачиваемая", "Неоплачиваемая")
    var remuneration by mutableStateOf<String>("")
    fun onRemunerationChanged(newText: String) {
        remuneration = newText
    }

    val workCities = listOf("Город пребывания", "Другой")
    var workCity by mutableStateOf<String>("")
    fun onWorkCityChanged(newText: String) {
        workCity = newText
    }

    val durations = listOf("Продолжительная", "Краткосрочная")
    var duration by mutableStateOf<String>("")
    fun onDurationChanged(newText: String) {
        duration = newText
    }

    val followingWorks = listOf("С последующим трудоустройством", "Без")
    var followingWork by mutableStateOf<String>("")
    fun onFollowingWorkChanged(newText: String) {
        followingWork = newText
    }
}