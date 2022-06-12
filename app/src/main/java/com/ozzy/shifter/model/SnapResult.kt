package com.ozzy.shifter.model

sealed class ShifterResult<out T> {
    class Loading<T> : ShifterResult<T>()

    data class ShifterResponse<T>(val response: T) : ShifterResult<T>()

    data class ServiceError<T>(val errorMessage: String) : ShifterResult<T>()

    data class InternalError<T>(val errorMessage: String) : ShifterResult<T>()

    companion object {
        fun <T> loading() = Loading<T>()

        fun <T> success(data: T) = ShifterResponse(data)

        fun <T> serviceError(message: String) = ServiceError<T>(message)

        fun <T> internalError(message: String) = InternalError<T>(message)
    }
}