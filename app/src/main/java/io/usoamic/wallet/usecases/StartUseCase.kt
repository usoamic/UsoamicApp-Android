package io.usoamic.wallet.usecases

import io.reactivex.rxjava3.core.Single
import io.usoamic.wallet.domain.repositories.UserRepository
import javax.inject.Inject

class StartUseCase @Inject constructor(
    private val mUserRepository: UserRepository
) {
    fun hasAccount(): Single<Boolean> {
        return mUserRepository.hasAccount()
    }
}