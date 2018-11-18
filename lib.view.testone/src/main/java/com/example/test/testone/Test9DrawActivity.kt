package com.example.test.testone

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test9_draw.*

/**
 *  仿写 MaterialEditText
 *
 * @author chengxiaobo
 * @time 2018/11/10 16:05
 */
class Test9DrawActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test9_draw)

        met.postDelayed({
            met.setShowFloatingLabel(false)
        }, 2000)

    }
}
