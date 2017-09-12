package com.example.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/9/12 0012.
 */
public class Test {
    public static void main(String[] args) {
        Class c=getPersonClass();
        getPersonMethod(c);
        getPersonField(c);
    }

    private static void getPersonField(Class c) {
        Field[] fields=c.getFields();
        Field[] fields1=c.getDeclaredFields();
//        try {
////            Field field=c.getField("age");
//        } catch (NoSuchFieldException e) {
//            e.printStackTrace();
//        }
    }

    private static void getPersonMethod(Class c) {
        Method[] methods=c.getMethods();//包括父类
        Method[] method2=c.getDeclaredMethods();//不包括父类
        try {
            Object o=c.newInstance();
            try {
                Method method=c.getMethod("print");
                method.invoke(o);
            } catch (NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Class getPersonClass() {
        //获取class
        //方式1
        Class class1 = Person.class;
        //方式2
        Person person = new Person("张三", "18");
        Class class2 = person.getClass();
        //方式3
        Class class3 = null;
        try {
            class3 = Class.forName("com.example.reflect.Person");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(class1 + " \n " + class2 + "  \n " + class3);
        return class2;
    }
}
