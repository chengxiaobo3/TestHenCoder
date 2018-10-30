package com.example.test.testone.view.L07

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testone.R
import com.example.test.testone.util.getBitmap

class BreakTextView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private lateinit var bitmap: Bitmap
    private var picWidth: Float = 0.0f
    private var picHeight: Float = 0.0f
    private lateinit var sourceRect: Rect
    private lateinit var destRect: Rect
    private val paint = Paint()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        picWidth = width / 5.0f
        bitmap = getBitmap(picWidth.toInt(), R.drawable.cheng)
        picHeight = picWidth * bitmap.height / bitmap.width
        sourceRect = Rect(0, 0, bitmap.width, bitmap.height)
        destRect = Rect(width - picWidth.toInt(), height / 3, width, (height / 3.0f + picHeight).toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawBitmap(bitmap, sourceRect, destRect, paint)
    }


}