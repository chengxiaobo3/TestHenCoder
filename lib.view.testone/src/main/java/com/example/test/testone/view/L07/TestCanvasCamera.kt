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

    private var imageWidth = 0.0f
    private var centerX = 0.0f
    private var centerY = 0.0f

    private lateinit var sourceRect1: Rect
    private lateinit var sourceRect2: Rect
    private lateinit var destRect1: Rect
    private lateinit var destRect2: Rect
    private lateinit var bitmap: Bitmap
    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val camera: Camera = Camera()

    init {
        camera.setLocation(0.0f, 0.0f, -dp2px(12.0f))
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        imageWidth = width / 2.5f
        centerX = width / 2.0f
        centerY = height / 2.0f

        bitmap = getBitmap(imageWidth.toInt(), R.drawable.cheng)
        destRect1 = Rect((centerX - imageWidth).toInt(), (centerY - imageWidth).toInt(), (centerX + imageWidth).toInt(), (centerY).toInt())
        destRect2 = Rect((centerX - imageWidth).toInt(), (centerY).toInt(), (centerX + imageWidth).toInt(), (centerY + imageWidth).toInt())
        val bitmapWidth = if (bitmap.width > bitmap.height) bitmap.height else bitmap.width
        sourceRect1 = Rect(0, 0, bitmapWidth, bitmapWidth / 2)
        sourceRect2 = Rect(0, bitmapWidth / 2, bitmapWidth, bitmapWidth)


    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawBitmap(bitmap, sourceRect1, destRect1, paint)

        camera.save()
        canvas?.save()
        canvas?.translate(centerX, centerY)
        camera.rotateX(45.0f)
        camera.applyToCanvas(canvas)
        canvas?.translate(-centerX, -centerY)
        canvas?.drawBitmap(bitmap, sourceRect2, destRect2, paint)
        canvas?.restore()
        camera.restore()

    }
}