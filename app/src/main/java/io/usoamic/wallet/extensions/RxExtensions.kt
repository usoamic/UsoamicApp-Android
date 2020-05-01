package io.usoamic.wallet.extensions

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

fun <T> Single<T>.observeOnMain() = observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.subscribeOnIo() = observeOn(Schedulers.io())
