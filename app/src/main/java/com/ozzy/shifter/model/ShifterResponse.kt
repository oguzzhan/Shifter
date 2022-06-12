package com.ozzy.shifter.model

import com.google.gson.annotations.SerializedName

data class ShifterResponse<R>(
    @SerializedName("data") val listResponse: List<R>
)