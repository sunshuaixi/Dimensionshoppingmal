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
 *  101.开始编写网络工具类
 *  * 102.编写单例模式，我这里是静态内部类
 */
public class VolleyUtil {

    private final RequestQueue requestQueue;

    private VolleyUtil(){
        requestQueue = Volley.newRequestQueue(App.getAppContext());
    }
    private static class Singlelnstace{
      private static VolleyUtil INSTANCE=  new VolleyUtil();
    }
    public static VolleyUtil getInstance(){
        return Singlelnstace.INSTANCE;
    }

    /**
     * 110.写get请求方法，入参和以前一样，路径+callback
     * 111.我们通过new 创建一个StringRequest
     * 112.我们这里new的时候，需要传入四个参数
     * 113.第一个参数，我们的请求方式，这里是Request.Method.GET，代表我们发起get请求
     * 114.第二个参数，请求路径，对应传入的url
     * 115.第三个参数，请求返回值的监听 这里我们需要自己new一个，在监听当中，把数据通过callback回调给M层
     * 116.第四个参数，请求失败的监听，这里我们需要自己new一个，在监听当中，把失败的结果通过callback回调给M层
     *
     *
     *     请求失败：网络失败（没有网，链接超时）、参数拼错、请求格式错误等等导致网络请求无法完成的错误会走第四个参数
     *     如果说我们的账号密码错误，或者请求成功了，服务器告诉我们失败，会走第三个参数
     */
    public void doGet(String url, final Callack callack){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callack.success(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callack.falied(error.getMessage());
            }
        });

        requestQueue.add(stringRequest);
    }

    public void doPost(String url, final HashMap<String,String> map, final Callack callack){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        callack.success(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callack.falied(error.getMessage());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return map;
            }
        };

        requestQueue.add(stringRequest);
    }


    //接口
    public interface Callack{
        void success(String json);
        void falied(String msg);
    }

}
