package com.karim.medicine.ui.loginscreen

import android.view.ViewTreeObserver
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.karim.medicine.R
import com.karim.medicine.ui.theme.MedicineTheme

@Composable
fun LoginScreen(modifier: Modifier = Modifier, onLoginClick: (String) -> Unit) {
    val state = hiltViewModel<LoginViewModel>()
    val username by state.userName.collectAsStateWithLifecycle()
    val password by state.password.collectAsStateWithLifecycle()
    val isLoginButtonEnabled by state.isLoginButtonEnabled.collectAsStateWithLifecycle()
    val isPasswordVisible by state.isPasswordVisible.collectAsStateWithLifecycle()
    val keyboardHeight by rememberKeyboardHeight()
    Column(
        modifier = modifier.padding(bottom = keyboardHeight.dp).padding(horizontal = 36.dp).verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.login_asset),
            contentDescription = "login image",
            modifier = Modifier.size(220.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = username,
            onValueChange = state::onUserNameChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Enter your username or email address",
                    maxLines = 1
                )
            },
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),

            )
        TextField(
            value = password,
            onValueChange = state::onPasswordChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(
                    text = "Enter your password",
                    maxLines = 1
                )
            },
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                errorContainerColor = Color.Transparent,
                disabledContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
            ),
            visualTransformation = if (isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                val image = if (isPasswordVisible) {
                    R.drawable.baseline_visibility_24
                } else {
                    R.drawable.baseline_visibility_off_24
                }
                IconButton(
                    onClick = {
                        state.onPasswordVisibilityChange(!isPasswordVisible)
                    }
                ){
                    Icon(painter = painterResource(id = image), contentDescription = "password visibility")
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        FilledTonalButton(
            enabled = isLoginButtonEnabled,
            onClick = { onLoginClick(username) },
            modifier = Modifier.align(Alignment.End),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                contentColor = Color.Black,
                containerColor = Color(0xff7fe2de)
            )
        ) {
            Text(text = "Login")
        }
    }
}

@Composable
fun rememberKeyboardHeight(): State<Int> {
    val view = LocalView.current
    val density = LocalDensity.current
    val keyboardHeight = remember { mutableStateOf(0) }

    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            val rect = android.graphics.Rect()
            view.getWindowVisibleDisplayFrame(rect)
            val screenHeight = view.height
            val keypadHeight = screenHeight - rect.bottom
            keyboardHeight.value = if (keypadHeight > screenHeight * 0.15) {
                (keypadHeight / density.density).toInt() // Convert to dp
            } else {
                0
            }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose {
            view.viewTreeObserver.removeOnGlobalLayoutListener(listener)
        }
    }

    return keyboardHeight
}

