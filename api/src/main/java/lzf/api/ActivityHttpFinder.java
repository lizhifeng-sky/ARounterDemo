package lzf.api;

import android.app.Activity;
import android.util.Log;

import com.example.NetUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by Administrator on 2017/7/18 0018.
 */
public class ActivityHttpFinder implements HttpFinder {

    @Override
    public void executeHttp(final Activity activity, final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //从网络获取数据
                final String response = NetUtils.post(url,"2");
                //向Handler发送处理操作
                Log.e("Lzf-test_finder","网络请求的数据是："+response);
                Method method= null;
                try {
                    method = activity.getClass().getMethod(url,String.class);
                    method.invoke(activity,response);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
}
