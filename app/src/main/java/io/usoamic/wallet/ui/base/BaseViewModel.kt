package io.usoamic.wallet.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.usoamic.commons.crossplatform.models.base.ErrorArguments
import io.usoamic.commons.crossplatform.models.base.ScreenTag
import io.usoamic.wallet.usecases.AppUseCases

open class BaseViewModel(
    private val mAppUseCases: AppUseCases,
    private val tag: ScreenTag
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val ldProgress = MutableLiveData<Boolean>()
    val ldThrowable = MutableLiveData<ErrorArguments>()
    val ldError = MutableLiveData<String>()

    protected open fun throwError(throwable: Throwable) {
        throwable.printStackTrace()
        ldThrowable.value = ErrorArguments.Warning(throwable)
    }

    protected open fun throwErrorAndFinish(throwable: Throwable) {
        throwable.printStackTrace()
        ldThrowable.value = ErrorArguments.Fatal(throwable)
    }

    protected fun <T> Single<T>.addProgress(): Single<T> {
        return doOnSubscribe { ldProgress.value = true }
                .doAfterTerminate { ldProgress.value = false }
    }

    protected fun Disposable.addToDisposable() {
        compositeDisposable.add(this)
    }

    fun onViewCreated() {
        if(tag == ScreenTag.WALLET) {
            mAppUseCases.updateLastActionTime()
        }
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}