package com.bawei.dimensionshoppingmal.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.dimensionshoppingmal.R;
import com.bawei.dimensionshoppingmal.bean.ListBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * TIme:2020/2/26
 * Author:孙帅喜
 * Descriotion:
 */
public class List2Adapter extends BaseAdapter {
    Context context;
    List<ListBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList;


    public List2Adapter(Context context, List<ListBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList) {
        this.context = context;
        this.commodityList = commodityList;
    }

    @Override
    public int getCount() {
        return commodityList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();
        if(convertView==null){
            convertView=View.inflate(context, R.layout.item2,null);
            holder.iv=convertView.findViewById(R.id.iv);
            holder.tv=convertView.findViewById(R.id.tv);
            holder.tv1=convertView.findViewById(R.id.tv1);
            convertView.setTag(holder);
        }else{
         holder=(ViewHolder) convertView.getTag();
        }
        ListBean.ResultBean.MlssBean.CommodityListBeanXX commodityListBean = commodityList.get(position);
        String commodityName = commodityListBean.getCommodityName();
        String masterPic = commodityListBean.getMasterPic();
        int price = commodityListBean.getPrice();
        holder.tv.setText(commodityName);
        holder.tv1.setText("￥"+price+".00");
        Glide.with(context).load(masterPic).into(holder.iv);
        return convertView;
    }

    private class ViewHolder{
        private ImageView iv;
        private TextView tv;
        private TextView tv1;
    }
}
