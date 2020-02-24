package com.bawei.dimensionshoppingmal.presenter;

import com.bawei.dimensionshoppingmal.activity.MainActivity;
import com.bawei.dimensionshoppingmal.baseActivity.BasePresenter;
import com.bawei.dimensionshoppingmal.baseActivity.IBaseView;
import com.bawei.dimensionshoppingmal.contract.IHomePageContract;
import com.bawei.dimensionshoppingmal.model.HomePageModel;

import java.util.HashMap;

/**
 * TIme:2020/2/21
 * Author:孙帅喜
 * Descriotion:
 */
public class HomePagePresenter extends BasePresenter implements IHomePageContract.IPresenter {
    //这里我们需要把传入的v层保存成成员变量
    HomePageModel model;

    public HomePagePresenter(IBaseView iBaseView) {
        super(iBaseView);
    }
    @Override
    public void getLogin(String path, HashMap<String, String> params) {
        //将v层发起的请求传递到m层,创建一个接口回调IHomePageContract.IModel.IModelCallback()用来接收m层返回的数据
            model.getLogin(path, params, new IHomePageContract.IModel.MyCallBack() {
                @Override
                public void onGetLoginSuccess(String str) {
                    //如果m层返回成功,则将数据通过接口回返回给V层
                    IBaseView view = getView();
                    if(view instanceof IHomePageContract.IView){
                           IHomePageContract.IView iView= (IHomePageContract.IView) view;
                           iView.onGetLonginSuccess(str);
                    }
                }

                @Override
                public void onGetLoginFailure(String str) {
                    //如果M层返回失败,则将数据通过接口回调返回给V层
                    IBaseView view = getView();
                    if(view instanceof IHomePageContract.IView){
                        IHomePageContract.IView iView= (IHomePageContract.IView) view;
                        iView.onGetLonginFailure(str);
                    }

                }
            });
    }

    @Override
    protected void initModel() {
         model = new HomePageModel();
    }
}
