package org.jzs.retrofit;

import android.os.Build;

import org.jzs.AppLog;
import org.jzs.skutils.AppContext.Applications;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author Jzs created 2018/6/6
 * @email 355392668@qq.com
 */
public class MyOkHttpClient {

    public static String token;

    private static OkHttpClient mOkHttpClient;

    public static OkHttpClient getOkHttpClient() {
        if (null == mOkHttpClient) {
            File sdcache = Applications.context().getExternalCacheDir();
            int cacheSize = 10 * 1024 * 1024;

            HttpLoggingInterceptor LoginInterceptor = new HttpLoggingInterceptor(
                    message -> {
                        //打印retrofit日志
//                        AppLog.e("Okhttp", message);
                    }
            );
            LoginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(20, TimeUnit.SECONDS)
                    .readTimeout(20, TimeUnit.SECONDS)
                    .cache(new Cache(sdcache.getAbsoluteFile(), cacheSize))
                    .addInterceptor(LoginInterceptor)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {

                            Request request = chain.request();

                            StringBuilder builder = new StringBuilder(request.url().toString());
                            builder.append("?");

                            RequestBody body = request.body();

                            if (body instanceof FormBody) {
                                FormBody.Builder formBodyBuilder = new FormBody.Builder();
                                FormBody formBody = (FormBody) body;
                                int size = formBody.size();
                                for (int index = 0; index < size; index++) {
                                    String name = formBody.name(index);
                                    String value = formBody.value(index);
                                    formBodyBuilder.add(name, value);
                                    builder.append(name).append("=").append(value).append("&");
                                }

                                if (token != null) {
                                    formBodyBuilder.add("token", token);
                                    builder.append("token").append("=").append(token);
                                }

                                //header
                                request = request.newBuilder()
                                        .method(request.method(), formBodyBuilder.build())
                                        .addHeader("APP_TYPE", "Android")
                                        .addHeader("APP_DEVICE_MODEL", Build.DEVICE)
//                                        .addHeader("APP_VERSION", BasicApp.mVersionName)
//                                        .addHeader("APP_BUILD_VERSION", String.valueOf(BasicApp.mVersionCode))
                                        .addHeader("APP_OS", String.valueOf(Build.VERSION.SDK_INT))
                                        .build();

                            } else {

                                HttpUrl.Builder httpUrlBuilder = request.url().newBuilder();
//                                httpUrlBuilder.addQueryParameter("token", token);

                                request = request.newBuilder()
                                        .url(httpUrlBuilder.build())
                                        .addHeader("APP_TYPE", "Android")
                                        .addHeader("APP_DEVICE_MODEL", Build.DEVICE)
//                                        .addHeader("APP_VERSION", BasicApp.mVersionName)
//                                        .addHeader("APP_BUILD_VERSION", String.valueOf(BasicApp.mVersionCode))
                                        .addHeader("APP_OS", String.valueOf(Build.VERSION.SDK_INT))
                                        .build();
                                builder = new StringBuilder(request.url().toString());
                            }
                            AppLog.e("SkRetrofit", "请求的url：" + builder.toString());

                            Response response = chain.proceed(request);
                            return response;
                        }
                    });
            mOkHttpClient = builder.build();
        }
        return mOkHttpClient;
    }

}
