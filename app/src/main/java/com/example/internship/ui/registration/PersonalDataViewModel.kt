package com.example.internship.ui.registration

import android.text.TextUtils
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.internship.data.DataStoreRepository
import com.example.internship.data.DataStoreRepository.PreferenceKeys.userStatus
import com.example.internship.models.BaseInfo
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
    var status by mutableStateOf("")
    var contact by mutableStateOf("")
    var photo by mutableStateOf("")
    var aboutUser by mutableStateOf("")
    var experience by mutableStateOf("")
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

    fun saveUser() {
        if (isValidData()) {
            val baseInfo = BaseInfo(
                name = name,
                surname = surname,
                lastName = lastname,
                photo = photo,
                city = city,
                contact = contact,
                status = status,
                password = password,
                email = email,
                aboutUser = aboutUser,
                experience = experience
            )
            viewModelScope.launch(Dispatchers.IO) {
                dataStoreRepository.setPref(status, userStatus)
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
                        status.isNotBlank() &&
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
