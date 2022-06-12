package com.ozzy.shifter.util

import android.content.Context
import com.ozzy.shifter.R

fun Int.handleErrorCode(context: Context): String {
    return if (this == 404) {
        context.getString(R.string.not_found_error_message)
    } else {
        context.getString(R.string.default_error_message)
    }
}