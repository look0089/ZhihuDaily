package org.jzs.mybaseapp.section.zhihu;

import org.jzs.mybaseapp.section.zhihu.entity.DailyNews;
import org.jzs.mybaseapp.section.zhihu.entity.NewsDetail;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import rx.Observable;

public interface ZhihuService {

    /**
     * 最新消息
     */
    @GET("news/latest")
    Observable<DailyNews> getLatestNews();

    /**
     * 过往消息
     */
    @GET("news/before" + "/{date}")
    Observable<DailyNews> getFixDateNews(@Path("date") String oid);

    /**
     * 消息内容
     */
    @GET("news" + "/{id}")
    Observable<NewsDetail> getEssayContent(@Path("id") String id);

    @GET
    Observable<ResponseBody> getEssayCSS(@Url String url);

}
