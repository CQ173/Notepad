package com.hackerfj.loansupermarket.view.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.utils.StringUtils;
import com.hackerfj.loansupermarket.R;
import com.hackerfj.loansupermarket.app.constant.Global;
import com.hackerfj.loansupermarket.model.entity.res.GetHomebannerRes;
import com.hackerfj.loansupermarket.util.ToastUtil;
import com.hackerfj.loansupermarket.util.network.Api;
import com.hackerfj.loansupermarket.util.network.RxHelper;
import com.hackerfj.loansupermarket.util.network.RxSubscriber;
import com.hackerfj.loansupermarket.view.adapter.HomeFragAdapter;
import com.hackerfj.loansupermarket.view.adapter.LoanHomepageAdapter;
import com.hackerfj.loansupermarket.view.fragment.base.BaseFragment;

import java.util.List;

import butterknife.BindView;

public class OneFragment extends BaseFragment {
    @BindView(R.id.rv_id)
    RecyclerView rvLoanList;
    private HomeFragAdapter homeFragAdapter;

    @Override
    protected int setContentLayout() {
        return R.layout.fragment_one;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void obtainData() {
        //设置RecycleView显示的布局
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        rvLoanList.setLayoutManager(manager);
        Api.getDefault().getHomeInfo(Global.getId(), 1)
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
