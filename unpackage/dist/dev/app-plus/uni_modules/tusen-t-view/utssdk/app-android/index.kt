@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.tusenTView
import android.text.TextUtils
import android.view.View
import android.widget.Button
import com.taobao.weex.annotation.JSMethod
import com.taobao.weex.ui.component.WXComponentProp
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
import io.dcloud.uts.component.UTSSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
open class TusenTViewComponent : UTSComponent<Button> {
    constructor(instance: UniSDKInstance?, parent: AbsVContainer<*>?, componentData: AbsComponentData<*>?) : super(instance, parent, componentData)
    open var buttontext: String = "点击触发"
    override fun created() {}
    override fun NVBeforeLoad() {}
    override fun NVLoad(): Button {
        var button = Button(`$androidContext`!!)
        button.setText("点击触发")
        button.setOnClickListener(ButtonClickListener(this))
        return button
    }
    override fun NVLoaded() {}
    override fun NVLayouted() {}
    override fun NVBeforeUnload() {}
    override fun NVUnloaded() {}
    override fun unmounted() {}
    override fun NVMeasure(size: UTSSize): UTSSize {
        return size
    }
    @JSMethod(uiThread = false)
    open fun doSomething(param: String) {
        console.log(param, " at uni_modules/tusen-t-view/utssdk/app-android/index.vue:91")
    }
    open fun privateMethod() {}
    override fun `$init`() {
        this.`$watch`<String>("buttontext", fun(newValue, oldValue){
            if (!TextUtils.isEmpty(newValue) && newValue != oldValue) {
                this.`$el`?.setText(newValue)
            }
        }
        )
    }
    @WXComponentProp(name = "buttontext")
    open fun componentSetButtontext(value: String) {
        this.buttontext = value
        this.`$componentWatchDispatch`("buttontext", value)
    }
}
open class ButtonClickListener : View.OnClickListener {
    private var comp: UTSComponent<Button>
    constructor(comp: UTSComponent<Button>) : super() {
        this.comp = comp
    }
    override fun onClick(v: View?) {
        console.log("按钮被点击", " at uni_modules/tusen-t-view/utssdk/app-android/index.vue:186")
        this.comp.`$emit`("buttonclick")
    }
}
