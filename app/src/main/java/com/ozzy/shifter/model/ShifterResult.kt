package com.ozzy.shifter.model

sealed class ShifterResult<out T> {
    class Loading<T> : ShifterResult<T>()

    data class Response<T>(val response: T) : ShifterResult<T>()

    data class Error<T>(
        val errorMessage: String? = null,
        val errorCode: Int? = null
    ) : ShifterResult<T>()

    companion object {
        fun <T> loading() = Loading<T>()

        fun <T> success(data: T) = Response(data)

        fun <T> defaultError() = Error<T>()

        fun <T> errorCode(code: Int?) = Error<T>(errorCode = code)
    }
}