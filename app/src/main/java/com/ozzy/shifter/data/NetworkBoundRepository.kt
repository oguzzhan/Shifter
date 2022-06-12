package com.ozzy.shifter.data

import android.util.Log
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

        if (apiResponse.isSuccessful
            && apiResponse.body() != null
            && remotePosts != null
        ) {
            val result = ShifterResult.success(apiResponse.body())
            result.response
            emit(result)

        } else if (apiResponse.errorBody() != null) {
            Log.d("Error", apiResponse.errorBody().toString())
            emit(ShifterResult.serviceError(apiResponse.errorBody().toString()))
        }
    }.catch { e ->
        Log.d("Internal Error", e.localizedMessage ?: "")
        emit(ShifterResult.internalError(e.localizedMessage ?: "Error"))
    }

    @MainThread
    protected abstract suspend fun fetchFromRemote(): Response<RESULT>
}