package com.example.test.testone.util

import android.content.res.Resources

/**
 * 工具类
 * @author chengxiaobo
 * @time 2018/10/22 13:15
 */

fun dp2px(dp: Float): Float {
    return Resources.getSystem().displayMetrics.density * dp
}
