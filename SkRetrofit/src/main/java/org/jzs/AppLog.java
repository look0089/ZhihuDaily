package org.jzs;

import android.util.Log;

import org.jzs.skretrofit.BuildConfig;

/**
 * 打印日志调用
 */
public class AppLog {

    public static String TAG = "SkRetrofit";

    public static boolean IS_LOG = BuildConfig.DEBUG;

    public static void e(String aLogInfo) {
        Log.e(TAG, aLogInfo);
    }

    public static void e(String aTag, String aLogInfo) {
        Log.e(TAG, aLogInfo);
    }

    public static void redLog(String aTag, String aLogInfo) {
        if (IS_LOG && aLogInfo != null) {
            Log.e(aTag, aLogInfo);
            aLogInfo = null;
            aTag = null;
        } else if (IS_LOG && aLogInfo == null) {
            Log.e(aTag, "null error");
        }
    }

    public static void greenLog(String aTag, String aLogInfo) {
        if (IS_LOG && aLogInfo != null) {
            Log.i(aTag, aLogInfo);
            aLogInfo = null;
            aTag = null;
        } else if (IS_LOG && aLogInfo == null) {
            Log.e(aTag, "null error");
        }
    }

    public static void yellowLog(String aTag, String aLogInfo) {
        if (IS_LOG && aLogInfo != null) {
            Log.w(aTag, aLogInfo);
            aLogInfo = null;
            aTag = null;
        } else if (IS_LOG && aLogInfo == null) {
            Log.e(aTag, "null error");
        }
    }

    public static void blackLog(String aTag, String aLogInfo) {
        if (IS_LOG && aLogInfo != null) {
            Log.d(aTag, aLogInfo);
            aLogInfo = null;
            aTag = null;
        } else if (IS_LOG && aLogInfo == null) {
            Log.e(aTag, "null error");
        }
    }

    public static void debug(String aTag, String aLogInfo) {
        if (IS_LOG && aLogInfo != null) {
            Log.e(aTag, aLogInfo);
            aLogInfo = null;
            aTag = null;
        } else if (IS_LOG && aLogInfo == null) {
            Log.e(aTag, "null error");
        }

    }
}
