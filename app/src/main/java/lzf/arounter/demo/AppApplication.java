package lzf.arounter.demo;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.launcher.ARouter;

import lzf.baselibrary.base.BaseApplication;
import lzf.baselibrary.base.BaseFourNetworkView;
import lzf.baselibrary.base.BaseOneNetworkView;
import lzf.baselibrary.base.BaseTwoNetworkView;

/**
 * Created by Administrator on 2017/4/27 0027.
 */
public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        ARouter.init(this);
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (activity instanceof BaseTwoNetworkView){
                    ((BaseTwoNetworkView) activity).prepareData();
                    ((BaseTwoNetworkView) activity).findView();
                    ((BaseTwoNetworkView) activity).setPresenter();
                    ((BaseTwoNetworkView) activity).setListener();
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }
}
