package com.ozzy.shifter.model

import com.google.gson.annotations.SerializedName
import com.ozzy.shifter.util.getCurrencySymbol

data class Price(
    @SerializedName("amount")
    val amount: Float? = null,
    @SerializedName("currency")
    val currency: String? = null
) {
    fun getAmountWithCurrencySymbol(): String =
        "${currency.getCurrencySymbol()} ${amount?.toString()}"
}
