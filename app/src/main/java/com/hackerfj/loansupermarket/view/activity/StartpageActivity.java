package com.hackerfj.loansupermarket.view.activity;


import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.hackerfj.loansupermarket.MainActivity;
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
        StartActivityUtil.start(this , MainActivity.class);
        finish();
    }
}
