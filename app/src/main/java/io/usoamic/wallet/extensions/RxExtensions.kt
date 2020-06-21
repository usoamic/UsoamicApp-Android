package io.usoamic.wallet.extensions

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import io.usoamic.wallet.BuildConfig
import java.util.concurrent.TimeUnit

fun <T> Single<T>.observeOnMain(): Single<T> = observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.subscribeOnIo(): Single<T> = subscribeOn(Schedulers.io())

fun <T> Single<T>.addSchedulers(): Single<T> = subscribeOnIo().observeOnMain()

fun <T> Single<T>.addDebugDelay(): Single<T> {
    if(BuildConfig.DEBUG) {
        return delay(2, TimeUnit.SECONDS)
    }
    return this
}
