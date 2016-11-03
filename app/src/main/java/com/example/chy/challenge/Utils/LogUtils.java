package com.example.chy.challenge.Utils;

import android.util.Log;

/**
 * Created by 77588 on 2016/9/20.
 */
public class LogUtils {
    private static final boolean d = true;

    public static void i(String tag, String message) {
        if (d) {
            Log.i(tag, message);
        }
    }

    public static void d(String tag, String message) {
        if (d) {
            Log.d(tag, message);
        }
    }

    public static void e(String tag, String message) {
        if (d) {
            Log.e(tag, message);
        }
    }

    public static void v(String tag, String message) {
        if (d) {
            Log.v(tag, message);
        }
    }

    public static void w(String tag, String message) {
        // TODO Auto-generated method stub
        if (d) {
            Log.w(tag, message);
        }
    }
}
