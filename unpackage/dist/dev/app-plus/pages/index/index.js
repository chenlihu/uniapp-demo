"use weex:vue";

if (typeof Promise !== 'undefined' && !Promise.prototype.finally) {
  Promise.prototype.finally = function(callback) {
    const promise = this.constructor
    return this.then(
      value => promise.resolve(callback()).then(() => value),
      reason => promise.resolve(callback()).then(() => {
        throw reason
      })
    )
  }
};

if (typeof uni !== 'undefined' && uni && uni.requireGlobal) {
  const global = uni.requireGlobal()
  ArrayBuffer = global.ArrayBuffer
  Int8Array = global.Int8Array
  Uint8Array = global.Uint8Array
  Uint8ClampedArray = global.Uint8ClampedArray
  Int16Array = global.Int16Array
  Uint16Array = global.Uint16Array
  Int32Array = global.Int32Array
  Uint32Array = global.Uint32Array
  Float32Array = global.Float32Array
  Float64Array = global.Float64Array
  BigInt64Array = global.BigInt64Array
  BigUint64Array = global.BigUint64Array
};


(() => {
  var __create = Object.create;
  var __defProp = Object.defineProperty;
  var __getOwnPropDesc = Object.getOwnPropertyDescriptor;
  var __getOwnPropNames = Object.getOwnPropertyNames;
  var __getProtoOf = Object.getPrototypeOf;
  var __hasOwnProp = Object.prototype.hasOwnProperty;
  var __commonJS = (cb, mod) => function __require() {
    return mod || (0, cb[__getOwnPropNames(cb)[0]])((mod = { exports: {} }).exports, mod), mod.exports;
  };
  var __copyProps = (to, from, except, desc) => {
    if (from && typeof from === "object" || typeof from === "function") {
      for (let key of __getOwnPropNames(from))
        if (!__hasOwnProp.call(to, key) && key !== except)
          __defProp(to, key, { get: () => from[key], enumerable: !(desc = __getOwnPropDesc(from, key)) || desc.enumerable });
    }
    return to;
  };
  var __toESM = (mod, isNodeMode, target) => (target = mod != null ? __create(__getProtoOf(mod)) : {}, __copyProps(
    // If the importer is in node compatibility mode or this is not an ESM
    // file that has been converted to a CommonJS file using a Babel-
    // compatible transform (i.e. "__esModule" has not been set), then set
    // "default" to the CommonJS "module.exports" for node compatibility.
    isNodeMode || !mod || !mod.__esModule ? __defProp(target, "default", { value: mod, enumerable: true }) : target,
    mod
  ));

  // vue-ns:vue
  var require_vue = __commonJS({
    "vue-ns:vue"(exports, module) {
      module.exports = Vue;
    }
  });

  // ../../../../../../Users/tiger/Documents/HBuilderProjects/demo/unpackage/dist/dev/.nvue/pages/index/index.js
  var import_vue = __toESM(require_vue());
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
  var TusenTViewComponent = {};
  var { registerUTSInterface, initUTSProxyClass, initUTSProxyFunction, initUTSPackageName, initUTSIndexClassName, initUTSClassName } = uni;
  var name = "utsApi";
  var moduleName = "ts-demo";
  var moduleType = "";
  var errMsg = ``;
  var is_uni_modules = true;
  var pkg = /* @__PURE__ */ initUTSPackageName(name, is_uni_modules);
  var cls = /* @__PURE__ */ initUTSIndexClassName(name, is_uni_modules);
  var myApi = /* @__PURE__ */ initUTSProxyFunction(false, { moduleName, moduleType, errMsg, main: true, package: pkg, class: cls, name: "myApiByJs", keepAlive: false, params: [{ "name": "options", "type": "UTSSDKModulesUtsApiMyApiOptionsJSONObject" }], return: "" });
  var _style_0 = { "content": { "": { "flex": 1, "alignItems": "center", "justifyContent": "center", "display": "flex" } }, "main-btn": { "": { "width": "300rpx", "height": "80rpx", "backgroundColor": "#007AFF", "color": "#ffffff", "borderRadius": "10rpx", "fontSize": "32rpx" } } };
  var _export_sfc = (sfc, props) => {
    const target = sfc.__vccOpts || sfc;
    for (const [key, val] of props) {
      target[key] = val;
    }
    return target;
  };
  var _sfc_main = {
    methods: {
      openHelloPage() {
        myApi({
          paramA: true,
          success: (res) => {
            formatAppLog("log", "at pages/index/index.nvue:17", "\u65B0\u9875\u9762\u6253\u5F00\u6210\u529F", res);
          },
          fail: (err) => {
            uni.showToast({
              title: "\u63D2\u4EF6\u672A\u5B89\u88C5\u6216\u672A\u7F16\u8BD1",
              icon: "none"
            });
            formatAppLog("error", "at pages/index/index.nvue:25", "\u6253\u5F00\u5931\u8D25", err);
          }
        });
      }
    }
  };
  function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
    resolveEasycom((0, import_vue.resolveDynamicComponent)("tusen-t-view"), TusenTViewComponent);
    const _component_button = (0, import_vue.resolveComponent)("button");
    return (0, import_vue.openBlock)(), (0, import_vue.createElementBlock)("scroll-view", {
      scrollY: true,
      showScrollbar: true,
      enableBackToTop: true,
      bubble: "true",
      style: { flexDirection: "column" }
    }, [
      (0, import_vue.createElementVNode)("view", { class: "content" }, [
        (0, import_vue.createElementVNode)(
          "tusen-t-view",
          {
            buttontext: "\u70B9\u51FB\u6309\u94AE\u5185\u5BB9",
            onButtonclick: _cache[0] || (_cache[0] = (...args) => _ctx.onButtonClick && _ctx.onButtonClick(...args)),
            style: { "width": "375px", "height": "60px", "background-color": "aqua" }
          },
          null,
          32
          /* NEED_HYDRATION */
        ),
        (0, import_vue.createVNode)(_component_button, {
          onClick: $options.openHelloPage,
          class: "main-btn"
        }, {
          default: (0, import_vue.withCtx)(() => [
            (0, import_vue.createTextVNode)("\u6253\u5F00\u65B0\u9875\u9762")
          ]),
          _: 1
          /* STABLE */
        }, 8, ["onClick"])
      ])
    ]);
  }
  var index = /* @__PURE__ */ _export_sfc(_sfc_main, [["render", _sfc_render], ["styles", [_style_0]], ["__file", "/Users/tiger/Documents/HBuilderProjects/demo/pages/index/index.nvue"]]);

  // <stdin>
  var webview = plus.webview.currentWebview();
  if (webview) {
    const __pageId = parseInt(webview.id);
    const __pagePath = "pages/index/index";
    let __pageQuery = {};
    try {
      __pageQuery = JSON.parse(webview.__query__);
    } catch (e) {
    }
    index.mpType = "page";
    const app = Vue.createPageApp(index, { $store: getApp({ allowDefault: true }).$store, __pageId, __pagePath, __pageQuery });
    app.provide("__globalStyles", Vue.useCssStyles([...__uniConfig.styles, ...index.styles || []]));
    app.mount("#root");
  }
})();
