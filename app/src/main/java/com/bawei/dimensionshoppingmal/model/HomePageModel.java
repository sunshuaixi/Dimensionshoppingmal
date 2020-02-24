package com.bawei.dimensionshoppingmal.model;

import android.util.Log;
import android.widget.Toast;

import com.bawei.dimensionshoppingmal.bean.BeanClass;
import com.bawei.dimensionshoppingmal.contract.IHomePageContract;
import com.bawei.dimensionshoppingmal.utils.Myutils;
import com.google.gson.Gson;

import java.util.HashMap;

/**
 * TIme:2020/2/21
 * Author:孙帅喜
 * Descriotion:
 */
public class HomePageModel implements IHomePageContract.IModel {
    //注册  登陆
    @Override
    public void getLogin(String path, HashMap<String, String> params, final MyCallBack myCallBack) {
        //P层传递过来的请求直接调用接口.创建回调Myutils.Ijk(接受网络工具类接受的数据
        Myutils.getInstance().getReg(path, params, new Myutils.Ijk() {
            @Override
            public void onSuccess(String json) {
                //如果网络工具类返回成功,则将数据通过接口回调返回给P层
                myCallBack.onGetLoginSuccess(json);
            }

            @Override
            public void onError(String msg) {
                //如果网络工具类返回失败,则将数据通过接口回调返回给P层
                myCallBack.onGetLoginFailure(msg);
            }
        });


    }
}
