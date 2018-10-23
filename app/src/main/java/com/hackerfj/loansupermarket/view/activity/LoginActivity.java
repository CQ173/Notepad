package com.hackerfj.loansupermarket.view.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hackerfj.loansupermarket.MainActivity;
import com.hackerfj.loansupermarket.R;
import com.hackerfj.loansupermarket.app.constant.Global;
import com.hackerfj.loansupermarket.model.entity.res.StingsRes;
import com.hackerfj.loansupermarket.model.entity.res.UserinfoRes;
import com.hackerfj.loansupermarket.util.ToastUtil;
import com.hackerfj.loansupermarket.util.network.Api;
import com.hackerfj.loansupermarket.util.network.RxHelper;
import com.hackerfj.loansupermarket.util.network.RxSubscriber;
import com.hackerfj.loansupermarket.view.activity.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.et_phone)
    EditText et_phone;
    @BindView(R.id.et_code)
    EditText et_code;
    @BindView(R.id.tv_login)
    TextView tv_login;

    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {

    }
    @OnClick({ R.id.tv_login})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.tv_login:
                if ("".equals(et_phone.getText().toString())){
                    ToastUtil.show("请输入手机号码!");
                }else if (et_phone.getText().toString().trim().length() != 11) {
                    ToastUtil.show("账号格式错误!");
                }else if (et_code.getText().toString().trim().length() != 6 ) {
                    ToastUtil.show("密码格式错误!");
                }else if ("".equals(et_code.getText().toString())) {
                    ToastUtil.show("请输入密码!");
                }else {
                    Api.getDefault().reloadlogin(et_phone.getText().toString(), et_code.getText().toString())
                            .compose(RxHelper.handleResult())
                            .subscribe(new RxSubscriber<UserinfoRes>(this) {
                                @Override
                                protected void _onNext(UserinfoRes list) {
                                    //Token
                                    Global.saveToken(list.getToken());
                                    //用户id
                                    Global.saveID(list.getId());
                                    //username
                                    Global.saveUsername(list.getName());
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    ToastUtil.show("登录成功!");
                                    finish();
                                }

                                @Override
                                protected void _onError(String msg) {
                                    ToastUtil.show("登录失败!");
                                }
                            });
                }
                break;
        }
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

}
