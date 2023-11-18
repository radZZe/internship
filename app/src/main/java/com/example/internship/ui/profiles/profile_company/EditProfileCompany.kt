package com.example.internship.ui.profiles.profile_company

import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.internship.R
import com.example.internship.ui.profiles.profiel_intern.NavProfileHeader
import com.example.internship.ui.profiles.profiel_intern.ProfileOutlinedTextField
import com.example.internship.ui.registration.CustomButton
import com.example.internship.ui.registration.MainText
import com.example.internship.ui.theme.SecondColour
import com.example.internship.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileCompany(
    viewModel: EditProfileCompanyViewModel = hiltViewModel(),
    onBackNavigate: () -> Unit,
){
    val resources = LocalContext.current.resources
    val resolver = LocalContext.current.contentResolver
    val galleryLauncher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri: Uri? ->
            if (uri != null) {
                val flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
                resolver.takePersistableUriPermission(uri, flags)
                viewModel.onPhotoChanged(uri)
            }
        }

    val imagePlaceholderUri = remember {
        Uri.Builder()
            .scheme(ContentResolver.SCHEME_ANDROID_RESOURCE)
            .authority(resources.getResourcePackageName(R.drawable.image_placeholder))
            .appendPath(resources.getResourceTypeName(R.drawable.image_placeholder))
            .appendPath(resources.getResourceEntryName(R.drawable.image_placeholder))
            .build()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NavProfileHeader(
            onEditClick = {
                //отправлять данные
                onBackNavigate()
            },
            onBackClick = {
                // не отправлять данные
                onBackNavigate()
            })
        MainText(
            text = "Изменить аккаунт",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = null
            ) {
                galleryLauncher.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
            }
            .size(138.dp)
            .clip(CircleShape))
        {
            AsyncImage(
                model = if (viewModel.photo == null) imagePlaceholderUri else viewModel.photo,
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.Center),
                contentDescription = "profile_placeholder",
                contentScale = ContentScale.Crop
            )
            Image(
                modifier = Modifier.align(Alignment.Center),
                painter = painterResource(id = R.drawable.image_upload_icon),
                contentDescription = "button_image_upload"
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        ProfileOutlinedTextField(
            label = "Имя",
            value = viewModel.name,
            onValueChanged = { viewModel.onNameChanged(it) },
            paddingStart = 16.dp,
            height = 48.dp,
            labelFontSize = 13,
            valueFontSize = 16,
            trailingIcon = R.drawable.ic_close_circle,
            onClickTrailingIcon = {
                viewModel.onNameChanged("")
            }
        )
        ProfileOutlinedTextField(
            label = "Организация",
            value = viewModel.organization,
            onValueChanged = { viewModel.onOrganizationChanged(it) },
            paddingStart = 16.dp,
            height = 48.dp,
            labelFontSize = 13,
            valueFontSize = 16,
            trailingIcon = R.drawable.ic_close_circle,
            onClickTrailingIcon = {
                viewModel.onNameChanged("")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProfileOutlinedTextField(
            label = "Фамилия",
            value = viewModel.surname,
            onValueChanged = { viewModel.onSurnameChanged(it) },
            paddingStart = 16.dp,
            height = 48.dp,
            labelFontSize = 13,
            valueFontSize = 16,
            trailingIcon = R.drawable.ic_close_circle,
            onClickTrailingIcon = {
                viewModel.onNameChanged("")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        ProfileOutlinedTextField(
            label = "Отчество",
            value = viewModel.lastname,
            onValueChanged = { viewModel.onLastnameChanged(it) },
            paddingStart = 16.dp,
            height = 48.dp,
            labelFontSize = 13,
            valueFontSize = 16,
            trailingIcon = R.drawable.ic_close_circle,
            onClickTrailingIcon = {
                viewModel.onNameChanged("")
            }
        )
        ProfileOutlinedTextField(
            label = "Город",
            value = viewModel.city,
            onValueChanged = { viewModel.onLastnameChanged(it) },
            paddingStart = 16.dp,
            height = 48.dp,
            labelFontSize = 13,
            valueFontSize = 16,
            trailingIcon = R.drawable.ic_close_circle,
            onClickTrailingIcon = {
                viewModel.onNameChanged("")
            }
        )
        ProfileOutlinedTextField(
            label = "Контакты",
            value = viewModel.contact,
            onValueChanged = { viewModel.onLastnameChanged(it) },
            paddingStart = 16.dp,
            height = 48.dp,
            labelFontSize = 13,
            valueFontSize = 16,
            trailingIcon = R.drawable.ic_close_circle,
            onClickTrailingIcon = {
                viewModel.onNameChanged("")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "О себе")
        TextField(
            placeholder = {
                MainText(
                    text = viewModel.aboutUser,
                    color = TextColor
                )
            },
            value = viewModel.aboutUser,
            onValueChange = { viewModel.onAboutChanged(it) },
            maxLines = 6,
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = TextColor,
                containerColor = SecondColour,
                cursorColor = Color.Black,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Опыт")
        TextField(
            placeholder = {
                MainText(
                    text = viewModel.experience,
                    color = TextColor
                )
            },
            value = viewModel.experience,
            onValueChange = { viewModel.onExperienceChanged(it) },
            maxLines = 6,
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.textFieldColors(
                textColor = TextColor,
                containerColor = SecondColour,
                cursorColor = Color.Black,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        CustomButton(
            text = "Сохранить"
        )
    }
}