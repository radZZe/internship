package com.example.internship.ui.profiles.profile_company

import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditProfileCompanyViewModel @Inject constructor():ViewModel() {
    var name by mutableStateOf("")
    var lastname by mutableStateOf("")
    var surname by mutableStateOf("")
    var city by mutableStateOf("")
    var contact by mutableStateOf("")
    var photo by mutableStateOf<Uri?>(null)
    var aboutUser by mutableStateOf("")
    var experience by mutableStateOf("")
    var organization by mutableStateOf("")
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
}