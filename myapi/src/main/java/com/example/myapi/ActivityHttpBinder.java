package com.example.myapi;

import android.app.Activity;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Machenike on 2017/7/17.
 */

public class ActivityHttpBinder {
    private static final ActivityDoHttp activityHttp=new ActivityDoHttp();
    private static final Map<String,ActivityHttpBinder> binderMap=new LinkedHashMap<>();
    public static void bind(Activity activity){
        bind(activity,activity,activityHttp);
    }
    /**
     * '注解绑定
     *
     * @param host   表示注解 View 变量所在的类，也就是注解类
     * @param object 表示查找 View 的地方，Activity & View 自身就可以查找，Fragment 需要在自己的 itemView 中查找
     * @param finder ui绑定提供者接口
     */
    private static void bind(Object host, Object object, DoHttp finder) {
        String className = host.getClass().getName();
        try {
            ActivityHttpBinder binder = binderMap.get(className);
            if (binder == null) {
                Class<?> aClass = Class.forName(className + "$$Http");
                binder = (ActivityHttpBinder) aClass.newInstance();
                binderMap.put(className, binder);
            }
            if (binder != null) {
                binder.bind(host, object, finder);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    /**
     * 解除注解绑定 ActivityViewFinder
     *
     * @param host
     */
    public static void unBind(Object host) {
        String className = host.getClass().getName();
        ActivityHttpBinder binder = binderMap.get(className);
        if (binder != null) {
            binder.unBind(host);
        }
        binderMap.remove(className);
    }
}
