package com.hackerfj.loansupermarket.app.constant;

/**
 *
 * @author 付杰
 * @date 2017/5/23
 */

public class Global {
    /**
     * 保存token
     */
    public static String getToken(){
        return SharePref.getInstance().getString(SharePref.TOKEN);
    }

    public static void saveToken(String Token){
        SharePref.getInstance().putString(SharePref.TOKEN,Token);
    }

    /**
     * ID
     */
    public static int getId(){
        return SharePref.getInstance().getInt(SharePref.ID);
    }

    public static void saveID(int id){
        SharePref.getInstance().putInt(SharePref.ID,id);
    }
    /**
     * username
     */
    public static String getUsername(){
        return SharePref.getInstance().getString(SharePref.USERNAME);
    }

    public static void saveUsername(String username) {
        SharePref.getInstance().putString(SharePref.USERNAME, username);
    }
}
