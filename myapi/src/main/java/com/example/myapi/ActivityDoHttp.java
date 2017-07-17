package com.example.myapi;

import android.util.Log;

/**
 * Created by Machenike on 2017/7/17.
 */

public class ActivityDoHttp implements DoHttp {
    @Override
    public void doHttp(final String url) {
       new Thread(new Runnable() {
           @Override
           public void run() {
               //从网络获取数据
               final String response = NetUtils.post(url,"2");
               //向Handler发送处理操作
                Log.e("Lzf-test","网络请求的数据是："+response);
           }
       });
    }
}
