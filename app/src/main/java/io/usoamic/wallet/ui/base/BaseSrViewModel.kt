package io.usoamic.wallet.ui.base

import androidx.lifecycle.MutableLiveData
import io.reactivex.Single

open class BaseSrViewModel : BaseViewModel() {
    val ldShowSwipeRefresh = MutableLiveData<Boolean>()

    protected fun <T> Single<T>.addProgress(force: Boolean): Single<T> {
        return if(force) {
            doAfterTerminate {
                ldShowSwipeRefresh.value = false
            }
        } else {
            addProgress()
        }
    }

    open fun onRefresh() = Unit
}