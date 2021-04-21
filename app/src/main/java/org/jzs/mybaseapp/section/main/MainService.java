package org.jzs.mybaseapp.section.main;

import org.jzs.mybaseapp.section.main.entity.WeatherEntity;
import org.jzs.mybaseapp.section.zhihu.entity.DailyNews;
import org.jzs.mybaseapp.section.zhihu.entity.NewsDetail;

import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

public interface MainService {
    /**
     * 当前天气        maps.put("city", city);
     * maps.put("key", Constant.HF_KEY);
     */
    @FormUrlEncoded
    @POST("now")
    Observable<WeatherEntity> getNowWeather(@Field("city") String city,
                                            @Field("key") String key);

}
