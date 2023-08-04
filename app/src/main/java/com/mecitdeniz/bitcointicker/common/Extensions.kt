package com.mecitdeniz.bitcointicker.common

import java.text.DecimalFormat

fun String.isValidEmail(): Boolean {
    if (isEmpty()) return false
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    if (isEmpty() || this.length < 6) return false
    return true
}

fun Double.toFixedString(fractionDigits: Int): String {
    val df = DecimalFormat()
    df.maximumFractionDigits = fractionDigits
    return df.format(this)
}