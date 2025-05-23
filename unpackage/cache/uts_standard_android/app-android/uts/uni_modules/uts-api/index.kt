@file:Suppress("UNCHECKED_CAST", "USELESS_CAST", "INAPPLICABLE_JVM_NAME", "UNUSED_ANONYMOUS_PARAMETER", "NAME_SHADOWING", "UNNECESSARY_NOT_NULL_ASSERTION")
package uts.sdk.modules.utsApi
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
open class MyApiOptions (
    @JsonNotNull
    open var paramA: Boolean = false,
    open var success: ((res: MyApiResult) -> Unit)? = null,
    open var fail: ((res: MyApiFail) -> Unit)? = null,
    open var complete: ((res: Any) -> Unit)? = null,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("MyApiOptions", "uni_modules/uts-api/utssdk/interface.uts", 5, 13)
    }
}
open class MyApiResult (
    @JsonNotNull
    open var fieldA: Number,
    @JsonNotNull
    open var fieldB: Boolean = false,
    @JsonNotNull
    open var fieldC: String,
) : UTSObject(), IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("MyApiResult", "uni_modules/uts-api/utssdk/interface.uts", 17, 13)
    }
}
typealias MyApiErrorCode = Number
interface MyApiFail : IUniError {
    override var errCode: MyApiErrorCode
}
typealias MyApi = (options: MyApiOptions) -> Unit
typealias MyApiSync = (paramA: Boolean) -> MyApiResult
val UniErrorSubject = "uts-api"
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
open class MyApiFailImpl : UniError, MyApiFail, IUTSSourceMap {
    override fun `__$getOriginalPosition`(): UTSSourceMapPosition? {
        return UTSSourceMapPosition("MyApiFailImpl", "uni_modules/uts-api/utssdk/unierror.uts", 25, 14)
    }
    override var errCode: MyApiErrorCode
    constructor(errCode: MyApiErrorCode) : super() {
        this.errSubject = UniErrorSubject
        this.errCode = errCode
        this.errMsg = UTSApiUniErrors.get(errCode) ?: ""
    }
}
val myApi: MyApi = fun(options: MyApiOptions) {
    if (options.paramA == true) {
        val res = MyApiResult(fieldA = 85, fieldB = true, fieldC = "some message")
        options.success?.invoke(res)
        options.complete?.invoke(res)
    } else {
        val err = MyApiFailImpl(9010001)
        options.fail?.invoke(err)
        options.complete?.invoke(err)
    }
}
val myApiSync: MyApiSync = fun(paramA: Boolean): MyApiResult {
    val res = MyApiResult(fieldA = 85, fieldB = paramA, fieldC = "some message")
    return res
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
fun myApiSyncByJs(paramA: Boolean): MyApiResult {
    return myApiSync(paramA)
}
