package com.ozzy.shifter.data

import androidx.annotation.MainThread
import com.ozzy.shifter.model.ShifterResult
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import retrofit2.Response

/**
 * Created by Oğuzhan Karacan on 30.08.2021.
 */
abstract class NetworkBoundRepository<RESULT> {
    fun asFlow() = flow {
        emit(ShifterResult.loading())

        val apiResponse = fetchFromRemote()
        val remotePosts = apiResponse.body()
        val responseBody = apiResponse.body()

        if (apiResponse.isSuccessful
            && responseBody != null
            && remotePosts != null
        ) {
            val result = ShifterResult.success(responseBody)
            result.response
            emit(result)

        } else if (apiResponse.errorBody() != null) {
            emit(ShifterResult.error(apiResponse.errorBody().toString()))
        }
    }.catch { e ->
        emit(ShifterResult.error(e.localizedMessage ?: "User friendly error"))
    }

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<RESULT>
}