package com.bawei.dimensionshoppingmal.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.dimensionshoppingmal.R;
import com.bawei.dimensionshoppingmal.baseActivity.BaseActivity;
import com.bawei.dimensionshoppingmal.baseActivity.BasePresenter;
import com.bawei.dimensionshoppingmal.bean.BeanClass;
import com.bawei.dimensionshoppingmal.contract.IHomePageContract;
import com.bawei.dimensionshoppingmal.presenter.HomePagePresenter;
import com.google.gson.Gson;

import java.util.HashMap;

public class Main2Activity extends BaseActivity implements IHomePageContract.IView {


    private EditText et1;
    private EditText et2;
    private Button bt;
    private TextView tv;
    private String phone;
    private String pwd;
    private SharedPreferences sp;
    private CheckBox cb;
    @Override
    public BasePresenter initPresenter() {
        return new HomePagePresenter(this);
    }

    @Override
    protected int getlayoutID() {
        return R.layout.activity_main2;
    }

    @Override
    protected void initView() {
        et1 = findViewById(R.id.et1);
        et2 = findViewById(R.id.et2);
        bt = findViewById(R.id.bt);
        tv = findViewById(R.id.tv);
        cb = findViewById(R.id.cb);
    }

    @Override
    protected void getData() {
        final String path="http://mobile.bwstudent.com/small/user/v1/login";
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean bo = sp.getBoolean("bo", false);
//               if(bo==true){
//                   if(bo){
//                       String phone = sp.getString("phone", "");
//                       String pwd = sp.getString("pwd", "");
//                       et1.setText(phone);
//                       et2.setText(pwd);
//                   }
//               }else{
                   phone = et1.getText().toString();
                   pwd = et2.getText().toString();
                   HashMap<String,String> map=new HashMap<>();
                   map.put("phone", phone);
                   map.put("pwd", pwd);
                   BasePresenter presenter = getPresenter();
                   if(presenter!=null&&presenter instanceof HomePagePresenter){
                       ((HomePagePresenter)presenter).getLogin(path,map);
                   }
               }


    //        }
        });
    }
    //登陆
    @Override
    public void onGetLonginSuccess(String str) {
        Log.i("xxx",str);
        Gson gson = new Gson();
        BeanClass beanClass = gson.fromJson(str, BeanClass.class);
        String message = beanClass.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        Log.i("xxx",message);
        if(message.equals("登录成功")){
            Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
            startActivity(intent);

//            //创建SharedPreferences
//            sp = getSharedPreferences("user", MODE_PRIVATE);
//            //获取编辑对象
//            SharedPreferences.Editor edit = sp.edit();
//            if(cb.isChecked()){
//                edit.putString("phone",phone);
//                edit.putString("pwd",pwd);
//                edit.putBoolean("bo",true);
//                edit.commit();
//            }else{
//                edit.putString("phone","");
//                edit.putString("pwd","");
//                edit.putBoolean("bo",false);
//                edit.commit();
//            }
        }




    }

    @Override
    public void onGetLonginFailure(String str) {

    }

}
