package com.example.internship.ui.registration

import android.net.Uri
import android.text.TextUtils
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internship.data.DataStoreRepository
import com.example.internship.data.DataStoreRepository.PreferenceKeys.userStatus
import com.example.internship.models.BaseInfo
import com.example.internship.models.Company
import com.example.internship.models.Intern
import com.example.internship.models.Speciality
import com.example.internship.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class PersonalDataViewModel @Inject constructor(
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {
    var name by mutableStateOf("")
    var lastname by mutableStateOf("")
    var email by mutableStateOf("")
    var surname by mutableStateOf("")
    var password by mutableStateOf("")
    var password2 by mutableStateOf("")
    var city by mutableStateOf("")
    var status by mutableStateOf<String>("")
    var contact by mutableStateOf("")
    var photo by mutableStateOf<Uri?>(null)
    var aboutUser by mutableStateOf("")
    var experience by mutableStateOf("")

    // Company
    var organization by mutableStateOf("")

    // Intern
    val preferences: MutableList<String> = mutableListOf()
    var position by mutableStateOf("")
    val skills: MutableList<String> = mutableListOf()
    var speciality by mutableStateOf("")
    var age by mutableStateOf("")
    var sex by mutableStateOf("")

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

    fun onPasswordChanged(newText: String) {
        password = newText
    }

    fun onPassword2Changed(newText: String) {
        password2 = newText
    }

    fun onEmailChanged(newText: String) {
        email = newText
    }

    fun onCityChanged(newText: String) {
        city = newText
    }

    fun onStatusChanged(newText: String) {
        status = newText
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

    fun onPhotoChanged(newUri: Uri) {
        photo = newUri
    }

    fun onPositionChanged(newText: String) {
        position = newText
    }

    val statuses = listOf("Работодатель", "Стажёр")

    val positions =
        listOf("Стажёр", "Помощник стажера", "Менеджер", "Администратор", "Член проектной команды")

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

    fun onSpecialityChanged(newText: String) {
        speciality = newText
    }

    fun onAgeChanged(newAge: String) {
        age = newAge
    }

    fun onSexChanged(newText: String) {
        sex = newText
    }

    val selectedSpecialities = mutableStateListOf<MutableState<Speciality>>()
    val specialities = Constants.specialities.map {
        mutableStateOf(Speciality(it, mutableStateOf(false)))
    }.toMutableStateList()

    val selectedSkills = mutableStateListOf<MutableState<Speciality>>()
    val softSkills = Constants.softSkills.map {
        mutableStateOf(Speciality(it, mutableStateOf(false)))
    }.toMutableStateList()

    private var isSpecialitiesSelected by mutableStateOf(false)

    fun changeStateCategory(index: Int) {
        if (selectedSpecialities.size < 1) {
            if (specialities[index].value.isSelected.value) {
                specialities[index].value.isSelected.value =
                    !specialities[index].value.isSelected.value
                selectedSpecialities.remove(specialities[index])
            } else {
                specialities[index].value.isSelected.value =
                    !specialities[index].value.isSelected.value
                selectedSpecialities.add(specialities[index])
            }
        } else {
            if (specialities[index].value.isSelected.value) {
                specialities[index].value.isSelected.value =
                    !specialities[index].value.isSelected.value
                selectedSpecialities.remove(specialities[index])
            }
        }
        if (selectedSpecialities.size > 0) {
            onSpecialityChanged(selectedSpecialities[0].value.title)
        }
    }

    fun changeStateSkill(index: Int) {
        if (selectedSkills.size < 5) {
            if (softSkills[index].value.isSelected.value) {
                softSkills[index].value.isSelected.value =
                    !softSkills[index].value.isSelected.value
                selectedSkills.remove(softSkills[index])
            } else {
                softSkills[index].value.isSelected.value =
                    !softSkills[index].value.isSelected.value
                selectedSkills.add(softSkills[index])
            }
        } else {
            if (softSkills[index].value.isSelected.value) {
                softSkills[index].value.isSelected.value =
                    !softSkills[index].value.isSelected.value
                selectedSkills.remove(softSkills[index])
            }
        }
        if (selectedSkills.size > 0) {
            skills.clear()
            skills.addAll(selectedSkills.map { it.value.title })
        }
    }

    private fun isValidData(): Boolean {
        return (
                isValidEmail() &&
                        isValidPassword() &&
                        name.isNotBlank() &&
                        surname.isNotBlank() &&
                        lastname.isNotBlank() &&
                        city.isNotBlank() &&
                        contact.isNotBlank() &&
                        !status.isNullOrBlank() &&
                        experience.isNotBlank() &&
                        aboutUser.isNotBlank() &&
                        password == password2 &&
                        (isValidIntern() || isValidCompany())
                )
    }

    private fun isValidPassword(): Boolean {
        val passwordREGEX = Pattern.compile(
            "^" +
                    "(?=.*[0-9])" +         //at least 1 digit
                    "(?=.*[a-z])" +         //at least 1 lower case letter
                    "(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=\\S+$)" +           //no white spaces
                    ".{8,}" +               //at least 8 characters
                    "$"
        );
        return passwordREGEX.matcher(password).matches()
    }

    private fun isValidEmail(): Boolean {
        return !TextUtils.isEmpty(email) &&
                android.util.Patterns
                    .EMAIL_ADDRESS
                    .matcher(email)
                    .matches()
    }

    private fun isValidCompany(): Boolean {
        return (organization.isNotBlank())
    }

    private fun isValidIntern(): Boolean {
        return (
                speciality.isNotBlank() &&
                        position.isNotBlank() &&
                        skills.size > 0 &&
                        age.toInt() >= 18 &&
                        preferences.size > 0 &&
                        skills.size > 0
                )
    }

    fun saveUser() {
        if (isValidData()) {
            val baseInfo = BaseInfo(
                name = name,
                surname = surname,
                lastName = lastname,
                photo = photo.toString(),
                city = city,
                contact = contact,
                status = status!!,
                password = password,
                email = email,
                aboutUser = aboutUser,
                experience = experience
            )
            if (status == "Работодатель") {
                val company = Company(
                    baseInfo = baseInfo,
                    organization = organization
                )
            } else {
                preferences.add(workFormat)
                preferences.add(remuneration)
                preferences.add(workCity)
                preferences.add(duration)
                preferences.add(followingWork)
                val intern = Intern(
                    baseInfo = baseInfo,
                    sex = sex,
                    age = age.toInt(),
                    speciality = speciality,
                    preferences = preferences,
                    skills = skills,
                    position = position
                )
            }
            viewModelScope.launch(Dispatchers.IO) {
                dataStoreRepository.setPref(status!!, userStatus)
            }
        }
    }
}
