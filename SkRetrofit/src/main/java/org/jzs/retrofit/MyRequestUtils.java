package org.jzs.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.jzs.AppLog;
import org.jzs.skretrofit.R;
import org.jzs.skutils.AppContext.Applications;

import java.io.File;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/6/16 0016.
 */

public class MyRequestUtils {

    public static String BASE_URL = "";
    private static Gson gson;


    /**
     * 与后台规定好返回格式，将 "data" 字段直接转成 Entity
     *
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T getRequestServices(Class<T> service) {
        return getRetrofit().create(service);
    }

    public static Retrofit getRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MyConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(MyOkHttpClient.getOkHttpClient())
                .build();
    }

    /**
     * 使用Gson解析
     *
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T getCommonRequestServices(Class<T> service) {
        return getCommonRetrofit().create(service);
    }

    /**
     * 使用Gson解析
     *
     * @param service
     * @param <T>
     * @return
     */
    public static <T> T getCommonRequestServices(String baseUrl, Class<T> service) {
        BASE_URL = baseUrl;
        return getCommonRetrofit().create(service);
    }


    public static Retrofit getCommonRetrofit() {
        HttpLoggingInterceptor LoginInterceptor = new HttpLoggingInterceptor(
                message -> {
                    AppLog.e("Okhttp", message);
                }
        );
        OkHttpClient builder = MyOkHttpClient.getOkHttpClient().newBuilder().addInterceptor(LoginInterceptor).build();
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(builder)
                .build();
    }

    private static Gson getGson() {
        if (null == gson) {
            gson = new GsonBuilder()
                    .setDateFormat("yyyy-MM-dd hh:mm:ss")
                    .create();
        }
        return gson;
    }

    /**
     * 单个文件
     *
     * @param filePath 文件路径
     * @return
     */
    public static RequestBody getImageRequestBody(String filePath) {
        File file = new File(filePath);
        RequestBody imgBody = RequestBody.create(MediaType.parse("form-data"), file);
        return imgBody;
    }

    /**
     * 多张图片
     *
     * @param filePathList 文件路径数组
     * @return
     */
    public static List<MultipartBody.Part> getImageRequestBodyWithMultipart(List<String> filePathList, String key) {
        // 多张图片
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);//表单类型
        for (String _path : filePathList) {
            File file = new File(_path);
            RequestBody imageBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//            builder.addFormDataPart("galleryPic", file.getName(), imageBody);//galleryPic 后台接收图片流的参数名
            builder.addFormDataPart(key, file.getName(), imageBody);//galleryPic 后台接收图片流的参数名
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        return parts;
    }

    public static void post(String url, Map<String, String> params, Callback callback) {
        StringBuffer _sb = new StringBuffer();
        _sb.append(url).append("?");

        FormBody.Builder builder = new FormBody.Builder();
        for (String _key : params.keySet()) {
            String _val = params.get(_key);
            builder.add(_key, _val);
            _sb.append(_key).append("=").append(_val).append("&");
        }

        AppLog.e("_sb.toString()");

        RequestBody formBody = builder.build();

        OkHttpClient mOkHttpClient = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(callback);
    }

}
