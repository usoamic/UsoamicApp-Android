package io.usoamic.wallet.extensions

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import io.usoamic.wallet.BuildConfig
import java.util.concurrent.TimeUnit

fun <T> Single<T>.observeOnMain(): Single<T> = observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.subscribeOnIo(): Single<T> = observeOn(Schedulers.io())

fun <T> Single<T>.addDebugDelay(): Single<T> {
    if(BuildConfig.DEBUG) {
        return delay(2, TimeUnit.SECONDS)
    }
    return this
}
