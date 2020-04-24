package io.usoamic.wallet

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import io.usoamic.wallet.di.AppComponent
import io.usoamic.wallet.di.DaggerAppComponent
import io.usoamic.wallet.di.modules.UsoamicModule

class UsoamicWallet : Application() {
    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        component = buildDagger()
    }

    private fun buildDagger(): AppComponent {
        return DaggerAppComponent
            .builder()
            .usoamicModule(UsoamicModule())
            .build()
    }
    companion object {
        lateinit var component: AppComponent
    }
}