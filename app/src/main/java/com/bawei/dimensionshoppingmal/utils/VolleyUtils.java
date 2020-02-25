package com.bawei.dimensionshoppingmal.utils;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.dimensionshoppingmal.baseActivity.App;

import java.util.HashMap;
import java.util.Map;

/**
 * TIme:2020/2/25
 * Author:孙帅喜
 * Descriotion:
 * 1:编写网络工具类
 * 2:编写单例模式,直立式静态内部类
 */
public class VolleyUtils {
    //RequestQueue 这个就是volley给我们提供的统一管理所有请求的一个队列
    //把所有的请求都方法哦队列中,然后他自己会帮我们执行
    private final RequestQueue requestQueue;
    //单例模式 必须 要有私有化  无参构造方法
    private VolleyUtils(){
        //3:在单例模式的构造方法中,初始化队列
        //初始化对列的方法Volley.
        //后面需要跟一个 全局  的上下文,避免内存泄漏
        //9:把app下的静态全局上下文当做参数传入
        requestQueue = Volley.newRequestQueue(App.getAppcontext());
    }
    //这个是静态内部类的单例模式,是一种推荐大家使用的单例模式
    private static class Singlelnstance{
       private static final VolleyUtils INSTANCE= new VolleyUtils();
    }
    public static VolleyUtils getInstance(){
        return Singlelnstance.INSTANCE;
    }
    //10:写get请求方法,入参和一起一样,路径+接口
    //11:我们通过new创建一个StringReques
    //12:我们这里new的时候,需要传入四个参数
    //13:第一个参数,我们的请求方式,这里是Request.method.GET,代表我们发起get请求
    //14:第二个参数,请求路径,对应传入的url
    //15:第三个参数,请求返回值得监听,这里我们需要自己new一个,在监听当中,把数据通过接口回调给M层
    //16:第四个参数:请求失败的监听,这里我们需要自己new一个,在监听当中,把失败数据通过接口回调给M层

    // 请求失败：网络失败（没有网，链接超时）、参数拼错、请求格式错误等等导致网络请求无法完成的错误会走第四个参数
    //     *     如果说我们的账号密码错误，或者请求成功了，服务器告诉我们失败，会走第三个参数
    //     */
    public void doGet(String url, final CallBack callBack){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callBack.success(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.falied(error.getMessage());
            }
        });
        //17:把请求添加到队列中
        requestQueue.add(stringRequest);
    }

    //18:写get请求方法,入参和一起一样,路径+hashmap+接口
    //19:我们通过new创建一个StringReques
    //20:我们这里new的时候,需要传入四个参数
    //21:第一个参数,我们的请求方式,这里是Request.method.POST,代表我们发起post请求
    //22:第二个参数,请求路径,对应传入的url
    //23:第三个参数,请求返回值得监听,这里我们需要自己new一个,在监听当中,把数据通过接口回调给M层
    //24:第四个参数:请求失败的监听,这里我们需要自己new一个,在监听当中,把失败数据通过接口回调给M层
    //25在第四个参数后面重写getParams方法 return 我们传入的map，这个方法的作用是，用来返回我们post需要传递的参数集合
    //      他的返回，会自动帮我们把post请求拼接好
    public void doPost(String url, final HashMap<String,String> map, final CallBack callBack){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callBack.success(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.falied(error.getMessage());
            }
        }){
            @Override
            protected Map getParams() {
                return map;
            }

        };

        requestQueue.add(stringRequest);
    }


    //创建接口
    public interface CallBack{
        void success(String json);
        void falied(String msg);
    }


}
