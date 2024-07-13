package com.karim.core.util.network

sealed class Result<out R> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
}

inline fun <reified T> Result<T>.doIfSuccess(data: (T) -> Unit) {
    if (this is Result.Success) data(this.data)
}

inline fun <reified T> Result<T>.doIfError(error: (Exception) -> Unit) {
    if (this is Result.Error) error(this.exception)
}


