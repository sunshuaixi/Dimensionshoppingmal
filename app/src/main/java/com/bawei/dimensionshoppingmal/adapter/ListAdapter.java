package com.bawei.dimensionshoppingmal.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.dimensionshoppingmal.R;
import com.bawei.dimensionshoppingmal.activity.Main3Activity;
import com.bawei.dimensionshoppingmal.bean.ListBean;
import com.bumptech.glide.Glide;

import org.w3c.dom.Text;

import java.util.List;

/**
 * TIme:2020/2/22
 * Author:孙帅喜
 * Descriotion:
 */
public class ListAdapter extends BaseAdapter {
    Context context;
    List<ListBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList;

    public ListAdapter(Context context, List<ListBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList) {
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
            convertView=View.inflate(context, R.layout.item1,null);
            holder.iv=convertView.findViewById(R.id.iv);
            holder.tv=convertView.findViewById(R.id.tv);

            convertView.setTag(holder);
        }else{
            holder=(ViewHolder) convertView.getTag();
        }

        ListBean.ResultBean.MlssBean.CommodityListBeanXX commodityListBeanXX = commodityList.get(position);
        String commodityName = commodityListBeanXX.getCommodityName();
        String masterPic = commodityListBeanXX.getMasterPic();
        holder.tv.setText(commodityName);
        Glide.with(context).load(masterPic).into(holder.iv);
        return convertView;
    }

    private class ViewHolder{
        private ImageView iv;
        private TextView tv;
    }
}
