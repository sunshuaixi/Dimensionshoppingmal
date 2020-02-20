package com.bawei.dimensionshoppingmal.baseActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bawei.dimensionshoppingmal.R;

/**
 * TIme:2020/2/20
 * Author:孙帅喜
 * Descriotion:
 */
public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutID());
        initView();
        getData();
        //2.20
    }

    protected abstract int getlayoutID();

    protected abstract void initView();

    protected abstract void getData();
}
