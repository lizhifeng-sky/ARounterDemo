package com.example.proxy;

import java.io.FileOutputStream;
import java.io.IOException;

import sun.misc.ProxyGenerator;

/**
 * Created by Administrator on 2017/9/11 0011.
 */
public class ProxyGeneratorUtils {

    public static void writeProxyClassToHardDisk(String path) {

        byte[] classFile = ProxyGenerator.generateProxyClass("LzfProxy", UserServiceImpl.class.getInterfaces());

        FileOutputStream out = null;

        try {
            out = new FileOutputStream(path);
            out.write(classFile);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
