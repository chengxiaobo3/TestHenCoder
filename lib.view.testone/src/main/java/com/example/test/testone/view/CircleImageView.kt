package com.example.test.testone.view

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testone.R
import com.example.test.testone.util.dp2px
import com.example.test.testone.util.getBitmap

/**
 * hencoder07 圆形图片
 * @author chengxiaobo
 * @time 2018/10/22 16:15
 */
class CircleImageView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var centerX = 0.0f
    private var centerY = 0.0f
    private var imageWidth = 0.0f

    private var padding: Int = dp2px(5.0f).toInt()

    private lateinit var paint: Paint
    private var bitmap: Bitmap
    private lateinit var imageViewRect: RectF
    private lateinit var bitmapRect: Rect
    private var xFermode: Xfermode

    init {
        bitmap = getBitmap(imageWidth.toInt(), R.drawable.cheng)
        xFermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        paint = Paint()
        centerX = width / 2.0f
        centerY = height / 2.0f

        imageWidth = width / 3.0f

        bitmapRect = Rect(0, 0, bitmap.width, bitmap.height)
        imageViewRect = RectF(centerX - imageWidth, centerY - imageWidth, centerX + imageWidth, centerY + imageWidth)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawCircle(centerX, centerY, imageWidth + padding, paint)
        val saved = canvas?.saveLayer(imageViewRect, paint)
        canvas?.drawCircle(centerX, centerY, imageWidth, paint)
        paint.xfermode = xFermode
        canvas?.drawBitmap(bitmap, bitmapRect, imageViewRect, paint)
        paint.xfermode = null
        saved?.let {
            canvas.restoreToCount(saved)
        }
    }
}