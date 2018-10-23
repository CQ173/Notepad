package com.hackerfj.loansupermarket.view.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.blankj.utilcode.utils.StringUtils;
import com.hackerfj.loansupermarket.MainActivity;
import com.hackerfj.loansupermarket.app.constant.Global;
import com.hackerfj.loansupermarket.util.StartActivityUtil;

public class StartpageActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gohome();
    }

    public void gohome(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (StringUtils.isEmpty(Global.getToken())){
            StartActivityUtil.start(this , LoginActivity.class);
        }else {
            StartActivityUtil.start(this , MainActivity.class);
        }
        finish();
    }
}
