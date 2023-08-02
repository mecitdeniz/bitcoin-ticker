package com.mecitdeniz.bitcointicker.data

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.mecitdeniz.bitcointicker.common.Resource

interface AuthService {
    suspend fun createAccount(email: String, password: String): Resource<AuthResult>
    suspend fun signIn(email: String, password: String): Resource<AuthResult>
    fun signOut()
    fun isUserLoggedIn(): Boolean
    fun getUserAccount(): FirebaseUser?
}