package lzf.api;

import android.app.Activity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/18 0018.
 */
public class LzfHttpBinder {
    private static final ActivityHttpFinder activityHttpFinder = new ActivityHttpFinder();
    private static final Map<String, HttpBinder> binderMap = new HashMap<>();

    public static void bind(Activity activity) {
        bind(activity, activityHttpFinder);
    }

    /**
     * @param activity
     * @param activityHttpFinder
     * @author lzf
     * create at 2017/7/18 0018 11:23
     * description 注解绑定
     */
    private static void bind(Activity activity, ActivityHttpFinder activityHttpFinder) {
        String className = activity.getClass().getName();
        try {
            HttpBinder binder = binderMap.get(className);
            if (binder == null) {
                Class<?> aClass = Class.forName(className + "$$Http");
                binder = (HttpBinder) aClass.newInstance();
                binderMap.put(className, binder);
            }
            if (binder != null) {
                binder.binder(activity, activityHttpFinder);
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
     * @param activity
     * @author lzf
     * create at 2017/7/18 0018 11:25
     * description 解除注解绑定
     */
    public static void unBind(Activity activity) {
        String className = activity.getClass().getName();
        HttpBinder binder = binderMap.get(className);
        if (binder != null) {
            binder.unBinder(activity);
        }
        binderMap.remove(className);
    }
}

