package lzf.arounter.demo;

import com.alibaba.android.arouter.launcher.ARouter;

import lzf.baselibrary.BaseApplication;

/**
 * Created by Administrator on 2017/4/27 0027.
 */
public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
    }
}
