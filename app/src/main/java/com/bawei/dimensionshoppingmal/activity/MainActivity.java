package com.bawei.dimensionshoppingmal.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bawei.dimensionshoppingmal.R;
import com.bawei.dimensionshoppingmal.baseActivity.BaseActivity;
import com.bawei.dimensionshoppingmal.bean.BeanClass;
import com.bawei.dimensionshoppingmal.utils.Myutils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity {


    private EditText et2;
    private EditText et1;
    private Button bt;

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
        final String path="http://172.17.8.100/small/user/v1/register";
        //点击事件
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = et1.getText().toString();
                String pwd = et2.getText().toString();
                //存入map集合
                Map<String,String> map=new HashMap<>();
                map.put("phone",phone);
                map.put("pwd",pwd);
                Myutils.getInstance().getReg(path, map, new Myutils.Ijk() {
                    @Override
                    public void onZhen(String json) {
                        Log.i("xxx",json);
                        Gson gson = new Gson();
                        BeanClass beanClass = gson.fromJson(json, BeanClass.class);
                        String message = beanClass.getMessage();
                        Toast.makeText(MainActivity.this, ""+message, Toast.LENGTH_SHORT).show();
                        Log.i("xxx",message);
                    }

                    @Override
                    public void onJia(String msg) {
                    }
                });
            }
        });
    }
}
