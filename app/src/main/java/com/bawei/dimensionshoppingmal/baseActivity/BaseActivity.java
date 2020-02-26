package com.bawei.dimensionshoppingmal.baseActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.dimensionshoppingmal.R;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView{


    private P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutID());

        presenter = getPresenter();

        initView();
        getData();
    }
    //提供一个外部调用,获取我们保存的P层的方法,以供外部使用
    public abstract P getPresenter();
    //在onDestroy生面周期中,我们需要完成P层与V层的解绑


    @Override
    protected void onDestroy() {
        super.onDestroy();
       if(presenter!=null){
           presenter.detachView();
       }
    }

    protected abstract int getlayoutID();

    protected abstract void initView();

    protected abstract void getData();
}
