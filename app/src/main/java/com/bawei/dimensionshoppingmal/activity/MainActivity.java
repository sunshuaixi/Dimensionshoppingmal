package com.bawei.dimensionshoppingmal.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dimensionshoppingmal.R;
import com.bawei.dimensionshoppingmal.baseActivity.BaseActivity;
import com.bawei.dimensionshoppingmal.baseActivity.BasePresenter;
import com.bawei.dimensionshoppingmal.bean.BeanClass;
import com.bawei.dimensionshoppingmal.contract.IHomePageContract;
import com.bawei.dimensionshoppingmal.model.HomePageModel;
import com.bawei.dimensionshoppingmal.presenter.HomePagePresenter;
import com.bawei.dimensionshoppingmal.utils.Myutils;
import com.google.gson.Gson;

import java.util.HashMap;

public class MainActivity  extends BaseActivity implements  IHomePageContract.IView{

    private EditText et1;
    private Button bt2;
    private EditText et2;
    String path = "http://mobile.bwstudent.com/small/user/v1/register";

    private HomePagePresenter homePagePresenter;
    private Button bt1;


    @Override
    public BasePresenter initPresenter() {
        return new HomePagePresenter(this);
    }

    @Override
    protected int getlayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        bt1 = findViewById(R.id.bt1);
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        bt2 = findViewById(R.id.bt2);

    }

    @Override
    protected void getData() {
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = et1.getText().toString();
                String pwd = et2.getText().toString();
                HashMap<String,String> map=new HashMap<>();
                map.put("phone",phone);
                map.put("pwd",pwd);

                BasePresenter presenter = getPresenter();
                if(presenter!=null&&presenter instanceof HomePagePresenter){
                    ((HomePagePresenter)presenter).getLogin(path,map);
                }
            }
        });

        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

    }

    //注册
    @Override
    public void onGetLonginSuccess(final String str) {
        Gson gson = new Gson();
        BeanClass beanClass = gson.fromJson(str, BeanClass.class);
        Toast.makeText(MainActivity.this, ""+beanClass.getMessage(), Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onGetLonginFailure(String str) {

    }


}
