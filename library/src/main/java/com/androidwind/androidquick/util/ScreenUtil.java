package com.androidwind.androidquick.util;

import android.content.Context;

/**
 * @author ddnosh
 * @website http://blog.csdn.net/ddnosh
 */
public class ScreenUtil {

    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int px2dp(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static int dp2px(Context context, float dipValue) {
        if (context != null) {
            final float scale = context.getResources().getDisplayMetrics().density;
            return (int) (dipValue * scale + 0.5f);
        }
        return (int) dipValue;
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }
}
