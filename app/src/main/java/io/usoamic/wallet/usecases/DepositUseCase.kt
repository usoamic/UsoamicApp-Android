package io.usoamic.wallet.usecases

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import io.reactivex.rxjava3.core.Single
import io.usoamic.wallet.domain.repositories.PreferencesRepository
import io.usoamic.wallet.extensions.addDebugDelay
import javax.inject.Inject

class DepositUseCase @Inject constructor(
    private val mPreferencesRepository: PreferencesRepository
) {
    fun getAddress(): String {
        return mPreferencesRepository.getAddress()
    }

    fun generateQrCode(content: String): Single<Bitmap> {
        return Single.fromCallable {
            val writer = QRCodeWriter()
            val bitMatrix = writer.encode(content, BarcodeFormat.QR_CODE, 512, 512)
            val width = bitMatrix.width
            val height = bitMatrix.height
            val bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
            for (x in 0 until width) {
                for (y in 0 until height) {
                    bitmap.setPixel(x, y, if (bitMatrix.get(x, y)) Color.BLACK else Color.WHITE)
                }
            }
            bitmap
        }.addDebugDelay()
    }
}