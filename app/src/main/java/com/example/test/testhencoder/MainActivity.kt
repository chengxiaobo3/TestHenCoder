package com.example.test.testhencoder

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.test.testone.Test7DrawActivity
import com.example.test.testone.Test6DrawActivity
import com.example.test.testone.Test8DrawActivity
import com.example.test.testone.Test9DrawActivity
import kotlinx.android.synthetic.main.activity_main.*

/**
 * hencoder主Activity
 * @author chengxiaobo
 * @time 2018/10/22 13:15
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv01TestOne.setOnClickListener {
            startActivity(Intent(this@MainActivity, Test6DrawActivity::class.java))
        }

        tv07.setOnClickListener {
            startActivity(Intent(this@MainActivity, Test7DrawActivity::class.java))
        }

        tv08.setOnClickListener {
            startActivity(Intent(this@MainActivity, Test8DrawActivity::class.java))
        }

        tv09.setOnClickListener {
            startActivity(Intent(this@MainActivity, Test9DrawActivity::class.java))
        }
    }
}
