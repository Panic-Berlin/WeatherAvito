package com.example.weatheravito.utils

sealed class RequestResult<out T> {
    data class Success<T>(val data: T) : RequestResult<T>()
    sealed class Failed : RequestResult<Nothing>() {
        data class Error(val throwable: Throwable) : Failed()
    }
}

inline fun <T> safeRequest(block: () -> T): RequestResult<T> = try {
    RequestResult.Success(block.invoke())
} catch (exception: Exception) {
    RequestResult.Failed.Error(exception)
}
