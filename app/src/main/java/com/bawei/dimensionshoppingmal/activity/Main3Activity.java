package com.bawei.dimensionshoppingmal.activity;



import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dimensionshoppingmal.R;
import com.bawei.dimensionshoppingmal.adapter.List2Adapter;
import com.bawei.dimensionshoppingmal.adapter.List3Adapter;
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
    private GridView gv1;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private ListView lv2;
    private GridView gv3;
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
        gv1 = findViewById(R.id.gv1);
        lv2 = findViewById(R.id.lv2);
        gv3 = findViewById(R.id.gv3);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
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
        Gson gson = new Gson();
        ListBean listBean = gson.fromJson(str, ListBean.class);
        ListBean.ResultBean result = listBean.getResult();
        ListBean.ResultBean.RxxpBean rxxp = result.getRxxp();
        ListBean.ResultBean.MlssBean mlss = result.getMlss();
        ListBean.ResultBean.PzshBean pzsh = result.getPzsh();
        String name = rxxp.getName();
        tv1.setText(name);
        String name1 = mlss.getName();
        tv2.setText(name1);
        String name2 = pzsh.getName();
        tv3.setText(name2);
        List<ListBean.ResultBean.RxxpBean.CommodityListBean> commodityList = rxxp.getCommodityList();
        ListAdapter list1Adapter = new ListAdapter(Main3Activity.this, commodityList);
        gv1.setAdapter(list1Adapter);

        List<ListBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList1 = mlss.getCommodityList();
        List2Adapter list2Adapter = new List2Adapter(Main3Activity.this, commodityList1);
        lv2.setAdapter(list2Adapter);

        List<ListBean.ResultBean.PzshBean.CommodityListBeanX> commodityList2 = pzsh.getCommodityList();
        List3Adapter list3Adapter = new List3Adapter(Main3Activity.this,commodityList2);
        gv3.setAdapter(list3Adapter);

    }

    @Override
    public void onListFailure(String str) {
    Log.i("xxx",str);
    }
}
