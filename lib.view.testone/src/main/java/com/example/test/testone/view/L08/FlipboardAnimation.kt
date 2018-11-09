package com.example.test.testone.view.L08

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.example.test.testone.R
import com.example.test.testone.util.getBitmap
import com.example.test.testone.util.getCameraPositionZ

/**
 * Flipborad动画
 *
 * @author chengxiaobo
 * @time 2018/11/9 22:20
 */
class FlipboardAnimation : View {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    private var imageWidth = 0.0f
    private var centerX = 0.0f
    private var centerY = 0.0f

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private lateinit var bitmap: Bitmap
    private lateinit var sourceRect: Rect
    private lateinit var destRect: Rect
    private val camera = Camera()

    private var rotateDegree: Float = 0.0f
        set(value) {
            field = value
            invalidate()
        }

    private var flipBottomDegree: Float = 0.0f
        set(value) {
            field = value
            invalidate()
        }

    private var flipTopDegree: Float = 0.0f
        set(value) {
            field = value
            invalidate()
        }

    init {

        camera.setLocation(0.0f, 0.0f, getCameraPositionZ())

        postDelayed({
            val rotateDegreeAnimation = getRotateDegreeAnimation()
            val flipBottomDegreeAnimation = getFlipBottomDegreeAnimation()
//            val flipboardAnimationReverse = getFlipBottomDegreeAnimationReverse()
            val flipTopDegreeAnimation = getFlipTopDegreddAnimation()
            val animatorSet = AnimatorSet()
            animatorSet.playSequentially(flipBottomDegreeAnimation, rotateDegreeAnimation, flipTopDegreeAnimation)
            animatorSet.start()
            animatorSet.addListener(object : Animator.AnimatorListener {
                override fun onAnimationRepeat(animation: Animator?) {
                }

                override fun onAnimationEnd(animation: Animator?) {
                    val animatorSet = AnimatorSet()
                    animatorSet.playTogether(getFlipTopDegreeAnimationReverse(), getFlipBottomDegreeAnimationReverse())
                    animatorSet.start()
                }

                override fun onAnimationCancel(animation: Animator?) {
                }

                override fun onAnimationStart(animation: Animator?) {
                }
            })
        }, 1000)
    }


    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        imageWidth = if (width > height) height / 2.5f else width / 2.5f
        centerX = width / 2.0f
        centerY = height / 2.0f

        bitmap = getBitmap(imageWidth.toInt(), R.drawable.rengwuxian)

        val bitmapWidth = if (bitmap.width > bitmap.height) bitmap.height else bitmap.width
        sourceRect = Rect(0, 0, bitmapWidth, bitmapWidth)
        destRect = Rect((centerX - imageWidth).toInt(), (centerY - imageWidth).toInt(), (centerX + imageWidth).toInt(), (centerY + imageWidth).toInt())
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.save()
        camera.save()
        canvas?.translate(centerX, centerY)
        canvas?.rotate(-rotateDegree)
        camera.rotateX(flipTopDegree)
        camera.applyToCanvas(canvas)
        canvas?.clipRect(-imageWidth * 2, -imageWidth * 2, imageWidth * 2, 0.0f)
        canvas?.rotate(rotateDegree)
        canvas?.translate(-centerX, -centerY)
        canvas?.drawBitmap(bitmap, sourceRect, destRect, paint)
        canvas?.restore()
        camera.restore()

        canvas?.save()
        camera.save()
        canvas?.translate(centerX, centerY)
        canvas?.rotate(-rotateDegree)
//        canvas?.clipRect(-imageWidth, 0.0f, imageWidth, imageWidth) //为什么不能在这个位置呢
        camera.rotateX(flipBottomDegree)
        camera.applyToCanvas(canvas)
        canvas?.clipRect(-imageWidth * 2, 0.0f, imageWidth * 2, imageWidth * 2)
        canvas?.rotate(rotateDegree)
        canvas?.translate(-centerX, -centerY)
        canvas?.drawBitmap(bitmap, sourceRect, destRect, paint)
        canvas?.restore()
        camera?.restore()
    }

    private fun getRotateDegreeAnimation(): ObjectAnimator {
        val objectAnimator = ObjectAnimator.ofFloat(this, "rotateDegree", 270.0f)
        objectAnimator.duration = 2000
        return objectAnimator
    }

    private fun getFlipBottomDegreeAnimation(): ObjectAnimator {
        val objectAnimator = ObjectAnimator.ofFloat(this, "flipBottomDegree", 45.0f)
        objectAnimator.duration = 2000
        return objectAnimator
    }

    private fun getFlipBottomDegreeAnimationReverse(): ObjectAnimator {
        val objectAnimator = ObjectAnimator.ofFloat(this, "flipBottomDegree", 0.0f)
        objectAnimator.duration = 500
        return objectAnimator
    }

    private fun getFlipTopDegreddAnimation(): ObjectAnimator {
        val objectAnimator = ObjectAnimator.ofFloat(this, "flipTopDegree", -45.0f)
        objectAnimator.duration = 2000
        return objectAnimator
    }

    private fun getFlipTopDegreeAnimationReverse(): ObjectAnimator {
        val objectAnimator = ObjectAnimator.ofFloat(this, "flipTopDegree", 0.0f)
        objectAnimator.duration = 500
        return objectAnimator
    }

}