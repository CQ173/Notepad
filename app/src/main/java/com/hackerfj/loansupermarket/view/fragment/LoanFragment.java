package com.hackerfj.loansupermarket.view.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.StringUtils;
import com.hackerfj.loansupermarket.R;
import com.hackerfj.loansupermarket.app.constant.Global;
import com.hackerfj.loansupermarket.model.entity.res.GetHomePageRes;
import com.hackerfj.loansupermarket.model.entity.res.GetHomebannerRes;
import com.hackerfj.loansupermarket.model.entity.res.SangeRes;
import com.hackerfj.loansupermarket.model.entity.res.StingsRes;
import com.hackerfj.loansupermarket.model.entity.res.WholeInfoRes;
import com.hackerfj.loansupermarket.util.StartActivityUtil;
import com.hackerfj.loansupermarket.util.network.Api;
import com.hackerfj.loansupermarket.util.network.RxHelper;
import com.hackerfj.loansupermarket.util.network.RxSubscriber;
import com.hackerfj.loansupermarket.view.activity.LoginActivity;
import com.hackerfj.loansupermarket.view.activity.StingsActivity;
import com.hackerfj.loansupermarket.view.adapter.LoanHomepageAdapter;
import com.hackerfj.loansupermarket.view.fragment.base.BaseFragment;
import com.hackerfj.loansupermarket.view.widget.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;



public class LoanFragment extends BaseFragment {

    @BindView(R.id.rv_red_bag)
    RecyclerView rvLoanList;
    @BindView(R.id.tv_three)
    TextView tv_three;
    @BindView(R.id.tv_one)
    TextView tv_one;
    @BindView(R.id.tv_tow)
    TextView tv_tow;
    @BindView(R.id.tv_nologin)
    TextView tv_nologin;
    private LoanHomepageAdapter loanHomepageAdapter;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_load;
    }

    @Override
    protected void initView() {
        onResume();
        if (StringUtils.isEmpty(Global.getToken())){
            rvLoanList.setVisibility(View.GONE);
            tv_nologin.setVisibility(View.VISIBLE);
        }else {
            tv_nologin.setVisibility(View.GONE);
            getHomeaa();
        }
    }


    @OnClick({ R.id.ll_ed , R.id.iv_jiyb})
    public void onclick(View view){
        switch (view.getId()){
            case R.id.ll_ed:
                if (StringUtils.isEmpty(Global.getToken())){
                    StartActivityUtil.start(mActivity , LoginActivity.class);
                }else {
                    setpopup();
                }
                break;
            case R.id.iv_jiyb:
                if (StringUtils.isEmpty(Global.getToken())){
                    StartActivityUtil.start(mActivity , LoginActivity.class);
                }else {
                    StartActivityUtil.start(mActivity , StingsActivity.class);
                }
                break;
        }
    }

    public void setpopup(){
        View parent = ((ViewGroup)mActivity.findViewById(android.R.id.content)).getChildAt(0);
        View popView = View.inflate(getContext(), R.layout.popup_edtext, null);
        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        final PopupWindow popWindow = new PopupWindow(popView,width,height);
        popWindow.setFocusable(true);
        popWindow.setOutsideTouchable(true);// 设置同意在外点击消失
        TextView tv_Determine = popView.findViewById(R.id.tv_Determine);
        tv_Determine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWindow.dismiss();
            }
        });
        EditText et_addmoeny = popView.findViewById(R.id.et_addmoeny);
        TextView tv_Sure = popView.findViewById(R.id.tv_Sure);
        tv_Sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_addmoeny.getText().toString()!=null) {
                    Api.getDefault().addmoney(Global.getId() , et_addmoeny.getText().toString())
                            .compose(RxHelper.handleResult())
                            .subscribe(new RxSubscriber< StingsRes >(mActivity) {
                                @Override
                                protected void _onNext( StingsRes  s) {
                                    Toast.makeText(mActivity, "添加成功", Toast.LENGTH_SHORT).show();
                                    popWindow.dismiss();
                                    onResume();
                                }

                                @Override
                                protected void _onError(String msg) {
                                    Toast.makeText(mActivity, "添加失败", Toast.LENGTH_SHORT).show();
                                }
                            });
                }else {
                    Toast.makeText(mActivity, "请填写金额", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ColorDrawable dw = new ColorDrawable(0x30000000);
        popWindow.setBackgroundDrawable(dw);
        popWindow.showAtLocation(parent , Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
    }



    /**
     * 获取首页数据
     */
    private void getHomeaa(){
        Api.getDefault().getHomeInfo(Global.getId() , 0)
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<GetHomebannerRes>>(mActivity) {
                    @Override
                    protected void _onNext(List<GetHomebannerRes> list) {
                        loanHomepageAdapter = new LoanHomepageAdapter(getContext(), list);
                        rvLoanList.setLayoutManager(new LinearLayoutManager(getContext()));
                        rvLoanList.setAdapter(loanHomepageAdapter);
                        rvLoanList.setNestedScrollingEnabled(false);
                        loanHomepageAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void _onError(String msg) {

                    }
                });
    }

    @Override
    protected void obtainData() {
        if (Global.getToken() != null) {
            allmoneyha();
        }else {
            tv_tow.setText("0");
            tv_three.setText("0");
            tv_one.setText("0");
        }
    }

    public void allmoneyha(){
        Api.getDefault().allmoney(Global.getId())
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<SangeRes>(mActivity) {
                    @Override
                    protected void _onNext(SangeRes s) {
                        tv_tow.setText(s.getIncome1());
                        tv_three.setText(s.getIncome2());
                        tv_one.setText(s.getIncome3());
                    }

                    @Override
                    protected void _onError(String msg) {

                    }
                });
    }

    @Override
    protected void initEvent() {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (StringUtils.isEmpty(Global.getToken())){
            rvLoanList.setVisibility(View.GONE);
            tv_nologin.setVisibility(View.VISIBLE);
        }else {
            tv_nologin.setVisibility(View.GONE);
            getHomeaa();
        }
        if (Global.getToken() != null) {
            allmoneyha();
        }else {
            tv_tow.setText("0");
            tv_three.setText("0");
            tv_one.setText("0");
        }
    }
}
