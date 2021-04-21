package org.jzs.mybaseapp.section.zhihu;

import org.jzs.mybaseapp.common.system.AppConfig;
import org.jzs.mybaseapp.section.zhihu.entity.DailyNews;
import org.jzs.mybaseapp.section.zhihu.entity.NewsDetail;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Jzs on 2017/9/11 0011.
 */

public interface ZhihuApi {


    @GET(AppConfig.LATEST)
    Observable<DailyNews> getLatestNews();

    @GET(AppConfig.FIX_DATE + "/{date}")
    Observable<DailyNews> getFixDateNews(@Path("date") String oid);


    @GET(AppConfig.CONTENT + "/{id}")
    Observable<NewsDetail> getEssayContent(@Path("id") String id);

    @GET
    Observable<ResponseBody> getEssayCSS(@Url String url);
}
