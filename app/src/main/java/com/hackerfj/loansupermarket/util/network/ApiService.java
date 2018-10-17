package com.hackerfj.loansupermarket.util.network;


import com.hackerfj.loansupermarket.model.entity.res.GetHomePageRes;
import com.hackerfj.loansupermarket.model.entity.res.GetHomebannerRes;
import com.hackerfj.loansupermarket.model.entity.res.GetUserRes;
import com.hackerfj.loansupermarket.model.entity.res.Param;
import com.hackerfj.loansupermarket.model.entity.res.SangeRes;
import com.hackerfj.loansupermarket.model.entity.res.TypeofloanRes;
import com.hackerfj.loansupermarket.model.entity.res.UserinfoRes;
import com.hackerfj.loansupermarket.model.entity.res.WholeInfoRes;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ApiService {

    /**
     * 获取banner
     */
    @GET(UrlUtil.BANNER)
    Observable<BaseModel<List<GetHomebannerRes>>> getHomeInfo(

    );


    /**
     * 添加
     */
    @GET(UrlUtil.ALL_PRODUCTTS)
    Observable<BaseModel<List<String>>> addmoney(
            @Query("money") String money
    );

    /**
     * tjia
     */
    @GET(UrlUtil.ADD_ALL)
    Observable<BaseModel<List<String>>> addall(
            @Query("type") Integer type ,
            @Query("aclass") Integer aclass ,
            @Query("money") String money
    );

    /**
     * 三个
     */
    @GET(UrlUtil.ALL_MONEY)
    Observable<BaseModel<SangeRes>> allmoney(

    );

    /**
     * 获取短信验证码
     */
    @FormUrlEncoded
    @POST(UrlUtil.GET_CODE)
    Observable<BaseModel<Param>> getiphonecode(
            @Field("mobile") String mobile,
            @Field("sign") String sign
    );

    /**
     * 用户登录
     */
    @FormUrlEncoded
    @POST(UrlUtil.LOGIN_USER)
    Observable<BaseModel<UserinfoRes>> reloadlogin(
            @Field("mobile") String mobile,
            @Field("sign") String sign,
            @Field("verifyCode") String verifyCode
    );

    /**
     * 退出登录
     */
    @GET(UrlUtil.EXIT_LOGON)
    Observable<BaseModel<List<String>>> exitlogon(
            @Query("token") String token
    );

    /**
     * 用户中心获取用户个人信息
     */
    @FormUrlEncoded
    @POST(UrlUtil.GET_USER)
    Observable<BaseModel<GetUserRes>> getuser(
            @Field("token") String token,
            @Field("uid") String uid
    );


}
