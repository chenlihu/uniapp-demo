package uts.sdk.modules.utsApi

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.view.Gravity
import android.graphics.Color

class HelloActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = Gravity.CENTER
        layout.setBackgroundColor(Color.WHITE)

        val tv = TextView(this)
        tv.text = "你好啊"
        tv.textSize = 40f
        tv.setTextColor(Color.BLACK)
        tv.gravity = Gravity.CENTER

        layout.addView(tv)

        setContentView(layout)
    }
}
