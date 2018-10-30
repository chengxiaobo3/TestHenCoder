package com.example.test.testone.view.l07

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testone.util.dp2px

/**
 * hencoder07 居中的TextView
 * @author chengxiaobo
 * @time 2018/10/22 16:15
 */
class CircleTextView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var radius = 0.0f
    private var centerX = 0.0f
    private var centerY = 0.0f
    private var strokWidth = dp2px(10.0f)
    private var paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private var rect = RectF()
    private var rectForText = Rect()
    private val text = "abcdefg"

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        radius = width / 3.0f
        centerX = width / 2.0f
        centerY = height / 2.0f
        rect = RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.reset()
        paint.strokeWidth = strokWidth
        paint.style = Paint.Style.STROKE
        paint.color = Color.parseColor("#afafaf")
        canvas?.drawCircle(centerX, centerY, radius, paint)
        paint.color = Color.parseColor("#ff0000")
        paint.strokeCap = Paint.Cap.ROUND
        canvas?.drawArc(rect, 60.0f, 300.0f, false, paint)
        paint.color = Color.parseColor("#ff8907")
        paint.textSize = dp2px(30.0f)
        paint.style = Paint.Style.FILL;
        paint.textAlign = Paint.Align.CENTER
        paint.getTextBounds(text, 0, text.length, rectForText)
        canvas?.drawText("abc", centerX, centerY - (rectForText.bottom + rectForText.top) / 2.0f, paint)
        paint.textAlign=Paint.Align.LEFT
//        canvas?.drawText("abcde", 0.0f, height.toFloat(), paint)

    }
}