package com.example.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/9/11 0011.
 */
public class ProxyTest implements InvocationHandler {
    private Object object;

    public ProxyTest(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("********** before *******");
        Object result=method.invoke(object,args);
        System.out.println("********** after *******");
        return result;
    }

    public Object getProxy(){
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                object.getClass().getInterfaces(),this);
    }
}
