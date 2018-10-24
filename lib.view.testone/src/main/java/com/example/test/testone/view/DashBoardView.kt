package com.example.test.testone.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testone.util.dp2px

/**
 * hencoder06 仪表盘
 * @author chengxiaobo
 * @time 2018/10/22 13:15
 */
class DashBoardView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var paint: Paint = Paint()
    private var path: Path = Path()

    private var centerX: Int = 0
    private var centerY: Int = 0

    private val PADDING_DP = 5.0f
    private val STROKE_DP = 3.0f
    private var padding: Int = dp2px(PADDING_DP).toInt()
    private var strokePx: Float = dp2px(STROKE_DP)

    private lateinit var rect: RectF

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        centerX = width / 2
        centerY = height / 2

        rect = RectF(padding.toFloat(), padding.toFloat(), width.toFloat() - padding, height.toFloat() - padding)
        path.addArc(rect, 120.0f, 300.0f)

        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokePx

    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.pathEffect = null
        canvas?.drawPath(path, paint)

        val dashPathEffect = DashPathEffect(floatArrayOf(dp2px(5.0f), dp2px(10.0f)), 0.0f)
        paint.strokeWidth = strokePx * 3
        paint.pathEffect = dashPathEffect

        canvas?.drawPath(path, paint)
        paint.pathEffect = null
    }
}
