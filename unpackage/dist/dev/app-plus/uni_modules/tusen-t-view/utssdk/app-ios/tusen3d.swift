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

        // Enable camera controls for debugging
        sceneView.allowsCameraControl = true
        // For debugging, give the sceneView a distinct background color
        // sceneView.backgroundColor = UIColor.lightGray 

        let scene = SCNScene()
        sceneView.scene = scene
        sceneView.backgroundColor = UIColor.clear
        
        guard  let url = Bundle.main.url(forResource: "male02", withExtension: "obj") else {
            fatalError("baby_groot.obj not exit.")
        }
		
		guard let customNode = SCNReferenceNode(url: url) else {
		    fatalError("load baby_groot error.")
		}
		customNode.load()
        // Adjust the model's position
        customNode.position = SCNVector3(x: 0, y: 0, z: 0) // Adjust y as needed based on model
        // Adjust model scale - make it smaller
        customNode.scale = SCNVector3(x: 0.2, y: 0.2, z: 0.2) // Example scale, adjust as needed
		        scene.rootNode.addChildNode(customNode)
        print("node finish")

        /* Temporarily comment out the cube to simplify the scene
        // 创建立方体
        let box = SCNBox(width: 1, height: 1, length: 1, chamferRadius: 0.05)
        let boxNode = SCNNode(geometry: box)
        scene.rootNode.addChildNode(boxNode)
        */

        // 添加光源
        // let light = SCNLight()
        // light.type = .omni
        // let lightNode = SCNNode()
        // lightNode.light = light
        // lightNode.position = SCNVector3(x: 2, y: 2, z: 2)
        // scene.rootNode.addChildNode(lightNode)

        // 添加摄像机
        let camera = SCNCamera()
        let cameraNode = SCNNode()
        cameraNode.camera = camera
        cameraNode.position = SCNVector3(x: 0, y: 0, z: 100)
        // Ensure camera's far clipping plane is sufficient
        camera.zFar = 1000
        scene.rootNode.addChildNode(cameraNode)

        /* Temporarily comment out the cube's animation
        // 添加旋转动画
        let spin = CABasicAnimation(keyPath: "rotation")
        spin.fromValue = NSValue(scnVector4: SCNVector4(0, 1, 0, 0))
        spin.toValue = NSValue(scnVector4: SCNVector4(0, 1, 0, Float.pi * 2))
        spin.duration = 3
        spin.repeatCount = .infinity
        boxNode.addAnimation(spin, forKey: "spin")
        */
    }
}
