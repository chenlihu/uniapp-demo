@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.tusenTView
import android.widget.Button
import android.widget.LinearLayout
import io.dcloud.feature.uniapp.UniSDKInstance
import io.dcloud.feature.uniapp.ui.action.AbsComponentData
import io.dcloud.feature.uniapp.ui.component.AbsVContainer
import io.dcloud.uniapp.*
import io.dcloud.uniapp.extapi.*
import io.dcloud.unicloud.*
import io.dcloud.uts.*
import io.dcloud.uts.Map
import io.dcloud.uts.Set
import io.dcloud.uts.UTSAndroid
import io.dcloud.uts.component.*
import io.dcloud.uts.component.UTSComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import uts.sdk.modules.tusenTView.CubeGLView
open class TusenTViewComponent : UTSComponent<LinearLayout> {
    constructor(instance: UniSDKInstance?, parent: AbsVContainer<*>?, componentData: AbsComponentData<*>?) : super(instance, parent, componentData)
    override fun NVLoad(): LinearLayout {
        console.log("=== 开始创建UTS组件 ===", " at uni_modules/tusen-t-view/utssdk/app-android/index.vue:14")
        val container = LinearLayout(`$androidContext`!!)
        try {
            container.setOrientation(LinearLayout.VERTICAL)
            container.setLayoutParams(android.view.ViewGroup.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.MATCH_PARENT))
            val glView = CubeGLView(`$androidContext`!!)
            console.log("CubeGLView created:", glView, " at uni_modules/tusen-t-view/utssdk/app-android/index.vue:27")
            glView.setLayoutParams(LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 600))
            val button = Button(`$androidContext`!!)
            button.setText("GLSurfaceView测试")
            button.setLayoutParams(LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT))
            container.addView(glView)
            container.addView(button)
        }
         catch (error: Throwable) {
            console.error("创建UTS组件失败:", error, " at uni_modules/tusen-t-view/utssdk/app-android/index.vue:46")
            throw error
        }
        console.log("=== 组件创建完成 ===", " at uni_modules/tusen-t-view/utssdk/app-android/index.vue:51")
        return container
    }
}
