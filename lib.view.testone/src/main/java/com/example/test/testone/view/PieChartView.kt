package com.example.test.testone.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testone.util.dp2px
import com.example.test.testone.util.getAngleRadians

/**
 * hencoder06 饼状图
 * @author chengxiaobo
 * @time 2018/10/23 13:35
 */
class PieChartView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)


    private var paint: Paint = Paint()

    private var centerX = 0.0f
    private var centerY = 0.0f
    private var radiusLength = 0.0f //半径
    private var offsetLength = dp2px(5.0f)

    private lateinit var rectF: RectF
    private val arrayAngles = arrayOf(0.0f, 80.0f, 130.0f, 250.0f, 360.0f)
    private val colors = arrayOf("#00BFFF", "#f042ee", "#b9f042", "#ff0000")

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        centerX = width / 2.0f
        centerY = height / 2.0f

        radiusLength = width / 3.0f
        rectF = RectF(centerX - radiusLength, centerY - radiusLength, centerX + radiusLength, centerY + radiusLength)
    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        for (index in 1 until arrayAngles.size) {
            paint.color = Color.parseColor(colors[index - 1])

            //我的做法，没有老师的做法好。老师用到了canvas?.translate()
            if (index == 3) {
                val offsetCenterAngle = (arrayAngles[index] - arrayAngles[index - 1]) / 2.0f + arrayAngles[index - 1]
                val offsetCenterX = (Math.cos(getAngleRadians(offsetCenterAngle)) * offsetLength).toFloat() + centerX
                val offsetCenterY = (Math.sin(getAngleRadians(offsetCenterAngle)) * offsetLength).toFloat() + centerY
                val offsetRect = RectF(offsetCenterX - radiusLength, offsetCenterY - radiusLength, offsetCenterX + radiusLength, offsetCenterY + radiusLength)
                canvas?.drawArc(offsetRect, arrayAngles[index - 1], arrayAngles[index] - arrayAngles[index - 1], true, paint)
            } else {
                canvas?.drawArc(rectF, arrayAngles[index - 1], arrayAngles[index] - arrayAngles[index - 1], true, paint)
            }
        }

    }
}