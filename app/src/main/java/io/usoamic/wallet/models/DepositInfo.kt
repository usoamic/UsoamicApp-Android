package io.usoamic.wallet.models

import android.graphics.Bitmap

data class DepositInfo(
    val address: String,
    val qrCode: Bitmap
)
