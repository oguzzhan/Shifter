package com.ozzy.shifter.util

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

const val DEFAULT_DATE_FORMAT = "yyyy-MM-dd"

fun getCurrentDateFormatted(): String {
    val sdf = SimpleDateFormat(DEFAULT_DATE_FORMAT, Locale.getDefault())
    val currentDate = sdf.format(Date())
    Log.d("CurrentDate", "getCurrentTimeFormatted: $currentDate")
    return currentDate
}