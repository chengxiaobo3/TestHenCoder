package com.example.test.testone.view

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testone.util.dp2px
import com.example.test.testone.util.getAngleRadians

/**
 * hencoder06 仪表盘
 * @author chengxiaobo
 * @time 2018/10/22 13:15
 */
class DashBoardView2 : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var paint: Paint = Paint()
    private var path: Path = Path()
    private var path2: Path = Path()

    private var centerX = 0.0f
    private var centerY = 0.0f

    private val START_ANGLE = 120.0f
    private val SWEEP_ANGLE = 300.0f
    private val PADDING_DP = 5.0f
    private val STROKE_DP = 3.0f
    //总共多少个格
    private val SUM = 40
    private val SUM_Big = 8
    //view的padding。否则圆弧会被截取
    private var padding: Int = dp2px(PADDING_DP).toInt()
    //刻度线的宽度和高度
    private val degreeWidth = dp2px(3.0f)
    private val degreeHeight = dp2px(8.0f)
    //圆弧的宽度
    private var strokePx: Float = dp2px(STROKE_DP)
    //指针开始的角度
    private var currentAngleIndex = 5
    private var currentAngle = START_ANGLE + (SWEEP_ANGLE / SUM) * currentAngleIndex
    private var needleLength: Int = 0

    private lateinit var rect: RectF

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        centerX = width / 2.0f
        centerY = height / 2.0f
        needleLength = (width / 3.0f).toInt()

        rect = RectF(padding.toFloat(), padding.toFloat(), width.toFloat() - padding, height.toFloat() - padding)
        path.addArc(rect, START_ANGLE, SWEEP_ANGLE)
        path2.addArc(rect, START_ANGLE, SWEEP_ANGLE)

        paint.isAntiAlias = true
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = strokePx

    }

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.color = Color.parseColor("#000000")

        //画圆弧
        canvas?.drawPath(path, paint)

        //画小刻度
        val dashPath = Path()
        dashPath.addRect(0.0f, 0.0f, degreeWidth, degreeHeight, Path.Direction.CW)
        val pathDashPathEffect = PathDashPathEffect(dashPath, (PathMeasure(path, false).length - degreeWidth) / SUM, 0.0f, PathDashPathEffect.Style.ROTATE)
        paint.pathEffect = pathDashPathEffect
        canvas?.drawPath(path2, paint)
        paint.pathEffect = null

        //画大刻度
        val dashPath2 = Path()
        dashPath2.addRect(0.0f, 0.0f, degreeWidth, degreeHeight * 1.5f, Path.Direction.CW)
        val pathDashPathEffect2 = PathDashPathEffect(dashPath2, (PathMeasure(path, false).length - degreeWidth) / SUM_Big, 0.0f, PathDashPathEffect.Style.ROTATE)
        paint.pathEffect = pathDashPathEffect2
        canvas?.drawPath(path2, paint)
        paint.pathEffect = null

        //画指针
        canvas?.save()
        paint.color = Color.parseColor("#ff0000")
        canvas?.translate(centerX, centerY)
        canvas?.drawLine(0.0f, 0.0f, needleLength * Math.cos(getAngleRadians(currentAngle)).toFloat(), needleLength * Math.sin(getAngleRadians(currentAngle)).toFloat(), paint)
        canvas?.restore()
        paint.color = Color.parseColor("#000000")
    }

    fun addCurrentIndex() {

        currentAngleIndex += 1
        if (currentAngleIndex > SUM - 1) {
            currentAngleIndex = 1
        }
        currentAngle = START_ANGLE + (SWEEP_ANGLE / SUM) * currentAngleIndex
        invalidate()
    }

}
