package io.usoamic.wallet.exceptions

import java.math.BigInteger

fun BigInteger?.orZero() = this ?: BigInteger.ZERO