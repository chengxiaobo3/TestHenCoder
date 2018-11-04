package com.example.test.testone.view.L07

import android.content.Context
import android.graphics.*
import android.text.Layout
import android.text.StaticLayout
import android.text.TextPaint
import android.util.AttributeSet
import android.view.View
import com.example.test.testone.R
import com.example.test.testone.util.BREAK_TEXT
import com.example.test.testone.util.dp2px
import com.example.test.testone.util.getBitmap
import kotlinx.android.synthetic.main.activity_test_7_draw.view.*
import org.w3c.dom.Text

/**
 * hencoder07 居中的TextView
 * @author chengxiaobo
 * @time 2018/10/30 23:15
 */
class BreakTextView : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private lateinit var bitmap: Bitmap
    private var picWidth: Float = 0.0f
    private var picHeight: Float = 0.0f

    private lateinit var sourceRect: Rect
    private lateinit var destRect: Rect
    private lateinit var fontMatrix: Paint.FontMetrics

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var staticLayout: StaticLayout
    private var lineHeight = 0.0f
    private var lineY = 0.0f

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        picWidth = width / 5.0f
        bitmap = getBitmap(picWidth.toInt(), R.drawable.cheng)
        picHeight = picWidth * bitmap.height / bitmap.width
        sourceRect = Rect(0, 0, bitmap.width, bitmap.height)
        destRect = Rect(width - picWidth.toInt(), height / 3, width, (height / 3.0f + picHeight).toInt())
        paint.color = Color.parseColor("#ff0000")
        paint.textSize = dp2px(20.0f)
        fontMatrix = paint.fontMetrics
        lineY = -fontMatrix.top
        lineHeight = paint.fontSpacing

        //StaticLayout的使用
//        textPaint.textSize = dp2px(20.0f)
//        staticLayout = StaticLayout(BREAK_TEXT, textPaint, width, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0f, true)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawBitmap(bitmap, sourceRect, destRect, paint)
        //StaticLayout的使用
//        staticLayout.draw(canvas)

        val floatArray: FloatArray = FloatArray(1)
        var lastMeasuredCount = 0
        var currentLine = 0
        while (true) {
            val currentYTop = currentLine * lineHeight
            val currentYBottom = (currentLine + 1) * lineHeight
            var breakWith:Float
            if ((currentYTop < destRect.top && currentYBottom < destRect.top) || (currentYTop > destRect.bottom && currentYBottom > destRect.bottom)){
                breakWith=width.toFloat()
            }else
            {
                breakWith=(width-(destRect.right-destRect.left)).toFloat()
            }
            val measuredCount = paint.breakText(BREAK_TEXT.substring(lastMeasuredCount, BREAK_TEXT.length), true,breakWith, floatArray)
            canvas?.drawText(BREAK_TEXT, lastMeasuredCount, lastMeasuredCount + measuredCount, 0.0f, lineY, paint)
            lastMeasuredCount += measuredCount
            lineY += lineHeight
            currentLine++
            if (lastMeasuredCount >= BREAK_TEXT.length) {
                return
            }
        }

    }


}