@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.utsApi
import android.content.Intent
import io.dcloud.uniapp.*
import io.dcloud.uniapp.extapi.*
import io.dcloud.unicloud.*
import io.dcloud.uts.*
import io.dcloud.uts.Map
import io.dcloud.uts.Set
import io.dcloud.uts.UTSAndroid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
open class My : Runnable, IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("My", "uni_modules/uts-api/utssdk/app-android/index.uts", 3, 7)
    }
    override fun run() {
        val activity = UTSAndroid.getUniActivity()
        if (activity != null) {
            try {
                val intent = Intent()
                intent.setClassName(activity, "uts.sdk.modules.utsApi.HelloActivity")
                activity.startActivity(intent)
            }
             catch (e: Throwable) {
                console.log("e", e, " at uni_modules/uts-api/utssdk/app-android/index.uts:14")
            }
        }
    }
}
val myApi = fun() {
    val activity = UTSAndroid.getUniActivity()
    if (activity != null) {
        activity.runOnUiThread(My())
    }
}
fun myApiByJs(): Unit {
    return myApi()
}
