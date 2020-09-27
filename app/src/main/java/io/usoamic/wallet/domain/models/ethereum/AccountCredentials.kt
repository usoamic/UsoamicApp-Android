package io.usoamic.wallet.domain.models.ethereum

import io.usoamic.wallet.extensions.privateKey
import org.web3j.crypto.Credentials

data class AccountCredentials(
    val address: String,
    val privateKey: String
)

fun Credentials.toDomain() = AccountCredentials(
    address,
    privateKey
)