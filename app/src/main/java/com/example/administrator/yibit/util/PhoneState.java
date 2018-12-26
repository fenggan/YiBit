package com.example.administrator.yibit.util;

import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class PhoneState {
    /**
     *
     * @param activity
     * @return  当前手机屏幕的高度
     */
    public static int getScreenHeight(Activity activity){
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.heightPixels;
    }

    /**
     *
     * @param activity
     * @return        当前手机屏幕的宽度
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    /**
     * 设置手机屏幕的透明度
     * @param activity
     * @param bgAlpha
     */
    public static void setBackgroundAlpha(Activity activity,float bgAlpha) {
        WindowManager.LayoutParams lp = activity.getWindow()
                .getAttributes();
        lp.alpha = bgAlpha;
        activity.getWindow().setAttributes(lp);
    }
}
