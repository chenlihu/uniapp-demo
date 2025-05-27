import DCloudUTSFoundation
import UIKit
@objc(UTSSDKModulesTusenTViewTusenTViewComponent)
@objcMembers
public class TusenTViewComponent : UTSComponent<UIButton> {
    public var buttontext: String = "点击触发"
    public override func created() {}
    public override func NVBeforeLoad() {}
    public override func NVLoad() -> UIButton {
        buttonClickListsner = ButtonClickListsner(self)
        var button = UIButton()
        button.setTitle(self.buttontext, for: UIControl.State.normal)
        var method = Selector("buttonClickAction")
        if (buttonClickListsner != nil) {
            button.addTarget(buttonClickListsner!, action: method, for: UIControl.Event.touchUpInside)
        }
        return button
    }
    public override func NVLoaded() {
        self.__$$el.setTitle(self.buttontext, for: UIControl.State.normal)
    }
    public override func NVLayouted() {}
    public override func NVBeforeUnload() {}
    public override func NVUnloaded() {}
    public override func unmounted() {}
    public func doSomething(_ paramA: String) {
        console.log(paramA, "this is in uts-button component", " at uni_modules/tusen-t-view/utssdk/app-ios/index.vue:91")
    }
    public static func wx_export_method_0() -> String {
        return "doSomething:"
    }
    public override func __$$init() {
        self.__$$watch("buttontext", {
        (__newValue, __oldValue) -> Void in
        var newValue = UTSiOS.convertString(__newValue)
        var oldValue = UTSiOS.convertString(__oldValue)
        self.__$$el.setTitle(newValue, for: UIControl.State.normal)
        })
    }
}
@objc(UTSSDKModulesTusenTViewButtonClickListsner)
@objcMembers
public class ButtonClickListsner : NSObject {
    private var component: UTSComponent<UIButton>
    public init(_ component: UTSComponent<UIButton>){
        self.component = component
        super.init()
    }
    @objc
    public func buttonClickAction() {
        console.log("按钮被点击", " at uni_modules/tusen-t-view/utssdk/app-ios/index.vue:197")
        self.component.__$$emit("buttonclick")
    }
}
public var buttonClickListsner: ButtonClickListsner? = nil
