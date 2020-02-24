package com.bawei.dimensionshoppingmal.baseActivity;


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
    //使用弱引用把我们传入的V层包裹起来.提成成员变量
    //WeakReference是系统提供的类,<>中包裹的是我们需要用弱引用的具体的哪一个类型
    //我们这里面是用弱引用包裹了泛型
    private WeakReference<V> vWeakReference;

    public BasePresenter(V v) {
        //在构造方法中new一个弱引用
        //在new这个弱引用的时候.我们只需要把符合我们创建时候的包裹类型的值传入即可
         vWeakReference = new WeakReference<>(v);
         initModel();
    }
    protected abstract void initModel();

    public V getView(){
        //先判断空
        if(vWeakReference!=null){
         //通过get方法获取弱引用中的V层
         return vWeakReference.get();
        }
        return null;
    }

    //添加一个解绑的方法,当V层被销毁得到时候,调用这个方法
    protected void detachView(){
        if(vWeakReference!=null){
            //清空弱引用中所有内容
            vWeakReference.clear();
            //把弱引用置空
            vWeakReference=null;
        }
    }
}
