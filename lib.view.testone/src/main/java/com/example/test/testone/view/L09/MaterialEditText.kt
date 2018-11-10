package com.example.test.testone.view.L09

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.widget.EditText
import com.example.test.testone.util.dp2px

/**
 *  仿写 MaterialEditText
 *
 * @author chengxiaobo
 * @time 2018/11/10 16:05
 */
class MaterialEditText : EditText {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    //自己仿写项目过程中，遇到的问题。
    //1.padding是在 xml 里面定义好呢，还是在materialEidtText里面定义呢？ 不能在程序中设置，否则edittext的text的位置就不对了
    //2.应该是drawText，那么动画怎么实现的呢？

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    //    private var paddingTop = dp2px(40.0f)
    private var tipBaseLine = 0.0f
    private var maxTipBaseLine = 0.0f
    private var tiptextSize = dp2px(16.0f)
    private val tips: String = hint.toString()
    private var tipAnimation: ObjectAnimator? = null

    private var tipX = dp2px(3.0f)
    private var tipY = 0.0f
        set(value) {
            field = value
            invalidate()
        }
    private var transparency = if (text.toString().isEmpty()) 0 else 255
        set(value) {
            field = value
            invalidate()
        }

    private var isHasContent = !text.toString().isEmpty() //editText是否有内容
    private var isShowTip = !text.toString().isEmpty() //是否显示提示

    init {

        addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                if (isHasContent) {

                    //editText内容从有->无，隐藏提示动画
                    if (s.toString().isEmpty()) {
                        getTranslateAnimation()?.reverse()
                    }
                } else {

                    //editText内容从无->有,显示提示动画
                    if (!s.toString().isEmpty()) {
                        getTranslateAnimation()?.start()
                        isShowTip = true
                    }
                }
                isHasContent = !s.toString().isEmpty()
            }
        })

        if (text.toString().isNotEmpty()) {
            setSelection(text.toString().length)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

//        setPadding(0, paddingTop.toInt(), 0, 0)

        paint.textSize = tiptextSize
        tipBaseLine = paddingTop.toFloat() - dp2px(5.0f)
        maxTipBaseLine = height - dp2px(15.0f)
        tipY = if (isHasContent) tipBaseLine else maxTipBaseLine

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        paint.alpha = transparency

        if (isShowTip)
            canvas?.drawText(tips, tipX, tipY, paint)
    }

    private fun getTranslateAnimation(): ObjectAnimator? {
        if (tipAnimation == null) {
            val tranlatePropertyValuesHolder = PropertyValuesHolder.ofFloat("tipY", maxTipBaseLine, tipBaseLine)
            val transparencyPropertyValuesHolder = PropertyValuesHolder.ofInt("transparency", 0, 255)
            tipAnimation = ObjectAnimator.ofPropertyValuesHolder(this, tranlatePropertyValuesHolder, transparencyPropertyValuesHolder)
            tipAnimation?.duration = 500
        }
        tipAnimation?.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                isShowTip = isHasContent
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        return tipAnimation
    }
}