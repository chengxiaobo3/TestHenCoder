package com.example.test.testone.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testone.util.dp2px

/**
 * hencoder07 仪表盘
 * @author chengxiaobo
 * @time 2018/10/22 13:15
 */
class DashBoardView2 : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var paint: Paint = Paint()
    private var path: Path = Path()

    private var centerX: Int = 0
    private var centerY: Int = 0

    private val PADDING_DP = 5.0f
    private val STROKE_DP = 3.0f
    private val SUM = 20
    private var padding: Int = dp2px(PADDING_DP).toInt()
    private val degreeWidth = dp2px(5.0f)
    private val degreeHeight = dp2px(10.0f)
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
//        canvas?.drawArc(rect, 120.0f, 300.0f, false, paint)

        canvas?.drawPath(path, paint)

        val dashPath = Path()
        dashPath.addRect(0.0f, 0.0f, degreeWidth, degreeHeight, Path.Direction.CW)

        val pathDashPathEffect = PathDashPathEffect(dashPath, (PathMeasure(path, false).length -degreeWidth)/ SUM, 0.0f, PathDashPathEffect.Style.ROTATE)
        paint.pathEffect = pathDashPathEffect

        canvas?.drawPath(path, paint)
    }
}
