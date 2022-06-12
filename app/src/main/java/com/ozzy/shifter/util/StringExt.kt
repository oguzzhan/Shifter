package com.ozzy.shifter.util

import java.util.*

fun String?.getCurrencySymbol() = this?.let {
    Currency.getInstance(this).symbol
}
