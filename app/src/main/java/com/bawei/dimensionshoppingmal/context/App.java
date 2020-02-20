package com.bawei.dimensionshoppingmal.context;

import android.app.Application;
import android.content.Context;

/**
 * TIme:2020/2/20
 * Author:孙帅喜
 * Descriotion:
 */
public class App extends Application {
    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context=this;
    }
}
