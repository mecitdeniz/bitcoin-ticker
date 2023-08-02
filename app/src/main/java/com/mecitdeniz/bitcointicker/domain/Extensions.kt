package com.mecitdeniz.bitcointicker.domain

fun String.isValidEmail(): Boolean {
    if(isEmpty()) return false
    return android.util.Patterns.EMAIL_ADDRESS.matcher(this).matches()
}

fun String.isValidPassword(): Boolean {
    if (isEmpty() || this.length < 6) return false
    return true
}