package com.hackerfj.loansupermarket.view.activity;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.blankj.utilcode.utils.StringUtils;
import com.hackerfj.loansupermarket.R;
import com.hackerfj.loansupermarket.app.constant.Global;
import com.hackerfj.loansupermarket.app.constant.SharePref;
import com.hackerfj.loansupermarket.model.entity.res.GetHomePageRes;
import com.hackerfj.loansupermarket.util.StartActivityUtil;
import com.hackerfj.loansupermarket.util.network.Api;
import com.hackerfj.loansupermarket.util.network.RxHelper;
import com.hackerfj.loansupermarket.util.network.RxSubscriber;
import com.hackerfj.loansupermarket.view.activity.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SetUpActivity extends BaseActivity {

    //------****** 缓存相关****----------
    private final int CLEAN_SUC=1001;
    private final int CLEAN_FAIL=1002;

    @BindView(R.id.ll_Exit_logon)
    LinearLayout ll_Exit_logon;
//    @BindView(R.id.tvCache)
//    TextView tvCache;
    @Override
    protected void setContentLayout() {
        setContentView(R.layout.activity_setup);
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.ll_Exit_logon , R.id.ll_feedback , R.id.ll_about ,R.id.iv_return})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.ll_Exit_logon:
                Exitlogon();
                break;
            case R.id.ll_feedback:
                StartActivityUtil.start(this , FeedbackActivity.class);
                break;
            case R.id.ll_about:
                StartActivityUtil.start(this , AboutActivity.class);
                break;
            case R.id.iv_return:
                finish();
                break;
        }
    }

        public void Exitlogon(){
        if (StringUtils.isEmpty(Global.getToken())){
            Toast.makeText(this, "您还没有登录哦！", Toast.LENGTH_SHORT).show();
        }else {
            Api.getDefault().exitlogon(Global.getToken())
                    .compose(RxHelper.handleResult())
                    .subscribe(new RxSubscriber<GetHomePageRes>(this) {
                        @Override
                        protected void _onNext(GetHomePageRes s) {
                            SharePref.getInstance().clear();
                            Toast.makeText(getContext(), "退出登录成功", Toast.LENGTH_SHORT).show();
                            ll_Exit_logon.setVisibility(View.GONE);
                            refreshfragment.onexitClick( );
                            finish();
                        }
                        @Override
                        protected void _onError(String msg) {
                            Toast.makeText(getContext() , "退出登录失败", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

//    传递到mainactivity
    public interface Refreshfragment{
        void onexitClick( );
    }

    private Refreshfragment refreshfragment;

    public void setClickListener(Refreshfragment listener){
        this.refreshfragment = listener;
    }

    @Override
    protected void obtainData() {

    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onResume() {
        super.onResume();
    }


}
