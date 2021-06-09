package io.usoamic.wallet.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hadilq.liveevent.LiveEvent
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.usoamic.commons.crossplatform.models.common.base.ErrorArguments
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.wallet.extensions.addSchedulers
import io.usoamic.wallet.extensions.emit
import io.usoamic.wallet.usecases.AppUseCases

open class BaseViewModel(
    private val mAppUseCases: AppUseCases,
    private val tag: ScreenTag
) : ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    val ldLogoutProgress = MutableLiveData<Boolean>()
    val ldProgress = MutableLiveData<Boolean>()
    val ldThrowable = MutableLiveData<ErrorArguments>()
    val ldError = LiveEvent<String>()
    val leLogout = LiveEvent<Boolean>()

    protected open fun throwError(throwable: Throwable) {
        throwable.printStackTrace()
        ldThrowable.value = ErrorArguments.Warning(throwable)
    }

    protected open fun throwErrorAndFinish(throwable: Throwable) {
        throwable.printStackTrace()
        ldThrowable.value = ErrorArguments.Fatal(throwable)
    }

    protected fun <T> Single<T>.addProgress(): Single<T> = addProgress(ldProgress)

    protected fun <T> Single<T>.addProgress(progress: MutableLiveData<Boolean>): Single<T> {
        return doOnSubscribe { progress.value = true }
                .doAfterTerminate { progress.value = false }
    }

    protected fun Completable.addProgress(): Completable = addProgress(ldProgress)

    protected fun Completable.addProgress(progress: MutableLiveData<Boolean>): Completable {
        return doOnSubscribe { progress.value = true }
            .doAfterTerminate { progress.value = false }
    }

    protected fun Disposable.addToDisposable() {
        compositeDisposable.add(this)
    }

    fun onViewCreated() {
        if(tag == ScreenTag.WALLET) {
            mAppUseCases.updateLastActionTime()
        }
    }

    fun onLogoutClick() {
        mAppUseCases.clearDb()
            .onErrorComplete()
            .andThen(mAppUseCases.removeAccount())
            .addSchedulers()
            .addProgress(ldLogoutProgress)
            .subscribe({
                onRemoveResult(true)
            }, {
                onRemoveResult(false)
            })
            .addToDisposable()
    }

    private fun onRemoveResult(isRemoved: Boolean) {
        mAppUseCases.removePreferences()
        leLogout.emit(isRemoved)
    }
    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}