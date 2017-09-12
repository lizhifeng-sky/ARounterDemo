package com.example.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/9/12 0012.
 */
public class Test {
    public static void main(String[]args){
        Enhancer enhancer=new Enhancer();
        enhancer.setSuperclass(Person.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                System.out.println(" before method "+method.getName());
                Object result = proxy.invokeSuper(obj, args);
                System.out.println(" after method "+method.getName());
                return result;
            }
        });
        Person person= (Person) enhancer.create();
        person.setName("zhangsan");
        person.setAge("15");
        person.method();
    }
}
