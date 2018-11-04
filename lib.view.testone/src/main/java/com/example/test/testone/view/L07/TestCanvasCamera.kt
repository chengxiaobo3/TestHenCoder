package com.example.test.testone.view.L07

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testone.R
import com.example.test.testone.util.dp2px
import com.example.test.testone.util.getBitmap

/**
 * 学习关于Canvas的Camera
 *
 * @author chengxiaobo
 * @time 2018/11/4 13:42
 */
class TestCanvasCamera : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var halfImageWidth = 0.0f
    private var centerX = 0.0f
    private var centerY = 0.0f

    private lateinit var sourceRect: Rect
    private lateinit var destRect: Rect
    private lateinit var bitmap: Bitmap
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera: Camera = Camera()

    init {
        camera.setLocation(0.0f, 0.0f, -12.0f)
        camera.rotateX(30.0f)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        halfImageWidth = width / 2.5f
        centerX = width / 2.0f
        centerY = height / 2.0f

        bitmap = getBitmap((halfImageWidth * 2).toInt(), R.drawable.rengwuxian)
        destRect = Rect((centerX - halfImageWidth).toInt(), (centerY - halfImageWidth).toInt(), (centerX + halfImageWidth).toInt(), (centerY + halfImageWidth).toInt())
        val bitmapWidth = if (bitmap.width > bitmap.height) bitmap.height else bitmap.width
        sourceRect = Rect(0, 0, bitmapWidth, bitmapWidth)


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()
        canvas?.translate(centerX, centerY)
        canvas?.rotate(-20.0f)
        canvas?.clipRect(-halfImageWidth * 2, -halfImageWidth * 2, halfImageWidth * 2, 0.0f)
        canvas?.rotate(20.0f)
        canvas?.translate(-centerX, -centerY)
        canvas?.drawBitmap(bitmap, sourceRect, destRect, paint)
        canvas?.restore()

        canvas?.save()
        canvas?.translate(centerX, centerY)
        canvas?.rotate(-20.0f)
        camera.applyToCanvas(canvas)
        canvas?.clipRect(-halfImageWidth * 2, 0.0f, halfImageWidth * 2, halfImageWidth * 2)
        canvas?.rotate(20.0f)
        canvas?.translate(-centerX, -centerY)
        canvas?.drawBitmap(bitmap, sourceRect, destRect, paint)
        canvas?.restore()

    }
}