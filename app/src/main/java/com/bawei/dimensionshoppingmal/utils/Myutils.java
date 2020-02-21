package com.bawei.dimensionshoppingmal.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

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
    public static Myutils myutils=new Myutils();

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
        void onSuccess(String json);
        void onError(String msg);
    }

    public void getJson(final String path, final Ijk ijk){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(path);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("GET");
                    conn.setConnectTimeout(5000);
                    conn.setReadTimeout(5000);
                    int responseCode = conn.getResponseCode();
                    if(responseCode==200){
                        InputStream inputStream = conn.getInputStream();
                        int len=0;
                        byte[] by=new byte[1024];
                        StringBuilder builder = new StringBuilder();
                        while((len=inputStream.read(by))!=-1){
                            String s = new String(by, 0, len);
                            builder.append(s);
                        }
                        inputStream.close();
                        final String json = builder.toString();

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if(ijk!=null){
                                    ijk.onSuccess(json);
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
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
                    conn.setDoOutput(true);
                    conn.setDoInput(true);
                    StringBuilder builder = new StringBuilder();
                    for(Map.Entry<String,String> entry:map.entrySet()){
                        String key = entry.getKey();
                        String value = entry.getValue();
                        builder.append(key+"="+value+"&");
                    }
                    String user = builder.toString();
                    user=user.substring(0,user.length()-1);
                    OutputStream outputStream = conn.getOutputStream();
                    outputStream.write(user.getBytes());
                    outputStream.flush();
                    conn.connect();
                    int responseCode = conn.getResponseCode();
                    if(responseCode==200){
                        InputStream inputStream = conn.getInputStream();
                        int len=0;
                        byte[] by=new byte[1024];
                        StringBuffer buffer = new StringBuffer();
                        while((len=inputStream.read(by))!=-1){
                            buffer.append(new String(by,0,len));
                        }
                        inputStream.close();
                        outputStream.close();
                        final String s = buffer.toString();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                               ijk.onSuccess(s);
                            }
                        });
                    }else{
                        Log.i("xxx","请求失败");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();

    }
}
