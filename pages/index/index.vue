<template>
  <view class="content">
    <button @click="openHelloPage" class="main-btn">打开新页面</button>
  </view>
</template>

<script>
import { myApi } from "@/uni_modules/uts-api";
export default {
  methods: {
    openHelloPage() {
      // 调用uts-api插件，弹出新页面
      myApi({
        paramA: true,
        success: (res) => {
          console.log("新页面打开成功", res);
        },
        fail: (err) => {
          // 这里如果报 "ts-demo not found" 说明插件未正确安装或未编译
          uni.showToast({
            title: "插件未安装或未编译",
            icon: "none"
          });
          console.error("打开失败", err);
        }
      });
    }
  }
}
</script>

<style>
.content {
  flex: 1;
  align-items: center;
  justify-content: center;
  display: flex;
}
.main-btn {
  width: 300rpx;
  height: 80rpx;
  background: #007AFF;
  color: #fff;
  border-radius: 10rpx;
  font-size: 32rpx;
}
</style>

