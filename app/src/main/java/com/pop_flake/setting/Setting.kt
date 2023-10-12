package com.pop_flake.setting

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import com.google.android.material.snackbar.Snackbar
import com.pop_flake.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Composable
fun SettingComplaint() {

    val context = LocalContext.current
    val view = LocalView.current

    val darkMode = remember {
        mutableStateOf(false)
    }

    val name = remember {
        mutableStateOf("")
    }

    val namFocus = remember {
        FocusRequester()
    }

    val phone = remember {
        mutableStateOf("")
    }

    val phoneFocus = remember {
        FocusRequester()
    }

    val description = remember {
        mutableStateOf("")
    }

    val descriptionFocus = remember {
        FocusRequester()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {


        Switch(
            modifier = Modifier
                .align(Alignment.End),
            checked = darkMode.value,
            onCheckedChange = { darkMode.value = it })

        Text(
            modifier = Modifier
                .padding(0.sdp, 5.sdp, 5.sdp, 0.sdp)
                .align(Alignment.End),
            text = if (darkMode.value) "ON" else "OFF",
            fontSize = 13.ssp
        )

        Spacer(modifier = Modifier.padding(5.sdp))

        OutlinedTextField(modifier = Modifier
            .fillMaxWidth()
            .padding(5.sdp)
            .focusRequester(namFocus),
            value = name.value,
            onValueChange = { name.value = it },
            label = {
                Text(text = "Name", fontSize = 17.ssp, color = Color.Black)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = Color.Black
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.name),
                    contentDescription = "Image Name",
                    tint = colorResource(id = R.color.blue)
                )
            })

        Spacer(modifier = Modifier.padding(5.sdp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.sdp)
                .focusRequester(phoneFocus),
            value = phone.value,
            onValueChange = { phone.value = it },
            label = {
                Text(text = "Phone", fontSize = 17.ssp, color = Color.Black)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = Color.Black
            ),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.phone),
                    contentDescription = "Image Phone",
                    tint = colorResource(id = R.color.blue)
                )
            }, keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Phone)
        )

        Spacer(modifier = Modifier.padding(5.sdp))

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.sdp)
                .padding(5.sdp)
                .focusRequester(descriptionFocus),
            value = description.value,
            onValueChange = { description.value = it },
            label = {
                Text(text = "Description", fontSize = 17.ssp, color = Color.Black)
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = Color.Black,
                focusedBorderColor = Color.Black
            )
        )

        Spacer(modifier = Modifier.padding(5.sdp))

        Button(modifier = Modifier
            .align(Alignment.End)
            .padding(5.sdp), onClick = {
            validationOfWidgetAndSubmitComplaint(
                name.value,
                phone.value,
                description.value,
                view,
                context,
                namFocus,
                phoneFocus,
                descriptionFocus
            )
        }, content = {
            Text(
                text = "Submit",
                fontSize = 20.ssp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }, colors = ButtonDefaults.buttonColors(colorResource(id = R.color.black))
        )
    }
}


fun validationOfWidgetAndSubmitComplaint(
    name: String,
    phone: String,
    description: String,
    view: View,
    context: Context,
    nameFocus: FocusRequester,
    phoneFocus: FocusRequester,
    descriptionFocus: FocusRequester
) {

    when {
        name.isNullOrEmpty() -> {
            Toast.makeText(context, "من فضلك ادخل الإسم", Toast.LENGTH_SHORT).show()
            nameFocus.requestFocus()
            return
        }

        phone.isNullOrEmpty() -> {
            Toast.makeText(context, "من فضلك ادخل رقم الموبايل", Toast.LENGTH_SHORT).show()
            phoneFocus.requestFocus()
            return
        }

        description.isNullOrEmpty() -> {
            Toast.makeText(context, "من فضلك ادخل الوصف", Toast.LENGTH_SHORT).show()
            descriptionFocus.requestFocus()
            return
        }

        else -> {
            Snackbar.make(view, "تم تسجيل الشكوي سيتم الرد في أقرب وقت", Snackbar.LENGTH_SHORT)
                .show()
            name.let {
                it.isEmpty()
            }
        }
    }
}
