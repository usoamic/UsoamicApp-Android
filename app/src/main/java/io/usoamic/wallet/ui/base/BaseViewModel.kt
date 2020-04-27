package io.usoamic.wallet.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.usoamic.wallet.domain.models.base.ErrorArguments

open class BaseViewModel : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val ldProgress = MutableLiveData<Boolean>()
    val ldThrowable = MutableLiveData<ErrorArguments>()
    val ldError = MutableLiveData<String>()

    open fun throwError(throwable: Throwable) {
        ldThrowable.value = ErrorArguments.Warning(throwable)
    }

    open fun throwErrorAndFinish(throwable: Throwable) {
        ldThrowable.value = ErrorArguments.Fatal(throwable)
    }

    fun <T> Single<T>.addProgress(): Single<T> {
        return doOnSubscribe { ldProgress.value = true }
            .doAfterTerminate { ldProgress.value = false }
    }

    fun Disposable.addToDisposable() {
        compositeDisposable.add(this)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}