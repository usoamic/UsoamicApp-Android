package io.usoamic.wallet.domain.models.deposit

import android.graphics.Bitmap

data class DepositInfo(
    val address: String,
    val qrCode: Bitmap
)