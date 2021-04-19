package io.usoamic.wallet.extensions

import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.usoamic.commons.crossplatform.extensions.subscribeOnIo

fun <T> Single<T>.observeOnMain(): Single<T> = observeOn(AndroidSchedulers.mainThread())
fun Completable.observeOnMain(): Completable = observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.addSchedulers(): Single<T> = subscribeOnIo().observeOnMain()

fun Completable.addSchedulers(): Completable = subscribeOnIo().observeOnMain()