package org.jzs.mybaseapp.section.tucao;

import org.jzs.mybaseapp.section.tucao.entity.Bangumi;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

public interface TucaoService {

    /**
     * 获取新番列表
     * http://www.tucao.tv/api_v2/list.php?type=json&apikey=25tids8f1ew1821ed&tid=19&page=1&pagesize=100
     */
    @FormUrlEncoded
    @POST("api_v2/list.php")
    Observable<Bangumi> getBangumiList(@Field("type") String type,
                                       @Field("apikey") String apikey,
                                       @Field("tid") String tid,
                                       @Field("page") String page,
                                       @Field("pagesize") String pagesize);


}
