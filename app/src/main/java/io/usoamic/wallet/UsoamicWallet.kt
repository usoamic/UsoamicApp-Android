package io.usoamic.wallet

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

class UsoamicWallet : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this);
    }
}