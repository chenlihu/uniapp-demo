import DCloudUTSFoundation
import UIKit
@objc(UTSSDKModulesTusenTViewTusenTViewComponent)
@objcMembers
public class TusenTViewComponent : UTSComponent<Tusen3DView> {
    public override func created() {}
    public override func NVBeforeLoad() {}
    public override func NVLoad() -> Tusen3DView {
        var tusen3dView = Tusen3DView()
        return tusen3dView
    }
    public override func NVLoaded() {}
    public override func NVLayouted() {}
    public override func NVBeforeUnload() {}
    public override func NVUnloaded() {}
    public override func unmounted() {}
}
