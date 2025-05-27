<template>
	<view />
</template>

<script lang="uts">
	import { CubeGLView } from 'uts.sdk.modules.tusenTView';
	import GLSurfaceView from "android.opengl.GLSurfaceView";
	import Button from "android.widget.Button";
	import LinearLayout from "android.widget.LinearLayout";

	export default {
		name: "tusen-t-view",
		NVLoad() : LinearLayout {
			console.log("=== 开始创建UTS组件 ===");
			const container = new LinearLayout($androidContext!);
			try {
				// 创建容器布局

				container.setOrientation(LinearLayout.VERTICAL);
				container.setLayoutParams(new android.view.ViewGroup.LayoutParams(
					android.view.ViewGroup.LayoutParams.MATCH_PARENT,
					android.view.ViewGroup.LayoutParams.MATCH_PARENT
				));

				// 创建GLSurfaceView
				const glView = new CubeGLView($androidContext!);
				console.log("CubeGLView created:", glView);

				glView.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					600 // 固定高度800px
				));

				// 创建测试按钮
				const button = new Button($androidContext!);
				button.setText("GLSurfaceView测试");
				button.setLayoutParams(new LinearLayout.LayoutParams(
					LinearLayout.LayoutParams.MATCH_PARENT,
					LinearLayout.LayoutParams.WRAP_CONTENT
				));

				// 添加到容器
				container.addView(glView);
				container.addView(button);
			} catch (error) {
				console.error("创建UTS组件失败:", error);
				throw error; // 抛出错误以便调试
			}


			console.log("=== 组件创建完成 ===");
			return container;
		}
	}
</script>

<style>
	:host,
	view {
		display: block;
		width: 100%;
		height: 100%;
	}
</style>