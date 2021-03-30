package com.github.ddnosh.arabbit.module.glide.support

import android.content.Context
import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapShader
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Shader
import com.bumptech.glide.load.Key

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool

import java.security.MessageDigest
import jp.wasabeef.glide.transformations.BitmapTransformation

/**
 * @author  ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
class CircleBorderTransformation : BitmapTransformation {

    private val mBorderPaint = Paint()
    private var mBorderWidth: Float = 0.0f

    constructor() {}

    constructor(borderWidth: Int, borderColor: Int) {
        val density = Resources.getSystem().displayMetrics.density
        mBorderWidth = (borderWidth * density + 0.5f).toInt().toFloat()
        mBorderPaint.isDither = true
        mBorderPaint.isAntiAlias = true
        mBorderPaint.color = borderColor
        mBorderPaint.style = Paint.Style.STROKE
        mBorderPaint.strokeWidth = mBorderWidth
    }

    override fun transform(context: Context, pool: BitmapPool, source: Bitmap, outWidth: Int, outHeight: Int): Bitmap {
        val size = (Math.min(source.width, source.height) - mBorderWidth / 2).toInt()
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2
        val squared = Bitmap.createBitmap(source, x, y, size, size)
        var result: Bitmap? = pool.get(size, size, Bitmap.Config.ARGB_8888)
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        }
        val canvas = Canvas(result!!)
        val paint = Paint()
        paint.shader = BitmapShader(squared, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        paint.isAntiAlias = true
        val r = size / 2f
        canvas.drawCircle(r, r, r, paint)
        if (mBorderPaint != null) {
            val borderRadius = r - mBorderWidth / 2
            canvas.drawCircle(r, r, borderRadius, mBorderPaint)
        }
        return result
    }

    override fun toString(): String {
        return "CircleBorderTransformation()"
    }

    override fun equals(o: Any?): Boolean {
        return o is CircleBorderTransformation
    }

    override fun hashCode(): Int {
        return ID.hashCode()
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
    }

    companion object {

        private val VERSION = 1
        private val ID = "com.github.ddnosh.arabbit.CircleBorderTransformation.$VERSION"
        private val ID_BYTES = ID.toByteArray(Key.CHARSET)
    }
}
