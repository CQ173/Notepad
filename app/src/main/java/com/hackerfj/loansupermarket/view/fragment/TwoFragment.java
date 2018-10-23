package com.hackerfj.loansupermarket.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.utils.ToastUtils;
import com.hackerfj.loansupermarket.R;
import com.hackerfj.loansupermarket.app.constant.Global;
import com.hackerfj.loansupermarket.model.entity.res.GetHomebannerRes;
import com.hackerfj.loansupermarket.util.ToastUtil;
import com.hackerfj.loansupermarket.util.network.Api;
import com.hackerfj.loansupermarket.util.network.RxHelper;
import com.hackerfj.loansupermarket.util.network.RxSubscriber;
import com.hackerfj.loansupermarket.view.adapter.HomeFragAdapter;
import com.hackerfj.loansupermarket.view.fragment.base.BaseFragment;

import java.util.List;

import butterknife.BindView;

public class TwoFragment extends BaseFragment {
    @BindView(R.id.rv_view)
    RecyclerView rvLoanList;

    private HomeFragAdapter homeFragAdapter;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_two;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        //设置RecycleView显示的布局
        LinearLayoutManager manager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvLoanList.setLayoutManager(manager);
        Api.getDefault().getHomeInfo(Global.getId() , 2 )
                .compose(RxHelper.handleResult())
                .subscribe(new RxSubscriber<List<GetHomebannerRes>>(mActivity) {
                    @Override
                    protected void _onNext(List<GetHomebannerRes> list) {
                        homeFragAdapter = new HomeFragAdapter(getContext(), list);
                        rvLoanList.setLayoutManager(new LinearLayoutManager(getContext()));
                        rvLoanList.setAdapter(homeFragAdapter);
                        rvLoanList.setNestedScrollingEnabled(false);
                        homeFragAdapter.notifyDataSetChanged();

                    }

                    @Override
                    protected void _onError(String msg) {

                    }
                });
    }

    @Override
    protected void initEvent() {

    }
}
