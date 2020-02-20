package com.bawei.dimensionshoppingmal.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.bawei.dimensionshoppingmal.activity.MainActivity;
import com.bawei.dimensionshoppingmal.context.App;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * TIme:2020/2/20
 * Author:孙帅喜
 * Descriotion:
 */
public class Myutils {
    private static Myutils myutils=new Myutils();

    private Myutils(){

    }

    public static Myutils getInstance(){
        return myutils;
    }

    public boolean isWock(Context context){
        ConnectivityManager co = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = co.getActiveNetworkInfo();
        if(activeNetworkInfo==null){
            return true;
        }
        return false;
    }

    Handler handler=new Handler();

    public interface Ijk{
        void onZhen(String json);
        void onJia(String msg);
    }

    public void getReg(final String path, final Map<String,String> map, final Ijk ijk){

        new Thread(){
           @Override
           public void run() {
               super.run();
                       try {
                           URL url = new URL(path);
                           HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                           conn.setRequestMethod("POST");
                           conn.setReadTimeout(5000);
                           conn.setConnectTimeout(5000);
                           conn.setUseCaches(false);
                           conn.setDoInput(true);
                           conn.setDoOutput(true);
                           //拼接中间人
                           StringBuilder builder = new StringBuilder();
                           //遍历集合
                           for(Map.Entry<String,String> entry:map.entrySet()){
                               String key = entry.getKey();
                               String value = entry.getValue();
                               builder.append(key+"="+value+"&");
                           }
                           String json = builder.toString();
                           json = json.substring(0, json.length() - 1);
                           Log.i("xxx",json);
                           OutputStream outputStream = conn.getOutputStream();
                           outputStream.write(json.getBytes());
                           outputStream.flush();
                           conn.connect();
                           //根据结果码判断
                           int responseCode = conn.getResponseCode();
                           if(responseCode==200){
                               InputStream inputStream = conn.getInputStream();
                               int len=0;
                               byte[] by=new byte[1024];
                               StringBuilder builder1 = new StringBuilder();
                               while((len=inputStream.read(by))!=-1){
                                   String s = new String(by, 0, len);
                                   builder1.append(s);
                               }
                               inputStream.close();
                               outputStream.close();
                               final String s = builder1.toString();
                               Log.i("xxx",s);
                               handler.post(new Runnable() {
                                   @Override
                                   public void run() {
                                       if(ijk!=null){
                                           ijk.onZhen(s);
                                       }
                                   }
                               });
                           }else{
                               handler.post(new Runnable() {
                                   @Override
                                   public void run() {
                                       ijk.onJia("失败");
                                   }
                               });
                           }
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }


       }.start();
    }
}
