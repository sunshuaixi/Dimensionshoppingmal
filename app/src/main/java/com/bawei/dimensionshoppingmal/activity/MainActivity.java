package com.bawei.dimensionshoppingmal.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.dimensionshoppingmal.R;
import com.bawei.dimensionshoppingmal.baseActivity.BaseActivity;
import com.bawei.dimensionshoppingmal.bean.BeanClass;
import com.bawei.dimensionshoppingmal.contract.IHomePageContract;
import com.bawei.dimensionshoppingmal.utils.Myutils;
import com.google.gson.Gson;

import java.util.HashMap;

public class MainActivity  extends BaseActivity implements  IHomePageContract.IView{

    private EditText et1;
    private Button bt;
    private EditText et2;
    String path = "http://mobile.bwstudent.com/small/user/v1/register";

    @Override
    protected int getlayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        bt = findViewById(R.id.bt);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
    }

    @Override
    protected void getData() {
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = et1.getText().toString();
                String pwd = et2.getText().toString();
                HashMap<String,String> map=new HashMap<>();
                map.put("phone",phone);
                map.put("pwd",pwd);
                Myutils.getInstance().getReg(path, map, new Myutils.Ijk() {
                    @Override
                    public void onSuccess(String json) {
                        Gson gson = new Gson();
                        BeanClass beanClass = gson.fromJson(json, BeanClass.class);
                        Toast.makeText(MainActivity.this, ""+beanClass.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String msg) {

                    }
                });
            }
        });
    }


    @Override
    public void onGetBannerSuccess(String str) {

    }

    @Override
    public void onGetBannerFailure(String str) {

    }
}
