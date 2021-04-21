package org.jzs.mybaseapp;

import android.app.ActivityManager;
import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;

import org.jzs.mybaseapp.common.system.AppConfig;
import org.jzs.mybaseapp.common.system.SharedPreferencesUtil;
import org.jzs.mybaseapp.common.utils.AppManager;
import org.jzs.mybaseapp.common.utils.GlobalExceptionHandler;

/**
 * Created by Jzs on 2017/7/19.
 * 全局Applaciton
 */
public class MyApplaciton extends MultiDexApplication {

    private static MyApplaciton instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        // 初始化参数
        AppConfig.init(this);
        // 全局异常捕获
//        GlobalExceptionHandler.getInstance().register(this);

        boolean isNight = SharedPreferencesUtil.getBoolean("night", false);
        if (isNight) {
            //使用夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public static MyApplaciton getInstance() {
        if (instance == null) {
            instance = new MyApplaciton();
            return instance;
        } else {
            return instance;
        }
    }

    /**
     * 退出应用程序
     */
    public void AppExit(Context context) {
        try {
            AppManager.getInstance().finishAllActivity();

            ActivityManager activityMgr = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityMgr.killBackgroundProcesses(context.getPackageName());
            // restartPackage(context.getPackageName());
            System.exit(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
