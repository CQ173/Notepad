package com.hackerfj.loansupermarket.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.utils.StringUtils;
import com.hackerfj.loansupermarket.R;
import com.hackerfj.loansupermarket.app.constant.Global;
import com.hackerfj.loansupermarket.model.entity.res.GetHomebannerRes;
import com.hackerfj.loansupermarket.model.entity.res.TypeofloanRes;
import com.hackerfj.loansupermarket.model.entity.res.WholeInfoRes;
import com.hackerfj.loansupermarket.util.StartActivityUtil;
import com.hackerfj.loansupermarket.util.network.Api;
import com.hackerfj.loansupermarket.util.network.RxHelper;
import com.hackerfj.loansupermarket.util.network.RxSubscriber;
import com.hackerfj.loansupermarket.view.activity.LoginActivity;
import com.hackerfj.loansupermarket.view.adapter.HomeFragAdapter;
import com.hackerfj.loansupermarket.view.adapter.LoanHomepageAdapter;
import com.hackerfj.loansupermarket.view.adapter.SpinerAAdapter;
import com.hackerfj.loansupermarket.view.adapter.SpinerAdapter;
import com.hackerfj.loansupermarket.view.fragment.base.BaseFragment;
import com.hackerfj.loansupermarket.view.widget.SpinerPopAWindow;
import com.hackerfj.loansupermarket.view.widget.SpinerPopWindow;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeFragment extends BaseFragment{

    @BindView(R.id.rv_loan_list)
    RecyclerView rvLoanList;

    private LoanHomepageAdapter loanHomepageAdapter;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView() {
        onResume();
        getHomeaa();
    }
    private void getHomeaa(){
        Api.getDefault().getHomeInfo()
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

    }

    @Override
    protected void initEvent() {

    }


    @Override
    public void onResume() {
        super.onResume();
        getHomeaa();
    }
}
