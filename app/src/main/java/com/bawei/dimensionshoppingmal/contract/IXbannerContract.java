package com.bawei.dimensionshoppingmal.contract;

/**
 * TIme:2020/2/22
 * Author:孙帅喜
 * Descriotion:
 */
public interface IXbannerContract {
    interface IView{
        void onXBannerSuccess(String str);
        void onXBannerFailure(String str);

        void onListSuccess(String str);
        void onListFailure(String str);
    }

    interface IModel{
        void getXBanner(String path, MyCallback myCallback );
        interface MyCallback{
            void onGetXBnnerSuccess(String str);
            void onGetXBnnerFailure(String str);
        }
        void getlist(String path, Callback Callback );
        interface Callback{
            void onGetListSuccess(String str);
            void onGetListFailure(String str);
        }
    }
    interface IPresenter{
        void getXBnner(String path);

        void getList(String path);
    }
}
