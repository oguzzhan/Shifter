package com.ozzy.shifter.util

import java.text.SimpleDateFormat
import java.util.*

const val FILTER_DATE_FORMAT = "yyyy-MM-dd"
const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss+hh:mm"
const val USER_FRIENDLY_DATE_FORMAT = "EEEE dd MMMM"
const val TIME_FORMAT = "HH:mm"

fun getCurrentDateFormatted(): String {
    val sdf = SimpleDateFormat(FILTER_DATE_FORMAT, Locale.getDefault())

    return sdf.format(Date())
}

fun String?.getTimeFromDate(): String? {
    return this?.let { date ->
        val sdf = SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault())
        val dateToConvert = sdf.parse(date)

        val sdfTime = SimpleDateFormat(TIME_FORMAT, Locale.getDefault())

        dateToConvert?.let { sdfTime.format(it) }
    }
}

fun String?.getUserFriendlyDate(): String? {
    return this?.let { date ->
        val sdf = SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault())
        val dateToConvert = sdf.parse(date)

        val sdfUserFriendly = SimpleDateFormat(USER_FRIENDLY_DATE_FORMAT, Locale.getDefault())

        dateToConvert?.let { sdfUserFriendly.format(it) }
    }
}