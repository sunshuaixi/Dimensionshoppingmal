package com.bawei.dimensionshoppingmal.baseActivity;


import com.bawei.dimensionshoppingmal.R;

import java.lang.ref.WeakReference;

/**
 * TIme:2020/2/24
 * Author:孙帅喜
 * Descriotion:
 * 泛型，就是满足条件的所有类型我都接受
 *  * 因为是所有类型，所以没办法知名具体的类名
 *  * 因为没办法指明具体类名，所以，用一个字母来代替，下面使用的是V，我们也可以改成T、M都行
 *  * 但是我们不能用所有类型，所以我们需要给泛型添加一个条件限制，就是继承了IBaseView的类才可以
 *  *
 *  * 他是一个所有P层基类，统一管理我们的V层的传入，V层的绑定与解绑，把所有P层共用的方法，抽取到基类中
 *  * 达到复用的目的
 */
public abstract class BasePresenter<V extends IBaseView> {
    private WeakReference<V> vWeakReference;

    //使用弱引用把我们传入的V层包裹起来.提成成员变量
    //WeakReference是系统提供的类,<>中包裹的是我们需要用弱引用的具体的哪一个类型
    //我们这里面是用弱引用包裹了泛型
    public BasePresenter(V v) {
        vWeakReference = new WeakReference<>(v);
        initModel();
    }

    public abstract void initModel();

    public  V getView(){
       if(vWeakReference!=null){
            return vWeakReference.get();
       }
       return null;
    }

    public void detachView(){
        if(vWeakReference!=null){
            vWeakReference.clear();
            vWeakReference=null;
        }
    }
}
