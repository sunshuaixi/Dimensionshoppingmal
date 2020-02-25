package com.bawei.dimensionshoppingmal.baseActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.dimensionshoppingmal.R;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements IBaseView{

    P mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutID());

        mPresenter = initPresenter();

        initView();
        getData();
    }
    //提供一个外部调用,获取我们保存的P层的方法,以供外部使用
    public P getPresenter(){
        return mPresenter;
    }
    //在onDestroy生面周期中,我们需要完成P层与V层的解绑
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mPresenter!=null){
            mPresenter.detachView();
            mPresenter=null;
        }
    }
    public abstract P initPresenter();

    protected abstract int getlayoutID();

    protected abstract void initView();

    protected abstract void getData();
}
