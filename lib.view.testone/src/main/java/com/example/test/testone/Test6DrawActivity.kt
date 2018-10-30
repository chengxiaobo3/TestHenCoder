package com.example.test.testone

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test_6_draw.*

/**
 * hencoder06 绘制基础以及图像和文字的测量
 * @author chengxiaobo
 * @time 2018/10/22 13:15
 */
class Test6DrawActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_6_draw)

        degree.setOnClickListener {
            dashBoardView2.addCurrentIndex()
        }
    }
}
