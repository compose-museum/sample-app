import android.graphics.Bitmap
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Matrix
import android.graphics.Paint


/**
 * 位图缩放
 */
fun Bitmap.scaleTo(desWidth: Float, desHeight: Float): Bitmap {
    val scalex = desWidth / width
    val scaley = desHeight / height
    val matrix = Matrix().apply { postScale(scalex, scaley) }
    return Bitmap.createBitmap(this, 0, 0, width, height, matrix, true).also { this.recycle() }
}

/**
 * 位图灰度
 */
fun Bitmap.toGrayscale(): Bitmap {
    val bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val c = android.graphics.Canvas(bmpGrayscale)
    val paint = Paint()
    val cm = ColorMatrix()
    cm.setSaturation(0f)
    val f = ColorMatrixColorFilter(cm)
    paint.colorFilter = f
    c.drawBitmap(this, 0f, 0f, paint)
    return bmpGrayscale
}

