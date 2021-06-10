package io.usoamic.app.models

import android.graphics.Bitmap

data class DepositInfo(
    val address: String,
    val qrCode: Bitmap
)
