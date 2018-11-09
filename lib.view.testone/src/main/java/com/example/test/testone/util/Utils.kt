package com.example.test.testone.util

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.View

/**
 * 工具类
 * @author chengxiaobo
 * @time 2018/10/22 13:15
 */

fun dp2px(dp: Float): Float {
    return Resources.getSystem().displayMetrics.density * dp
}

fun getCameraPositionZ(): Float {
    return Resources.getSystem().displayMetrics.density * -6
}

fun getAngleRadians(angle: Float): Double {
    return angle * Math.PI / 180
}

fun View.getBitmap(widthPX: Int, resourceId: Int): Bitmap {
    val options = BitmapFactory.Options()
    options.inJustDecodeBounds = true
    BitmapFactory.decodeResource(this.resources, resourceId, options)
    options.inJustDecodeBounds = false
    options.inDensity = options.outWidth
    options.inTargetDensity = widthPX
    return BitmapFactory.decodeResource(this.resources, resourceId, options)
}

val BREAK_TEXT = "Make installs smaller and faster for your users. Upload your app using Android's new app publishing format and Google Play's Dynamic Delivery to generate and serve APKs that are optimized for each user's device.Make installs smaller and faster for your users. Upload your app using Android's new app publishing format "