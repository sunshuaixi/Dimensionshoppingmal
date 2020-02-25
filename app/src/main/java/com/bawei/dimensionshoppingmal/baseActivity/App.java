package com.bawei.dimensionshoppingmal.baseActivity;

import android.app.Application;
import android.content.Context;

/**
 * TIme:2020/2/25
 * Author:孙帅喜
 * Descriotion:
 * 4:创建一个app类,继承Application
 * 5:写一个静态成员变量,记录上下文
 * 6:在onCreate中通过getApplicationContext来给成员变量赋值
 * 7:编写一个静态方法,提供外部调用获取上下文
 * 8:在我们的manifest里面,application下,添加android:name="该累的路径"
 */
public class App extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
    public static Context getAppcontext(){
        return mContext;
    }
}
