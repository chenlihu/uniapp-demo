@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.utsApi
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import io.dcloud.uniapp.*
import io.dcloud.uniapp.extapi.*
import io.dcloud.uts.*
import io.dcloud.uts.Map
import io.dcloud.uts.Set
import io.dcloud.uts.UTSAndroid
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
open class MyApiOptions (
    @JsonNotNull
    open var paramA: Boolean = false,
    open var success: ((res: MyApiResult) -> Unit)? = null,
    open var fail: ((res: MyApiFail) -> Unit)? = null,
    open var complete: ((res: Any) -> Unit)? = null,
) : UTSObject()
open class MyApiResult (
    @JsonNotNull
    open var fieldA: Number,
    @JsonNotNull
    open var fieldB: Boolean = false,
    @JsonNotNull
    open var fieldC: String,
) : UTSObject()
typealias MyApiErrorCode = Number
interface MyApiFail : IUniError {
    override var errCode: MyApiErrorCode
}
typealias MyApi = (options: MyApiOptions) -> Unit
val UTSApiUniErrors: Map<MyApiErrorCode, String> = Map(utsArrayOf(
    utsArrayOf(
        9010001,
        "custom error mseeage1"
    ),
    utsArrayOf(
        9010002,
        "custom error mseeage2"
    )
))
open class HelloActivity : Activity {
    constructor() : super() {}
    override fun onCreate(savedInstanceState: Bundle?): Unit {
        super.onCreate(savedInstanceState)
    }
}
open class My : Runnable {
    override fun run() {
        val activity = UTSAndroid.getUniActivity()
        if (activity != null) {
            try {
                val intent = Intent(activity, HelloActivity().javaClass)
                activity.startActivity(intent)
            }
             catch (e: Throwable) {
                console.log("e", e)
            }
        }
    }
}
val myApi: MyApi = fun(options: MyApiOptions) {
    val activity = UTSAndroid.getUniActivity()
    if (activity != null) {
        activity.runOnUiThread(My())
    }
}
open class MyApiOptionsJSONObject : UTSJSONObject() {
    open var paramA: Boolean = false
    open var success: UTSCallback? = null
    open var fail: UTSCallback? = null
    open var complete: UTSCallback? = null
}
fun myApiByJs(options: MyApiOptionsJSONObject): Unit {
    return myApi(MyApiOptions(paramA = options.paramA, success = fun(res: MyApiResult): Unit {
        options.success?.invoke(res)
    }
    , fail = fun(res: MyApiFail): Unit {
        options.fail?.invoke(res)
    }
    , complete = fun(res: Any): Unit {
        options.complete?.invoke(res)
    }
    ))
}
