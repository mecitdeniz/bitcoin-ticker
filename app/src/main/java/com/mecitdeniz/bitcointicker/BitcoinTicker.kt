package com.mecitdeniz.bitcointicker

import android.app.Application
import com.google.firebase.FirebaseApp

class BitcoinTicker: Application() {
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(applicationContext)
    }
}