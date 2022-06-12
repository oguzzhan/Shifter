package com.ozzy.shifter.data

import com.ozzy.shifter.model.Shift
import com.ozzy.shifter.model.ShifterResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ShifterService {
    @GET("shifts")
    suspend fun fetchShiftList(
        @QueryMap filters: HashMap<String, String>?
    ): Response<ShifterResponse<Shift>>
}