package com.hackerfj.loansupermarket.util.network;

import com.hackerfj.loansupermarket.model.entity.res.BeannRes;
import com.hackerfj.loansupermarket.model.entity.res.GetHomePageRes;
import com.hackerfj.loansupermarket.model.entity.res.GetHomebannerRes;
import com.hackerfj.loansupermarket.model.entity.res.SangeRes;
import com.hackerfj.loansupermarket.model.entity.res.StingsRes;
import com.hackerfj.loansupermarket.model.entity.res.UserinfoRes;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    /**
     * 获取
     */
    @GET(UrlUtil.BANNER)
    Observable<BaseModel<List<GetHomebannerRes>>> getHomeInfo(
            @Query("uid") Integer uid,
            @Query("type") Integer type
    );

    /**
     * 添加
     */
    @GET(UrlUtil.ALL_PRODUCTTS)
    Observable<BaseModel<StingsRes>> addmoney(
            @Query("uid") Integer uid,
            @Query("money") String money
    );

    /**
     * tjia
     */
    @GET(UrlUtil.ADD_ALL)
    Observable<BaseModel<StingsRes>> addall(
            @Query("uid") Integer uid,
            @Query("type") Integer type ,
            @Query("aclass") Integer aclass ,
            @Query("money") String money
    );

    /**
     * 三个
     */
    @GET(UrlUtil.ALL_MONEY)
    Observable<BaseModel<SangeRes>> allmoney(
            @Query("uid") Integer uid
    );

    /**
     * 用户登录
     */
    @GET(UrlUtil.LOGIN_USER)
    Observable<BaseModel<UserinfoRes>> reloadlogin(
            @Query("name") String name,
            @Query("passwd") String passwd
    );

    /**
     * 退出登录
     */
    @GET(UrlUtil.EXIT_LOGON)
    Observable<BaseModel<GetHomePageRes>> exitlogon(
            @Query("token") String token
    );

    /**
     * 统计图
     */
    @GET(UrlUtil.TJITU)
    Observable<BaseModel<BeannRes>> alltjt(
            @Query("uid") Integer uid
    );



}
