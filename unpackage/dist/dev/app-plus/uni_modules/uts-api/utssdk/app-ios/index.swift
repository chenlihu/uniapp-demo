import DCloudUTSFoundation
@objc(UTSSDKModulesUtsApiMyApiOptions)
@objcMembers
public class MyApiOptions : NSObject, UTSObject, IUTSSourceMap {
    public func __$getOriginalPosition() -> UTSSourceMapPosition? {
        return UTSSourceMapPosition("MyApiOptions", "uni_modules/uts-api/utssdk/interface.uts", 5, 13)
    }
    public var paramA: Bool = false
    public var success: ((_ res: MyApiResult) -> Void)?
    public var fail: ((_ res: MyApiFail) -> Void)?
    public var complete: ((_ res: Any) -> Void)?
    public subscript(_ key: String) -> Any? {
        get {
            return utsSubscriptGetValue(key)
        }
        set {
            switch(key){
                case "paramA":
                    self.paramA = try! utsSubscriptCheckValue(newValue)
                case "success":
                    self.success = try! utsSubscriptCheckValueIfPresent(newValue)
                case "fail":
                    self.fail = try! utsSubscriptCheckValueIfPresent(newValue)
                case "complete":
                    self.complete = try! utsSubscriptCheckValueIfPresent(newValue)
                default:
                    break
            }
        }
    }
    public override init() {
        super.init()
    }
    public init(_ obj: UTSJSONObject) {
        self.paramA = obj["paramA"] as! Bool
        self.success = obj["success"] as! ((_ res: MyApiResult) -> Void)?
        self.fail = obj["fail"] as! ((_ res: MyApiFail) -> Void)?
        self.complete = obj["complete"] as! ((_ res: Any) -> Void)?
    }
}
@objc(UTSSDKModulesUtsApiMyApiResult)
@objcMembers
public class MyApiResult : NSObject, UTSObject, IUTSSourceMap {
    public func __$getOriginalPosition() -> UTSSourceMapPosition? {
        return UTSSourceMapPosition("MyApiResult", "uni_modules/uts-api/utssdk/interface.uts", 17, 13)
    }
    public var fieldA: NSNumber!
    public var fieldB: Bool = false
    public var fieldC: String!
    public subscript(_ key: String) -> Any? {
        get {
            return utsSubscriptGetValue(key)
        }
        set {
            switch(key){
                case "fieldA":
                    self.fieldA = try! utsSubscriptCheckValue(newValue)
                case "fieldB":
                    self.fieldB = try! utsSubscriptCheckValue(newValue)
                case "fieldC":
                    self.fieldC = try! utsSubscriptCheckValue(newValue)
                default:
                    break
            }
        }
    }
    public override init() {
        super.init()
    }
    public init(_ obj: UTSJSONObject) {
        self.fieldA = obj["fieldA"] as! NSNumber
        self.fieldB = obj["fieldB"] as! Bool
        self.fieldC = obj["fieldC"] as! String
    }
}
public typealias MyApiErrorCode = NSNumber
public protocol MyApiFail : IUniError {
    var errCode: MyApiErrorCode { get set }
}
public typealias MyApi = (_ options: MyApiOptions) -> Void
public typealias MyApiSync = (_ paramA: Bool) -> MyApiResult
public var UniErrorSubject = "uts-api"
public var UTSApiUniErrors: Map<MyApiErrorCode, String> = Map([
    [
        9010001,
        "custom error mseeage1"
    ],
    [
        9010002,
        "custom error mseeage2"
    ]
])
@objc(UTSSDKModulesUtsApiMyApiFailImpl)
@objcMembers
public class MyApiFailImpl : UniError, MyApiFail {
    public override var errCode: MyApiErrorCode {
        get {
            return self.__errCode
        }
        set(value) {
            self.__errCode = value
        }
    }
    public init(_ errCode: MyApiErrorCode){
        super.init()
        self.errSubject = UniErrorSubject
        self.errCode = errCode
        self.errMsg = UTSApiUniErrors.get(errCode) ?? ""
    }
    private var __errCode: MyApiErrorCode!
}
public var myApi: MyApi = {
(_ options: MyApiOptions) -> Void in
if (options.paramA == true) {
    var res = MyApiResult(UTSJSONObject([
        "fieldA": 85 as NSNumber,
        "fieldB": true,
        "fieldC": "some message"
    ]))
    options.success?(res)
    options.complete?(res)
} else {
    var failResult = MyApiFailImpl(9010001)
    options.fail?(failResult)
    options.complete?(failResult)
}
}
public var myApiSync: MyApiSync = {
(_ paramA: Bool) -> MyApiResult in
var res = MyApiResult(UTSJSONObject([
    "fieldA": 85 as NSNumber,
    "fieldB": paramA,
    "fieldC": "some message"
]))
return res
}
@objc(UTSSDKModulesUtsApiMyApiOptionsJSONObject)
@objcMembers
public class MyApiOptionsJSONObject : NSObject {
    public var paramA: Bool = false
    public var success: UTSCallback?
    public var fail: UTSCallback?
    public var complete: UTSCallback?
}
public func myApiByJs(_ options: MyApiOptionsJSONObject) -> Void {
    return myApi(MyApiOptions(UTSJSONObject([
        "paramA": options.paramA,
        "success": {
        (res: MyApiResult) -> Void in
        options.success?(res)
        },
        "fail": {
        (res: MyApiFail) -> Void in
        options.fail?(res)
        },
        "complete": {
        (res: Any) -> Void in
        options.complete?(res)
        }
    ])))
}
public func myApiSyncByJs(_ paramA: Bool) -> MyApiResult {
    return myApiSync(paramA)
}
@objc(UTSSDKModulesUtsApiIndexSwift)
@objcMembers
public class UTSSDKModulesUtsApiIndexSwift : NSObject {
    public static func s_myApiByJs(_ options: MyApiOptionsJSONObject) -> Void {
        return myApiByJs(options)
    }
    public static func s_myApiSyncByJs(_ paramA: Bool) -> MyApiResult {
        return myApiSyncByJs(paramA)
    }
}
