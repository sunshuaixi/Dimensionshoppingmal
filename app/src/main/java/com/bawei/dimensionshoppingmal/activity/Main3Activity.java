package com.bawei.dimensionshoppingmal.activity;



import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bawei.dimensionshoppingmal.R;
import com.bawei.dimensionshoppingmal.adapter.ListAdapter;
import com.bawei.dimensionshoppingmal.baseActivity.BaseActivity;
import com.bawei.dimensionshoppingmal.baseActivity.BasePresenter;
import com.bawei.dimensionshoppingmal.bean.ListBean;
import com.bawei.dimensionshoppingmal.bean.XBannerBean;
import com.bawei.dimensionshoppingmal.contract.IXbannerContract;
import com.bawei.dimensionshoppingmal.presenter.HomePagePresenter;
import com.bawei.dimensionshoppingmal.presenter.XbannerPresenter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends BaseActivity implements IXbannerContract.IView {


    private XBanner xb;
    private ListView lv;
    String banner="http://mobile.bwstudent.com/small/commodity/v1/bannerShow";
    String list="http://mobile.bwstudent.com/small/commodity/v1/commodityList";



    @Override
    public BasePresenter getPresenter() {
        return new XbannerPresenter(this);
    }

    @Override
    protected int getlayoutID() {
        return R.layout.activity_main3;
    }

    @Override
    protected void initView() {
        xb = findViewById(R.id.xb);
        lv = findViewById(R.id.lv);
    }

    @Override
    protected void getData() {
        BasePresenter presenter = getPresenter();
        if(presenter!=null&&presenter instanceof XbannerPresenter){
            ((XbannerPresenter)presenter).getXBnner(banner);
            ((XbannerPresenter)presenter).getList(list);
        }
    }

    @Override
    public void onXBannerSuccess(String str) {
        Gson gson = new Gson();
        XBannerBean xBannerBean = gson.fromJson(str, XBannerBean.class);
        final List<XBannerBean.ResultBean> result = xBannerBean.getResult();

        //设置数据源
        xb.setBannerData(result);
        //加载图片
        xb.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                XBannerBean.ResultBean resultBean = result.get(position);
                String imageUrl = resultBean.getImageUrl();
                Glide.with(Main3Activity.this).load(imageUrl).into((ImageView)view);
            }
        });
    }

    @Override
    public void onXBannerFailure(String str) {
        Log.i("xxx",str);
    }

    @Override
    public void onListSuccess(String str) {
        Log.i("xxx",str);
        Gson gson = new Gson();
        ListBean listBean = gson.fromJson(str, ListBean.class);
        ListBean.ResultBean result = listBean.getResult();
        ListBean.ResultBean.MlssBean mlss1 = result.getMlss();
        List<ListBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList = mlss1.getCommodityList();
        ListAdapter listAdapter = new ListAdapter(Main3Activity.this, commodityList);
        lv.setAdapter(listAdapter);
    }

    @Override
    public void onListFailure(String str) {
    Log.i("xxx",str);
    }
}
