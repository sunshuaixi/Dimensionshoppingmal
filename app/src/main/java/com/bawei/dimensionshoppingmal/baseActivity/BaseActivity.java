package com.bawei.dimensionshoppingmal.baseActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.dimensionshoppingmal.R;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getlayoutID());

        initView();
        getData();
    }

    protected abstract int getlayoutID();

    protected abstract void initView();

    protected abstract void getData();
}
