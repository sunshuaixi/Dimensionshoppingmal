package com.bawei.dimensionshoppingmal.presenter;

import com.bawei.dimensionshoppingmal.baseActivity.BasePresenter;
import com.bawei.dimensionshoppingmal.baseActivity.IBaseView;
import com.bawei.dimensionshoppingmal.contract.IXbannerContract;
import com.bawei.dimensionshoppingmal.model.HomePageModel;
import com.bawei.dimensionshoppingmal.model.XbannerModel;

/**
 * TIme:2020/2/22
 * Author:孙帅喜
 * Descriotion:
 */
public class XbannerPresenter extends BasePresenter implements IXbannerContract.IPresenter {

    private XbannerModel xbannerModel;

    public XbannerPresenter(IBaseView iBaseView) {
        super(iBaseView);
    }

    @Override
    public void getXBnner(String path) {
        xbannerModel.getXBanner(path, new IXbannerContract.IModel.MyCallback() {
            @Override
            public void onGetXBnnerSuccess(String str) {
                IBaseView view = getView();
                if(view instanceof  IXbannerContract.IView){
                    IXbannerContract.IView iView= (IXbannerContract.IView) view;
                    iView.onXBannerSuccess(str);
                }
            }

            @Override
            public void onGetXBnnerFailure(String str) {
                IBaseView view = getView();
                if(view instanceof IXbannerContract.IView){
                    IXbannerContract.IView iView= (IXbannerContract.IView) view;
                    iView.onXBannerFailure(str);
                }
            }
        });
    }

    @Override
    public void getList(String path) {
        xbannerModel.getlist(path, new IXbannerContract.IModel.Callback() {
            @Override
            public void onGetListSuccess(String str) {
                IBaseView view = getView();
                if(view instanceof IXbannerContract.IView){
                    IXbannerContract.IView iView= (IXbannerContract.IView) view;
                    iView.onListSuccess(str);
                }
            }

            @Override
            public void onGetListFailure(String str) {
                IBaseView view = getView();
                if(view instanceof IXbannerContract.IView){
                    IXbannerContract.IView iView= (IXbannerContract.IView) view;
                    iView.onListFailure(str);
                }
            }
        });
    }

    @Override
    protected void initModel() {
        xbannerModel = new XbannerModel();
    }
}
