package uts.sdk.modules.utsApi

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.widget.ScrollView
import android.view.Gravity
import android.graphics.Color
import android.view.ViewGroup
import android.view.View

class HelloActivity : Activity() {
    private lateinit var chatLayout: LinearLayout
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 外层垂直布局
        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setBackgroundColor(Color.WHITE)
        root.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        // 聊天消息滚动区
        scrollView = ScrollView(this)
        scrollView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            0, 1f
        )

        chatLayout = LinearLayout(this)
        chatLayout.orientation = LinearLayout.VERTICAL
        chatLayout.setPadding(40, 40, 40, 40)
        scrollView.addView(chatLayout)

        // 输入区
        val inputLayout = LinearLayout(this)
        inputLayout.orientation = LinearLayout.HORIZONTAL
        inputLayout.gravity = Gravity.CENTER_VERTICAL
        inputLayout.setPadding(20, 20, 20, 20)
        inputLayout.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val editText = EditText(this)
        editText.hint = "请输入消息"
        editText.setTextColor(Color.BLACK)
        editText.setBackgroundColor(Color.parseColor("#F5F5F5"))
        val editParams = LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
        editParams.setMargins(0, 0, 20, 0)
        editText.layoutParams = editParams

        val sendBtn = Button(this)
        sendBtn.text = "发送"
        sendBtn.setBackgroundColor(Color.parseColor("#007AFF"))
        sendBtn.setTextColor(Color.WHITE)
        sendBtn.setPadding(40, 20, 40, 20)

        sendBtn.setOnClickListener {
            val msg = editText.text.toString().trim()
            if (msg.isNotEmpty()) {
                addMessage("我", msg)
                editText.setText("")
                scrollToBottom()
            }
        }

        inputLayout.addView(editText)
        inputLayout.addView(sendBtn)

        // 标题
        val title = TextView(this)
        title.text = "聊天界面"
        title.setTextColor(Color.BLACK)
        title.textSize = 22f
        title.gravity = Gravity.CENTER
        val titleParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        titleParams.setMargins(0, 40, 0, 20)
        title.layoutParams = titleParams

        root.addView(title)
        root.addView(scrollView)
        root.addView(inputLayout)

        setContentView(root)

        // 示例消息
        addMessage("小明", "你好！")
        addMessage("小红", "你好，小明！")
        addMessage("小明", "今天过得怎么样？")
        addMessage("小红", "挺好的，你呢？")
        addMessage("小明", "我也不错，一起加油！")
        scrollToBottom()
    }

    private fun addMessage(sender: String, message: String) {
        val msgLayout = LinearLayout(this)
        msgLayout.orientation = LinearLayout.HORIZONTAL
        msgLayout.setPadding(0, 10, 0, 10)

        val senderView = TextView(this)
        senderView.text = "$sender: "
        senderView.setTextColor(Color.parseColor("#007AFF"))
        senderView.textSize = 16f

        val msgView = TextView(this)
        msgView.text = message
        msgView.setTextColor(Color.DKGRAY)
        msgView.textSize = 16f

        msgLayout.addView(senderView)
        msgLayout.addView(msgView)

        chatLayout.addView(msgLayout)
    }

    private fun scrollToBottom() {
        scrollView.post {
            scrollView.fullScroll(View.FOCUS_DOWN)
        }
    }
}
