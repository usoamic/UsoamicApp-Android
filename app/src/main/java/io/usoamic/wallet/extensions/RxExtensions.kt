package io.usoamic.wallet.extensions

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.usoamic.commons.crossplatform.extensions.subscribeOnIo

fun <T> Single<T>.observeOnMain(): Single<T> = observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.addSchedulers(): Single<T> = subscribeOnIo().observeOnMain()