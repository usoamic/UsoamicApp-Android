package io.usoamic.wallet.di.modules

import dagger.Module
import dagger.Provides
import io.usoamic.usoamickt.core.Usoamic
import io.usoamic.usoamickt.enumcls.NetworkType
import io.usoamic.usoamickt.enumcls.NodeProvider
import io.usoamic.wallet.BuildConfig
import io.usoamic.wallet.UsoamicWallet
import javax.inject.Singleton

@Module
class UsoamicModule {
    @Provides
    @Singleton
    fun provideContract(): Usoamic {
        return Usoamic(
            BuildConfig.ACCOUNT_FILENAME,
            UsoamicWallet.appInfo.dataDir,
            NetworkType.valueOf(BuildConfig.FLAVOR),
            NodeProvider.valueOf(
                BuildConfig.NODE_PROVIDER,
                BuildConfig.PROJECT_ID
            )
        )
    }
}