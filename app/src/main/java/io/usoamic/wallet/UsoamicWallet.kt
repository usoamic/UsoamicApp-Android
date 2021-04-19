package io.usoamic.wallet

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import com.jakewharton.threetenabp.AndroidThreeTen
import io.usoamic.commons.crossplatform.UsoamicCommonsConfig
import io.usoamic.wallet.di.AppComponent
import io.usoamic.wallet.di.DaggerAppComponent
import io.usoamic.wallet.di.modules.UsoamicModule
import org.bouncycastle.jce.provider.BouncyCastleProvider
import java.security.Security

class UsoamicWallet : Application() {
    override fun onCreate() {
        super.onCreate()

        UsoamicCommonsConfig.DEBUG = BuildConfig.DEBUG

        appContext = applicationContext
        AndroidThreeTen.init(this)
        setupBouncyCastle()
        component = buildDagger()
    }

    private fun setupBouncyCastle() {
        //Source: https://github.com/web3j/web3j/issues/915
        val provider = Security.getProvider(BouncyCastleProvider.PROVIDER_NAME)
            ?: // Web3j will set up the provider lazily when it's first used.
            return
        if (provider.javaClass == BouncyCastleProvider::class.java) {
            // BC with same package name, shouldn't happen in real life.
            return
        }
        // Android registers its own BC provider. As it might be outdated and might not include
        // all needed ciphers, we substitute it with a known BC bundled in the app.
        // Android's BC has its package rewritten to "com.android.org.bouncycastle" and because
        // of that it's possible to have another BC implementation loaded in VM.
        Security.removeProvider(BouncyCastleProvider.PROVIDER_NAME)
        Security.insertProviderAt(BouncyCastleProvider(), 1)
    }

    private fun buildDagger(): AppComponent {
        return DaggerAppComponent
            .builder()
            .usoamicModule(UsoamicModule())
            .build()
    }
    companion object {
        lateinit var appContext: Context
        lateinit var component: AppComponent
        val appInfo: ApplicationInfo get() = appContext.applicationInfo
    }
}