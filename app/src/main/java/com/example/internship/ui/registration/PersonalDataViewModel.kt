package com.example.internship.ui.registration

import android.net.Uri
import android.text.TextUtils
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internship.data.DataStoreRepository
import com.example.internship.data.DataStoreRepository.PreferenceKeys.userStatus
import com.example.internship.models.BaseInfo
import com.example.internship.models.Company
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
    var status by mutableStateOf<String?>(null)
    var contact by mutableStateOf("")
    var photo by mutableStateOf<Uri?>(null)
    var aboutUser by mutableStateOf("")
    var experience by mutableStateOf("")
    // Company
    var organization by mutableStateOf("")

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

    val statuses  = listOf("Работодатель", "Стажёр")
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
                val company = Company(baseInfo = baseInfo, organization = organization)
            }
            viewModelScope.launch(Dispatchers.IO) {
                dataStoreRepository.setPref(status!!, userStatus)
            }
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
                        password == password2
                )
    }

    private fun isValidPassword() : Boolean {
        val passwordREGEX = Pattern.compile("^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=\\S+$)" +           //no white spaces
                ".{8,}" +               //at least 8 characters
                "$");
        return passwordREGEX.matcher(password).matches()
    }

    private fun isValidEmail(): Boolean {
        return !TextUtils.isEmpty(email) &&
                android.util.Patterns
                    .EMAIL_ADDRESS
                    .matcher(email)
                    .matches()
    }
}
