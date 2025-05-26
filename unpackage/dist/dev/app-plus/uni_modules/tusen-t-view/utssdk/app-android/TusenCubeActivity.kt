package uts.sdk.modules.tusenTView

import android.opengl.GLSurfaceView
import android.util.Log
import javax.microedition.khronos.opengles.GL10
import javax.microedition.khronos.egl.EGLConfig
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class CubeGLView(context: android.content.Context) : GLSurfaceView(context) {
    
    private val TAG = "CubeGLView"
    
    init {
        Log.d(TAG, "初始化CubeGLView")
        
        // 设置EGL上下文版本
        setEGLContextClientVersion(1)
        
        // 设置渲染器
        val renderer = SimpleRenderer()
        setRenderer(renderer)
        
        // 设置渲染模式为连续渲染
        renderMode = RENDERMODE_CONTINUOUSLY
        
        Log.d(TAG, "CubeGLView初始化完成")
    }
    
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "GLSurfaceView attached to window")
    }
    
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "GLSurfaceView detached from window")
    }
}

class SimpleRenderer : GLSurfaceView.Renderer {
    
    private val TAG = "SimpleRenderer"
    private lateinit var triangleVB: FloatBuffer
    private var angle = 0f
    
    // 三角形顶点坐标
    private val triangleCoords = floatArrayOf(
         0.0f,  0.6f, 0.0f,  // 顶部
        -0.6f, -0.6f, 0.0f,  // 左下
         0.6f, -0.6f, 0.0f   // 右下
    )
    
    // 颜色数组 (RGB)
    private val colors = floatArrayOf(
        1.0f, 0.0f, 0.0f, 1.0f,  // 红色
        0.0f, 1.0f, 0.0f, 1.0f,  // 绿色
        0.0f, 0.0f, 1.0f, 1.0f   // 蓝色
    )

    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        Log.d(TAG, "onSurfaceCreated called")
        
        // 设置清屏颜色为黑色
        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        
        // 启用深度测试
        gl.glEnable(GL10.GL_DEPTH_TEST)
        
        // 准备顶点缓冲区
        val bb = ByteBuffer.allocateDirect(triangleCoords.size * 4)
        bb.order(ByteOrder.nativeOrder())
        triangleVB = bb.asFloatBuffer()
        triangleVB.put(triangleCoords)
        triangleVB.position(0)
        
        Log.d(TAG, "Surface created successfully")
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        Log.d(TAG, "onSurfaceChanged: ${width}x${height}")
        
        // 设置视口
        gl.glViewport(0, 0, width, height)
        
        // 设置投影矩阵
        gl.glMatrixMode(GL10.GL_PROJECTION)
        gl.glLoadIdentity()
        
        val ratio = width.toFloat() / height.toFloat()
        gl.glFrustumf(-ratio, ratio, -1f, 1f, 3f, 7f)
    }

    override fun onDrawFrame(gl: GL10) {
		Log.d(TAG, "onDrawFrame")
        // 清除颜色缓冲区和深度缓冲区
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT or GL10.GL_DEPTH_BUFFER_BIT)
        
        // 设置模型视图矩阵
        gl.glMatrixMode(GL10.GL_MODELVIEW)
        gl.glLoadIdentity()
        
        // 移动到合适位置
        gl.glTranslatef(0.0f, 0.0f, -5.0f)
        
        // 旋转
        gl.glRotatef(angle, 0.0f, 0.0f, 1.0f)
        
        // 启用顶点数组
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY)
        
        // 设置顶点指针
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, triangleVB)
        
        // 设置颜色
        gl.glColor4f(1.0f, 0.5f, 0.0f, 1.0f) // 橙色
        
        // 绘制三角形
        gl.glDrawArrays(GL10.GL_TRIANGLES, 0, 3)
        
        // 禁用顶点数组
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY)
        
        // 更新旋转角度
        angle += 1.0f
        if (angle > 360) {
            angle = 0f
        }
    }
}