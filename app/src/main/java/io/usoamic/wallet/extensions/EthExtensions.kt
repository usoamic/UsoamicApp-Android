package io.usoamic.wallet.extensions

import org.web3j.crypto.Credentials

val Credentials.privateKey get() = ecKeyPair.privateKey.toString(16)