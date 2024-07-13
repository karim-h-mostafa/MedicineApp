package com.karim.core.util.common

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

object ErrorManager {
    private val _error = MutableStateFlow<String?>(null)
    val error get() = _error.asStateFlow()
    fun setError(error: String?) {
        _error.value = error
    }
    fun clearError() {
        _error.value = null
    }
}
