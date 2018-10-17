package com.hackerfj.loansupermarket.util.network;



public class UrlUtil {
    //外网服务器地址
//    public static final String BASE_URL = "http://api.aoyi.pro:8080/api/";
    /**
     * 加载图片域名
     */
     public static final String BANNER_URL = "https://www.yidongdaichao.com";
    public static final String IMAGE_URL = "https://www.yidongdaichao.com/Public/";

    //内网服务器地址
    public static final String BASE_URL = "http://www.ydqb365.com/";
    /**
     *获取
     */
    public static final String BANNER = "api";

    /**
     * 添加
     */
    public static final String ALL_PRODUCTTS = "api/budget";

    /**
     * tjia
     */
    public static final String ADD_ALL = "api/income";

    /**
     * 三个
     */
    public static final String ALL_MONEY = "api/Monthly";

    /**
     * 获取短信验证码
     */
    public static final String GET_CODE = "appapi/User/verifiDriver";

    /**
     * 登录
     */
    public static final String LOGIN_USER = "appapi/User/loginact";

    /**
     * 退出登录
     */
    public static final String EXIT_LOGON = "appapi/User/logout";

    /**
     * 用户中心获取用户个人信息
     */
    public static final String GET_USER = "appapi/User/getUserInfo";

}
