package com.hackerfj.loansupermarket.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hackerfj.loansupermarket.R;
import com.hackerfj.loansupermarket.model.entity.res.GetHomebannerRes;
import com.hackerfj.loansupermarket.model.entity.res.WholeInfoRes;
import com.hackerfj.loansupermarket.util.FrescoUtil;
import com.hackerfj.loansupermarket.util.network.UrlUtil;
import com.hackerfj.loansupermarket.view.adapter.base.RecyclerBaseAdapter;
import com.hackerfj.loansupermarket.view.adapter.base.ViewHolder;

import java.util.List;

public class LoanHomepageAdapter extends RecyclerBaseAdapter<GetHomebannerRes> {



    public LoanHomepageAdapter(@NonNull Context context, @NonNull List<GetHomebannerRes> getHomebannerRes ) {
        super(context, getHomebannerRes);
    }

    @Override
    protected void bindDataForView(ViewHolder holder, GetHomebannerRes getHomebannerRes, int position) {
        TextView tv_Title = holder.getView(R.id.tv_Title);
        ImageView image_log = holder.getView(R.id.image_log);
        if ("1".equals(getHomebannerRes.getAclass())){
            tv_Title.setText("餐饮");
            image_log.setImageResource(R.drawable.shell9_2_restaurant);
        }else if ("2".equals(getHomebannerRes.getAclass())){
            tv_Title.setText("旅游");
            image_log.setImageResource(R.drawable.shell9_2_tourism);
        }else if ("3".equals(getHomebannerRes.getAclass())){
            tv_Title.setText("话费");
            image_log.setImageResource(R.drawable.shell9_2_telephone);
        }else if ("4".equals(getHomebannerRes.getAclass())){
            tv_Title.setText("娱乐");
            image_log.setImageResource(R.drawable.shell9_2_entertainment);
        }else if ("5".equals(getHomebannerRes.getAclass())){
            tv_Title.setText("住房");
            image_log.setImageResource(R.drawable.shell_housing);
        }else if ("6".equals(getHomebannerRes.getAclass())){
            tv_Title.setText("交通");
            image_log.setImageResource(R.drawable.shell9_2_traffic);
        }else if ("7".equals(getHomebannerRes.getAclass())){
            tv_Title.setText("运动");
            image_log.setImageResource(R.drawable.shell9_2_motion);
        }else if ("8".equals(getHomebannerRes.getAclass())){
            tv_Title.setText("维修");
            image_log.setImageResource(R.drawable.shell9_2_repair);
        }else if ("9".equals(getHomebannerRes.getAclass())){
            tv_Title.setText("工资");
            image_log.setImageResource(R.drawable.shell9_2_wages);
        }else if ("10".equals(getHomebannerRes.getAclass())){
            tv_Title.setText("兼职");
            image_log.setImageResource(R.drawable.shell9_2_investment);
        }else if ("11".equals(getHomebannerRes.getAclass())){
            tv_Title.setText("奖金");
            image_log.setImageResource(R.drawable.shell9_2_bonus);
        }else if ("12".equals(getHomebannerRes.getAclass())){
            tv_Title.setText("红包");
            image_log.setImageResource(R.drawable.shell9_2_redenvelopes);
        }

        TextView tv_rate = holder.getView(R.id.tv_rate);
        if ("1".equals(getHomebannerRes.getType())){
            tv_rate.setText("-" + getHomebannerRes.getMoney());
            tv_rate.setTextColor(Color.parseColor("#fa723c"));
        }else {
            tv_rate.setText("+" + getHomebannerRes.getMoney());
            tv_rate.setTextColor(Color.parseColor("#dbb76c"));
        }



    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(getContext(), R.layout.item_classification, null);
        return new ViewHolder(view);
    }
}
