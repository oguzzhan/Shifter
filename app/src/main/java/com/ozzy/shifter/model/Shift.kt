package com.ozzy.shifter.model

import com.google.gson.annotations.SerializedName
import com.ozzy.shifter.util.getTimeFromDate
import com.ozzy.shifter.util.getUserFriendlyDate


data class Shift(
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("starts_at")
    val startsAt: String? = null,
    @SerializedName("ends_at")
    val endsAt: String? = null,
    @SerializedName("job")
    val job: Job? = null,
    @SerializedName("earnings_per_hour")
    val earningsPerHour: Price? = null
) {
    fun getStartDate(): String? {
        return startsAt.getUserFriendlyDate()
    }

    fun getStartEndTimeText(): String? {
        return if (startsAt != null && endsAt != null) {
            "${startsAt.getTimeFromDate()} - ${endsAt.getTimeFromDate()}"
        } else {
            null
        }
    }
}