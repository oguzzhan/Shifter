package com.ozzy.shifter.util

class ShiftListFilterHelper {
    val filterMap = hashMapOf<String, String>()

    companion object {
        const val FILTER_KEY_DATE = "filter[date]"
    }


    fun setFilterForToday() {
        val currentDate = getCurrentDateFormatted()

        filterMap[FILTER_KEY_DATE] = currentDate
    }
}