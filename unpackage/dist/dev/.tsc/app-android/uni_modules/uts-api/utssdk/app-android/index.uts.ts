import Intent from "android.content.Intent";
import Activity from "android.app.Activity";
import Bundle from "android.os.Bundle";
import LinearLayout from "android.widget.LinearLayout";
import TextView from "android.widget.TextView";
import Color from "android.graphics.Color";
import Gravity from "android.view.Gravity";
import ViewGroup from "android.view.ViewGroup";
import { MyApiOptions, MyApiResult, MyApi, MyApiSync } from '../interface.uts';
import { MyApiFailImpl } from '../unierror';

class HelloActivity extends Activity {
  constructor() {
    super();
  }
  override onCreate(savedInstanceState: Bundle | null): void {
    super.onCreate(savedInstanceState);

    const layout = new LinearLayout(this);
    layout.setOrientation(LinearLayout.VERTICAL);
    layout.setGravity(Gravity.CENTER);
    layout.setBackgroundColor(Color.WHITE);

    const tv = new TextView(this);
    tv.setText("你好");
    // tv.setTextSize(40);
    tv.setTextColor(Color.BLACK);
    tv.setGravity(Gravity.CENTER);

    const params = new LinearLayout.LayoutParams(
      ViewGroup.LayoutParams.WRAP_CONTENT,
      ViewGroup.LayoutParams.WRAP_CONTENT
    );
    layout.addView(tv, params);

    this.setContentView(layout);
  }
}

class My implements Runnable {
  override run() {
    const activity = UTSAndroid.getUniActivity();
    if (activity !== null) {
      try {
        const intent = new Intent(activity, HelloActivity().javaClass);
        activity.startActivity(intent);
      } catch (e) {
        __f__('log','at uni_modules/uts-api/utssdk/app-android/index.uts:48','e', e);
      }
    }
  }
}

// 异步方法：打开新Activity页面
export const myApi: MyApi = function (options: MyApiOptions) {
	const activity = UTSAndroid.getUniActivity();
	if (activity !== null) {
		activity.runOnUiThread(new My());
	}
}