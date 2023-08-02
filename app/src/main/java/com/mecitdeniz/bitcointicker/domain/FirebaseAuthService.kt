package com.mecitdeniz.bitcointicker.domain

import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mecitdeniz.bitcointicker.common.Resource
import com.mecitdeniz.bitcointicker.data.AuthService
import kotlinx.coroutines.CompletableDeferred

class FirebaseAuthService : AuthService {

    private val auth by lazy {
        Firebase.auth
    }

    override suspend fun createAccount(email: String, password: String): Resource<AuthResult> {
        val def = CompletableDeferred<Resource<AuthResult>>()
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                def.complete(
                    if (it.isSuccessful) Resource.Success(data = it.result) else Resource.Error(
                        message = it.exception?.localizedMessage ?: "Unable to create the account! Please try again later."
                    )
                )
            }
        return def.await()
    }

    override suspend fun signIn(email: String, password: String): Resource<AuthResult> {
        val def = CompletableDeferred<Resource<AuthResult>>()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                def.complete(
                    if (it.isSuccessful) Resource.Success(data = it.result) else Resource.Error(
                        message = it.exception?.localizedMessage ?: "Unable to sign in! Please try again later."
                    )
                )
            }
        return def.await()
    }

    override fun isUserLoggedIn() = auth.currentUser != null

    override fun getUserAccount() = auth.currentUser

    override fun signOut() {
        auth.signOut()
    }
}