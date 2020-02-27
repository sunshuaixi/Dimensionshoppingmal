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
 *  * 1.创建 RecyclerView 的 Adapter时，必须写viewHolder
 *  * 2.创建内部类 ViewHolder r继承 RecyclerView.ViewHolde ！！！！！
 *  * 3.根据提示，生成 ViewHolder 的构造方法
 *  *
 *  * 4.这个类，继承 RecyclerView.Adapter<RecyclerView.ViewHolder>
 *  * 5.根据提示，自动生成需要重写的方法
 */
public class RxxpAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ListBean.ResultBean.RxxpBean.CommodityListBean> commodityList;

    public RxxpAdapter(Context context, List<ListBean.ResultBean.RxxpBean.CommodityListBean> commodityList) {
        this.context = context;
        this.commodityList = commodityList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.item1, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListBean.ResultBean.RxxpBean.CommodityListBean commodityListBean = commodityList.get(position);
        String commodityName = commodityListBean.getCommodityName();
        String masterPic = commodityListBean.getMasterPic();
        int price = commodityListBean.getPrice();
        ((ViewHolder)holder).tv1.setText("￥"+price+".00");
        ((ViewHolder)holder).tv.setText(commodityName);
        Glide.with(context).load(masterPic).into(((ViewHolder)holder).iv);

    }

    @Override
    public int getItemCount() {
        return commodityList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv;
        private final TextView tv1;
        private final ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tv = itemView.findViewById(R.id.tv);
            tv1 = itemView.findViewById(R.id.tv1);

        }
    }
}
