package com.bawei.dimensionshoppingmal.activity;



import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

/**
 * * RecyclerView:
 *  * 1.在 buildGradle 中，添加依赖和配合使用的代码
 *  * 2.在布局文件中创建 RecyclerView 的控件
 *  * 3.通过 findViewById 获取控件的实例
 *  * 4.在获取到数据的地方，开始 new 布局管理器
 *  * 5.如果想使用线性布局，new LinearLayoutManager（）
 *  *   第一个参数：上下文
 *  *   第二个参数：线性布局是横向还是纵向展示，RecyclerView.HORIZONTAL 代表横向 RecyclerView.VERTICAL 代表纵向
 *  *   第三个参数：true 代表从尾部开始展示，false 代表从头开始展示
 *  *
 *  * 5.如果想使用网格布局，new GridLayoutManager（）
 *  *   第一个参数：上下文
 *  *   第二个参数：展示的数量，如果第三个参数横向展示，则是展示几行 如果纵向展示，展示激烈
 *  *   第三个参数：线性布局是横向还是纵向展示，RecyclerView.HORIZONTAL 代表横向 RecyclerView.VERTICAL 代表纵向
 *  *   第四个参数：true 代表从尾部开始展示，false 代表从头开始展示
 *  *
 *  * 5.如果想使用瀑布流布局，new StaggeredGridLayoutManager（）
 *  *   第一个参数：展示的数量，如果第三个参数横向展示，则是展示几行 如果纵向展示，展示激烈
 *  *   第二个参数：线性布局是横向还是纵向展示，RecyclerView.HORIZONTAL 代表横向 RecyclerView.VERTICAL 代表纵向
 *  *
 *  * 6.把 布局管理器 添加到 RecyclerView 中 ！！！！！！！！！！！！
 *  * 7.new 一个 adapter 具体里面的操作，见 RxxpAdapter 类
 *  * 8.把 adapter 添加到 RecyclerView 中
 *  */

public class Main3Activity extends BaseActivity implements IXbannerContract.IView {


    private XBanner xb;
    private RecyclerView rl1;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private RecyclerView rl2;
    private RecyclerView rl3;
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
        rl1 = findViewById(R.id.rl1);
        rl2 = findViewById(R.id.rl2);
        rl3 = findViewById(R.id.rl3);
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
//        ListAdapter list1Adapter = new ListAdapter(Main3Activity.this, commodityList);
//        rl1.setAdapter(list1Adapter);

        List<ListBean.ResultBean.MlssBean.CommodityListBeanXX> commodityList1 = mlss.getCommodityList();
//        List2Adapter list2Adapter = new List2Adapter(Main3Activity.this, commodityList1);
//        lv2.setAdapter(list2Adapter);
//
        List<ListBean.ResultBean.PzshBean.CommodityListBeanX> commodityList2 = pzsh.getCommodityList();
//        List3Adapter list3Adapter = new List3Adapter(Main3Activity.this,commodityList2);
//        gv3.setAdapter(list3Adapter);

        //使用网格布局 new GridLayoutManager();
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(this,3);
        rl1.setLayoutManager(layoutManager);

        RxxpAdapter rxxpAdapter = new RxxpAdapter(this, commodityList);
        rl1.setAdapter(rxxpAdapter);

        // newLinearLayoutManager
        RecyclerView.LayoutManager layoutManager1=  new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        rl2.setLayoutManager(layoutManager1);
        MllsAdapter mllsAdapter = new MllsAdapter(this, commodityList1);
        rl2.setAdapter(mllsAdapter);

        //
        RecyclerView.LayoutManager layoutManager2=new GridLayoutManager(this,2);
        rl3.setLayoutManager(layoutManager2);
        PzshAdapter pzshAdapter = new PzshAdapter(this, commodityList2);
        rl3.setAdapter(pzshAdapter);

    }

    @Override
    public void onListFailure(String str) {
    Log.i("xxx",str);
    }
}
