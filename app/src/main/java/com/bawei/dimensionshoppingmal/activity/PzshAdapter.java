package com.bawei.dimensionshoppingmal.activity;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.dimensionshoppingmal.R;
import com.bawei.dimensionshoppingmal.bean.ListBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * TIme:2020/2/27
 * Author:孙帅喜
 * Descriotion:
 */
public class PzshAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ListBean.ResultBean.PzshBean.CommodityListBeanX> commodityList2;

    public PzshAdapter(Context context, List<ListBean.ResultBean.PzshBean.CommodityListBeanX> commodityList2) {
        this.context = context;
        this.commodityList2 = commodityList2;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item3, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListBean.ResultBean.PzshBean.CommodityListBeanX commodityListBeanX = commodityList2.get(position);
        String commodityName = commodityListBeanX.getCommodityName();
        String masterPic = commodityListBeanX.getMasterPic();
        int price = commodityListBeanX.getPrice();
        ((ViewHolder)holder).tv.setText(commodityName);
        ((ViewHolder)holder).tv1.setText("￥"+price+".00");
        Glide.with(context).load(masterPic).into(((ViewHolder)holder).iv);

    }

    @Override
    public int getItemCount() {
        return commodityList2.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv1;
        private final TextView tv;
        private final ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            tv1 = itemView.findViewById(R.id.tv1);
        }
    }
}
