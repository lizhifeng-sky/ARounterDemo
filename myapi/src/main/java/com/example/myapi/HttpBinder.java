package com.example.myapi;

/**
 * Created by Machenike on 2017/7/17.
 */

public interface HttpBinder<T> {
    void bindView(T host,Object object,DoHttp find);
    void unBindView(T host);
}
