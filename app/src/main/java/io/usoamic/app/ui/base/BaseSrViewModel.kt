package io.usoamic.app.ui.base

import androidx.lifecycle.MutableLiveData
import io.reactivex.Single
import io.usoamic.commons.crossplatform.models.common.base.ScreenTag
import io.usoamic.app.usecases.AppUseCases

open class BaseSrViewModel(mAppUseCases: AppUseCases, tag: ScreenTag) : BaseViewModel(mAppUseCases, tag) {
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