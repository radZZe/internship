package com.example.internship.ui.registration


import android.content.ContentResolver
import android.content.Intent
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.internship.R
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonColors
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.internship.ui.theme.ButtonColour
import com.example.internship.ui.theme.MainFont
import com.example.internship.ui.theme.SecondColour
import com.example.internship.ui.theme.TextColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonalDataScreen(
    viewModel: PersonalDataViewModel = hiltViewModel()
) {
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
    var text1 = ""
    var text2 = ""
    if (viewModel.status == "Стажёр") {
        text1 = "Расскажите о себе"
        text2 = "Расскажите о своем опыте работы"
    } else if (viewModel.status == "Работодатель") {
        text1 = "Расскажите о своей компании"
        text2 = "Расскажите о своем опыте найма стажёров"
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MainText(text = "Создайте аккаунт", fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Color.Black)
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
        Spacer(modifier = Modifier.height(16.dp))
        ProfileOutlinedTextField(
            label = "Email",
            value = viewModel.city,
            onValueChanged = { viewModel.onEmailChanged(it) },
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
            label = "Город",
            value = viewModel.city,
            onValueChanged = { viewModel.onCityChanged(it) },
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
            label = "Пароль",
            value = viewModel.password,
            onValueChanged = { viewModel.onPasswordChanged(it) },
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
            label = "Повторите пароль",
            value = viewModel.password2,
            onValueChanged = { viewModel.onPassword2Changed(it) },
            paddingStart = 16.dp,
            height = 48.dp,
            labelFontSize = 13,
            valueFontSize = 16,
            trailingIcon = R.drawable.ic_close_circle,
            onClickTrailingIcon = {
                viewModel.onNameChanged("")
            }
        )
        CustomRadioButton(viewModel) { viewModel.onStatusChanged(it) }
        if (viewModel.status == "Работодатель") {
            ProfileOutlinedTextField(
                label = "Организация",
                value = viewModel.password2,
                onValueChanged = { viewModel.onPassword2Changed(it) },
                paddingStart = 16.dp,
                height = 48.dp,
                labelFontSize = 13,
                valueFontSize = 16,
                trailingIcon = R.drawable.ic_close_circle,
                onClickTrailingIcon = {
                    viewModel.onOrganizationChanged("")
                }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        ProfileOutlinedTextField(
            label = "Контакт для связи",
            value = viewModel.password2,
            onValueChanged = { viewModel.onContactChanged(it) },
            paddingStart = 16.dp,
            height = 48.dp,
            labelFontSize = 13,
            valueFontSize = 16,
            trailingIcon = R.drawable.ic_close_circle,
            onClickTrailingIcon = {
                viewModel.onContactChanged("")
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        if (!viewModel.status.isNullOrBlank()) {
            TextField(
                placeholder = {
                    MainText(
                        text = text1,
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
            TextField(
                placeholder = {
                    MainText(
                        text = text2,
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
        }
        CustomButton(
            modifier = Modifier,
            text = "Далее",
            onCLick = {}
        )
    }


}

@Composable
fun ProfileOutlinedTextField(
    label: String,
    value: String,
    onValueChanged: (String) -> Unit,
    paddingStart: Dp,
    height: Dp,
    trailingIcon: Int? = null,
    onClickTrailingIcon: () -> Unit,
    labelFontSize: Int,
    valueFontSize: Int,
) {
    val lineHeightSp: TextUnit = labelFontSize.sp
    val lineHeightDp: Dp = with(LocalDensity.current) {
        lineHeightSp.toDp()
    }
    Box(
        Modifier
            .fillMaxWidth()
            .height(height)
    ) {
        Row(
            modifier = Modifier
                .align(Alignment.TopStart)
                .zIndex(3f)
        ) {
            Spacer(modifier = Modifier.width(paddingStart))
            Box(
                modifier = Modifier
                    .background(Color.White)
                    .zIndex(4f)
                    .padding(horizontal = 2.dp)
            ) {
                MainText(text = label, color = ButtonColour)
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height - lineHeightDp.div(1.5f))
                .clip(
                    RoundedCornerShape(9.dp)
                )
                .border(
                    1.dp, ButtonColour, RoundedCornerShape(9.dp)
                )
                .background(Color.White)
                .align(Alignment.BottomCenter)
                .zIndex(1f),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(end = paddingStart),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = value,
                    onValueChange = { onValueChanged(it) },
                    modifier = Modifier.padding(start = paddingStart),
                    singleLine = true,
                    textStyle = TextStyle(
                        fontSize = valueFontSize.sp,
                        fontFamily = MainFont
                    )
                )
                if (trailingIcon != null) Image(
                    painter = painterResource(id = trailingIcon),
                    contentDescription = null,
                    modifier = Modifier.clickable(
                        interactionSource = MutableInteractionSource(),
                        indication = null
                    ) { onClickTrailingIcon() })
            }

        }
    }
}

@Composable
fun CustomRadioButton(
    viewModel: PersonalDataViewModel,
    stateChange: (String) -> Unit
) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        viewModel.statuses.forEach { option ->
            Row (modifier = Modifier.wrapContentSize(), verticalAlignment = Alignment.CenterVertically) {
                MainText(text = option, color = Color.Black)
                RadioButton(
                    selected = viewModel.status == option,
                    onClick = {
                        stateChange(option)
                    },
                    colors = RadioButtonDefaults.colors(
                        selectedColor = ButtonColour, unselectedColor = Color.Gray
                    )
                )
                Spacer(modifier = Modifier.width(20.dp))
            }
        }
    }
}