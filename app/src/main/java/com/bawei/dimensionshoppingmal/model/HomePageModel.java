package com.bawei.dimensionshoppingmal.model;

import com.bawei.dimensionshoppingmal.contract.IHomePageContract;
import com.bawei.dimensionshoppingmal.utils.Myutils;
import com.bawei.dimensionshoppingmal.utils.VolleyUtil;

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
        VolleyUtil.getInstance().doPost(path, params, new VolleyUtil.Callack() {
            @Override
            public void success(String json) {
                myCallBack.onGetLoginSuccess(json);
            }

            @Override
            public void falied(String msg) {
                myCallBack.onGetLoginFailure(msg);
            }
        });


    }
}
