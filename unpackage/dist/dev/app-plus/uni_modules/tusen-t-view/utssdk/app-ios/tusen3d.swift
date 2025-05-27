import UIKit
import SceneKit

@objc public class Tusen3DView: UIView {
    private var sceneView: SCNView!

    public override init(frame: CGRect) {
        super.init(frame: frame)
        setup3DView()
    }

    required init?(coder: NSCoder) {
        super.init(coder: coder)
        setup3DView()
    }

    private func setup3DView() {
        sceneView = SCNView(frame: self.bounds)
        sceneView.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        self.addSubview(sceneView)

        let scene = SCNScene()
        sceneView.scene = scene
        sceneView.backgroundColor = UIColor.clear

        // 创建立方体
        let box = SCNBox(width: 1, height: 1, length: 1, chamferRadius: 0.05)
        let boxNode = SCNNode(geometry: box)
        scene.rootNode.addChildNode(boxNode)

        // 添加光源
        let light = SCNLight()
        light.type = .omni
        let lightNode = SCNNode()
        lightNode.light = light
        lightNode.position = SCNVector3(x: 2, y: 2, z: 2)
        scene.rootNode.addChildNode(lightNode)

        // 添加摄像机
        let camera = SCNCamera()
        let cameraNode = SCNNode()
        cameraNode.camera = camera
        cameraNode.position = SCNVector3(x: 0, y: 0, z: 5)
        scene.rootNode.addChildNode(cameraNode)

        // 添加旋转动画
        let spin = CABasicAnimation(keyPath: "rotation")
        spin.fromValue = NSValue(scnVector4: SCNVector4(0, 1, 0, 0))
        spin.toValue = NSValue(scnVector4: SCNVector4(0, 1, 0, Float.pi * 2))
        spin.duration = 3
        spin.repeatCount = .infinity
        boxNode.addAnimation(spin, forKey: "spin")
    }
}
