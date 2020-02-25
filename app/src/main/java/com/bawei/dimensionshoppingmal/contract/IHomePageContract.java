package com.bawei.dimensionshoppingmal.contract;

import com.bawei.dimensionshoppingmal.baseActivity.IBaseView;

import java.util.HashMap;

/**
 * TIme:2020/2/21
 * Author:孙帅喜
 * Descriotion:
 */
public interface IHomePageContract {

    //在view的接口中我们需要定义获取成功和失败的方法
    interface IView extends IBaseView {
        //注册 登录
        void onGetLonginSuccess(String str);
        void onGetLonginFailure(String str);

    }
    //在presenter中,我们需要定义获取数据的方法就可以了
    interface IPresenter{
        //注册 登陆
        void getLogin(String path, HashMap<String,String> params);
    }

    //在model中,我们需要定义获取数据的方法,并再次创建一个接口回调,用来回调网络请求的数据给p层
    interface IModel{
        //注册  登陆
        void getLogin(String path,HashMap<String,String> params,MyCallBack myCallBack);
        interface MyCallBack{
            //注册 登陆
            void onGetLoginSuccess(String str);
            void onGetLoginFailure(String str);
        }

    }
}
