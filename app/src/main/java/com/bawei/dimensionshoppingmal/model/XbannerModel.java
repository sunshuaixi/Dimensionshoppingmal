package com.bawei.dimensionshoppingmal.model;

import android.util.Log;

import com.bawei.dimensionshoppingmal.contract.IXbannerContract;
import com.bawei.dimensionshoppingmal.utils.Myutils;
import com.bawei.dimensionshoppingmal.utils.VolleyUtils;

/**
 * TIme:2020/2/22
 * Author:孙帅喜
 * Descriotion:
 */
public class XbannerModel implements IXbannerContract.IModel {

    @Override
    public void getXBanner(String path, final MyCallback myCallback) {
        Myutils.getInstance().getJson(path, new Myutils.Ijk() {
            @Override
            public void onSuccess(String json) {
                    Log.i("xxx",json);
               myCallback.onGetXBnnerSuccess(json);
            }

            @Override
            public void onError(String msg) {
               myCallback.onGetXBnnerFailure(msg);
            }
        });
    }

    @Override
    public void getlist(String path, final Callback Callback) {
       Myutils.getInstance().getJson(path, new Myutils.Ijk() {
           @Override
           public void onSuccess(String json) {
               Callback.onGetListSuccess(json);
           }

           @Override
           public void onError(String msg) {
                Callback.onGetListFailure(msg);
           }
       });
    }
}
