package org.jzs.mybaseapp.common.system;

import android.content.Context;
import android.os.Environment;

/**
 * Created by Jzs on 2017/7/24.
 * 用于存放系统的一些常量参数
 * 如基地址、文件存放地址、是否开启log打印、SP储存名
 */

public class AppConfig {

    // 是否开启Log打印
    public static Boolean IS_LOG;

    public static Boolean GLOBAL_EXCEPTION_ENABLE; // 是否开启全部异常捕获
    public static String GLOBAL_EXCEPTION_ACTION;
    public static String GLOBAL_EXCEPTION_SDCARD_DIR; // 异常日志存放路径

    // 图片缓存最大容量，150M，根据自己的需求进行修改
    public static final int GLIDE_CATCH_SIZE = 150 * 1000 * 1000;
    // 图片缓存子目录
    public static final String GLIDE_CARCH_DIR = "image_catch";
    // 应用储存路径
    public static String APP_SDCARD_DIR;

    public static String BASE_URL;
    //知乎地址
    public static String ZHIHU_BASE_URL;
    //吐槽新番地址
    public static String TUCAO_BASE_URL;
    //和风天气
    public static String HEFENG_BASE_URL;

    public static void init(Context context) {
//        BASE_URL = context.getString(R.string.base_url);
        BASE_URL = "http://www.yigongkeji.cc:8010";
        ZHIHU_BASE_URL = "https://news-at.zhihu.com/api/4/";
        TUCAO_BASE_URL = "http://www.tucao.tv/api_v2/";
        HEFENG_BASE_URL = "https://free-api.heweather.com/v5/";
        IS_LOG = true;

        APP_SDCARD_DIR = Environment.getExternalStorageDirectory() + "/MyBaseApp/";
        initExceptionConfig();
    }

    /**
     * 全局异常捕获设置
     */
    public static void initExceptionConfig() {
        GLOBAL_EXCEPTION_ENABLE = true;
        GLOBAL_EXCEPTION_ACTION = "org.jzs.mybaseapp.common.utils.GlobalExceptionHanlder";
        GLOBAL_EXCEPTION_SDCARD_DIR = APP_SDCARD_DIR + "log/";
    }


    /**
     * 登录
     */
    public static final String LOGIN = "/mobile/weixiu/view/member/login";

    //////////////////////知乎日报//////////////////////
    /**
     * 最新消息
     */
    public static final String LATEST = "news/latest";
    /**
     * 过往消息
     */
    public static final String FIX_DATE = "news/before";
    /**
     * 消息内容
     */
    public static final String CONTENT = "news";

    //////////////////////吐槽追番//////////////////////
    /**
     * 新番列表
     */
    public static final String BANGUMILIST = "list.php";


    //////////////////////和风天气//////////////////////
    /**
     * 天气
     */
    public static final String WEATHER = "weather";
    /**
     * 当前天气
     */
    public static final String NOW_WEATHER = "now";
}
