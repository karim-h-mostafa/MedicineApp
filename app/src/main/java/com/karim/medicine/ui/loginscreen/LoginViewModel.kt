package com.karim.medicine.ui.loginscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karim.core.util.common.ErrorManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {
    private val _userName = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _isLoginButtonEnabled = MutableStateFlow(false)
    private val _isPasswordVisible = MutableStateFlow(false)
    val userName = _userName.asStateFlow()
    val password = _password.asStateFlow()
    val isLoginButtonEnabled = _isLoginButtonEnabled.asStateFlow()
    val isPasswordVisible = _isPasswordVisible.asStateFlow()

    fun onPasswordVisibilityChange(isPasswordVisible: Boolean) {
        _isPasswordVisible.value = isPasswordVisible
    }
    fun onUserNameChange(userName: String) {
        _userName.value = userName
        _isLoginButtonEnabled.value = userName.isNotEmpty() && _password.value.isNotEmpty()
    }
    fun onPasswordChange(password: String) {
        _password.value = password
        _isLoginButtonEnabled.value = _userName.value.isNotEmpty() && _password.value.isNotEmpty()
    }


}