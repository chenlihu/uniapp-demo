import { resolveDynamicComponent, resolveComponent, openBlock, createElementBlock, createElementVNode, createVNode, withCtx, createTextVNode } from "vue";
function formatAppLog(type, filename, ...args) {
  if (uni.__log__) {
    uni.__log__(type, filename, ...args);
  } else {
    console[type].apply(console, [...args, filename]);
  }
}
function resolveEasycom(component, easycom) {
  return typeof component === "string" ? easycom : component;
}
uni;
const TusenTViewComponent = {};
const { registerUTSInterface, initUTSProxyClass, initUTSProxyFunction, initUTSPackageName, initUTSIndexClassName, initUTSClassName } = uni;
const name = "utsApi";
const moduleName = "ts-demo";
const moduleType = "";
const errMsg = ``;
const is_uni_modules = true;
const pkg = /* @__PURE__ */ initUTSPackageName(name, is_uni_modules);
const cls = /* @__PURE__ */ initUTSIndexClassName(name, is_uni_modules);
const myApi = /* @__PURE__ */ initUTSProxyFunction(false, { moduleName, moduleType, errMsg, main: true, package: pkg, class: cls, name: "myApiByJs", keepAlive: false, params: [{ "name": "options", "type": "UTSSDKModulesUtsApiMyApiOptionsJSONObject" }], return: "" });
const _style_0 = { "content": { "": { "flex": 1, "alignItems": "center", "justifyContent": "center", "display": "flex" } }, "main-btn": { "": { "width": "300rpx", "height": "80rpx", "backgroundColor": "#007AFF", "color": "#ffffff", "borderRadius": "10rpx", "fontSize": "32rpx" } } };
const _export_sfc = (sfc, props) => {
  const target = sfc.__vccOpts || sfc;
  for (const [key, val] of props) {
    target[key] = val;
  }
  return target;
};
const _sfc_main = {
  methods: {
    openHelloPage() {
      myApi({
        paramA: true,
        success: (res) => {
          formatAppLog("log", "at pages/index/index.nvue:17", "新页面打开成功", res);
        },
        fail: (err) => {
          uni.showToast({
            title: "插件未安装或未编译",
            icon: "none"
          });
          formatAppLog("error", "at pages/index/index.nvue:25", "打开失败", err);
        }
      });
    }
  }
};
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  resolveEasycom(resolveDynamicComponent("tusen-t-view"), TusenTViewComponent);
  const _component_button = resolveComponent("button");
  return openBlock(), createElementBlock("scroll-view", {
    scrollY: true,
    showScrollbar: true,
    enableBackToTop: true,
    bubble: "true",
    style: { flexDirection: "column" }
  }, [
    createElementVNode("view", { class: "content" }, [
      createElementVNode(
        "tusen-t-view",
        {
          buttontext: "点击按钮内容",
          onButtonclick: _cache[0] || (_cache[0] = (...args) => _ctx.onButtonClick && _ctx.onButtonClick(...args)),
          style: { "width": "375px", "height": "60px", "background-color": "aqua" }
        },
        null,
        32
        /* NEED_HYDRATION */
      ),
      createVNode(_component_button, {
        onClick: $options.openHelloPage,
        class: "main-btn"
      }, {
        default: withCtx(() => [
          createTextVNode("打开新页面")
        ]),
        _: 1
        /* STABLE */
      }, 8, ["onClick"])
    ])
  ]);
}
const index = /* @__PURE__ */ _export_sfc(_sfc_main, [["render", _sfc_render], ["styles", [_style_0]], ["__file", "/Users/tiger/Documents/HBuilderProjects/demo/pages/index/index.nvue"]]);
export {
  index as default
};
