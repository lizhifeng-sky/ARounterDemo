package com.example.proxy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import sun.misc.ProxyGenerator;

/**
 * Created by Administrator on 2017/9/11 0011.
 */
public class Test {
    public static void main(String[] args){
        UserServiceImpl service=new UserServiceImpl();
        System.out.println("________________");
        ProxyTest proxyTest=new ProxyTest(service);
        UserService proxy= (UserService) proxyTest.getProxy();
        proxy.add();

//        String test="张三";
//        System.out.println(Arrays.toString(test.getBytes()));
//        byte[] bytes=new byte[4];
//        bytes[0]=-54;
//        bytes[1]=-2;
//        bytes[2]=-70;
//        bytes[3]=-66;
//        String tes=new String(bytes);
//        System.out.println(test);
//        System.out.println(tes);
//        UserService userService = new UserServiceImpl();
//        ProxyTest invocationHandler = new ProxyTest(
//                userService);
//
//        UserService proxy = (UserService) invocationHandler.getProxy();
//        proxy.add();
//
//        try {
//            Class c =Class.forName("D:/qiniu/utils/x86/LivePlayer_Android_Demo_v1.2.4/ARounterDemo/proxy/build/classes/main/com/example/LzfProxy");
//
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        testGenerateProxyClass();
    }

    public static void testGenerateProxyClass() {
        ProxyGeneratorUtils.writeProxyClassToHardDisk("D:/qiniu/utils/x86/LivePlayer_Android_Demo_v1.2.4/ARounterDemo/proxy/build/classes/main/com/example/LzfProxy.class");
    }
}
