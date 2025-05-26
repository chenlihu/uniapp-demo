package uts.sdk.modules.utsApi

import android.app.Activity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.EditText
import android.widget.Button
import android.widget.ScrollView
import android.widget.FrameLayout
import android.view.Gravity
import android.graphics.Color
import android.view.ViewGroup
import android.view.View
import android.opengl.GLSurfaceView
import javax.microedition.khronos.opengles.GL10
import javax.microedition.khronos.egl.EGLConfig

class HelloActivity : Activity() {
    private lateinit var chatLayout: LinearLayout
    private lateinit var scrollView: ScrollView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // FrameLayout用于层叠3D背景和聊天内容
        val frame = FrameLayout(this)
        frame.layoutParams = FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        // 3D背景
        val glView = SimpleCubeGLView(this)
        frame.addView(glView)

        // 聊天内容垂直布局
        val root = LinearLayout(this)
        root.orientation = LinearLayout.VERTICAL
        root.setBackgroundColor(Color.TRANSPARENT)
        root.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

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

        // 聊天消息滚动区
        scrollView = ScrollView(this)
        scrollView.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            0, 1f
        )
        scrollView.isVerticalScrollBarEnabled = false

        chatLayout = LinearLayout(this)
        chatLayout.orientation = LinearLayout.VERTICAL
        chatLayout.setPadding(40, 40, 40, 40)
        chatLayout.setBackgroundColor(Color.argb(180, 255, 255, 255)) // 半透明白色背景
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

        // 组装聊天内容
        root.addView(title)
        root.addView(scrollView)
        root.addView(inputLayout)

        // 将聊天内容布局添加到FrameLayout（覆盖在3D背景上）
        frame.addView(root)

        setContentView(frame)

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

// 简单3D立方体OpenGL视图
class SimpleCubeGLView(context: android.content.Context) : GLSurfaceView(context) {
    private val renderer = CubeRenderer()
    init {
        setEGLContextClientVersion(1)
        setRenderer(renderer)
        renderMode = RENDERMODE_CONTINUOUSLY
    }
    private class CubeRenderer : GLSurfaceView.Renderer {
        private var angle = 0f
        override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
            gl.glClearColor(1f, 1f, 1f, 1f)
            gl.glEnable(GL10.GL_DEPTH_TEST)
        }
        override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
            gl.glViewport(0, 0, width, height)
            gl.glMatrixMode(GL10.GL_PROJECTION)
            gl.glLoadIdentity()
            val ratio = width.toFloat() / height
            gl.glFrustumf(-ratio, ratio, -1f, 1f, 3f, 7f)
        }
        override fun onDrawFrame(gl: GL10) {
            gl.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
            gl.glMatrixMode(GL10.GL_MODELVIEW)
            gl.glLoadIdentity()
            gl.glTranslatef(0f, 0f, -5f)
            gl.glRotatef(angle, 1f, 1f, 0f)
            drawCube(gl)
            angle += 1.5f
        }
        private fun drawCube(gl: GL10) {
            val vertices = floatArrayOf(
                -1f, -1f,  1f,  1f, -1f,  1f,  1f,  1f,  1f, -1f,  1f,  1f, // Front
                -1f, -1f, -1f, -1f,  1f, -1f,  1f,  1f, -1f,  1f, -1f, -1f  // Back
            )
            val colors = floatArrayOf(
                1f, 0f, 0f, 1f,  0f, 1f, 0f, 1f,  0f, 0f, 1f, 1f,  1f, 1f, 0f, 1f,
                1f, 0f, 1f, 1f,  0f, 1f, 1f, 1f
            )
            val indices = byteArrayOf(
                0, 1, 2, 0, 2, 3, // Front
                4, 5, 6, 4, 6, 7, // Back
                0, 3, 5, 0, 5, 4, // Left
                1, 7, 6, 1, 6, 2, // Right
                3, 2, 6, 3, 6, 5, // Top
                0, 4, 7, 0, 7, 1  // Bottom
            )
            val vb = java.nio.ByteBuffer.allocateDirect(vertices.size * 4).order(java.nio.ByteOrder.nativeOrder()).asFloatBuffer()
            vb.put(vertices).position(0)
            val cb = java.nio.ByteBuffer.allocateDirect(colors.size * 4).order(java.nio.ByteOrder.nativeOrder()).asFloatBuffer()
            cb.put(colors).position(0)
            val ib = java.nio.ByteBuffer.allocateDirect(indices.size).order(java.nio.ByteOrder.nativeOrder())
            ib.put(indices).position(0)
            gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
            gl.glEnableClientState(GL10.GL_COLOR_ARRAY)
            gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vb)
            gl.glColorPointer(4, GL10.GL_FLOAT, 0, cb)
            gl.glDrawElements(GL10.GL_TRIANGLES, indices.size, GL10.GL_UNSIGNED_BYTE, ib)
            gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
            gl.glDisableClientState(GL10.GL_COLOR_ARRAY)
        }
    }
}
