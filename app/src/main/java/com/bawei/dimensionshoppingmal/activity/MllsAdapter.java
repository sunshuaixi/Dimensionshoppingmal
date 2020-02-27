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
public class MllsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ListBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList1;

    public MllsAdapter(Context context, List<ListBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList1) {
        this.context = context;
        this.commodityList1 = commodityList1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item2, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListBean.ResultBean.MlssBean.CommodityListBeanXX commodityListBeanXX = commodityList1.get(position);
        String commodityName = commodityListBeanXX.getCommodityName();
        String masterPic = commodityListBeanXX.getMasterPic();
        int price = commodityListBeanXX.getPrice();
        ((ViewHolder)holder).tv.setText(commodityName);
        ((ViewHolder)holder).tv1.setText("￥"+price+".00");
        Glide.with(context).load(masterPic).into(((ViewHolder) holder).iv);
    }

    @Override
    public int getItemCount() {
        return commodityList1.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView tv;
        private final TextView tv1;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            tv1 = itemView.findViewById(R.id.tv1);
        }
    }
}
