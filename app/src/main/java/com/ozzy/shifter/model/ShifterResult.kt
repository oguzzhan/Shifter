package com.ozzy.shifter.model

sealed class ShifterResult<out T> {
    class Loading<T> : ShifterResult<T>()

    data class ShifterResponse<T>(val response: T) : ShifterResult<T>()

    data class Error<T>(val errorMessage: String) : ShifterResult<T>()

    companion object {
        fun <T> loading() = Loading<T>()

        fun <T> success(data: T) = ShifterResponse(data)

        fun <T> error(message: String) = Error<T>(message)
    }
}