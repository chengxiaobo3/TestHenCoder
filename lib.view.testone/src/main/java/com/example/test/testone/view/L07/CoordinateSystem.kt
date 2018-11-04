package com.example.test.testone.view.L07

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * hencoder07 坐标系
 * @author chengxiaobo
 * @time 2018/11/3 18:15
 */
class CoordinateSystem :View{

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)
    private val paint=Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.style = Paint.Style.STROKE;

        canvas?.save()
        canvas?.translate(100.0f,0.0f)
        canvas?.drawRect(0.0f,0.0f,100.0f,100.0f,paint)
        canvas?.restore();

        canvas?.save()
        canvas?.rotate(45.0f)
        canvas?.translate(100.0f,0.0f)
        canvas?.drawRect(0.0f,0.0f,100.0f,100.0f,paint)
        canvas?.restore();

        canvas?.translate(0.0f,50.0f)
        canvas?.rotate(45.0f)
        canvas?.translate(100.0f,0.0f)
        canvas?.drawRect(0.0f,0.0f,100.0f,100.0f,paint)
    }
}