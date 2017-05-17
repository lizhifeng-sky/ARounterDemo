package lzf.arounter.demo;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import lzf.baselibrary.BaseActivity;
@Route(path = "/moduleMain/activity")
public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void onClick(View view){
        ARouter.getInstance().build("/moduleMain/test").navigation();
    }
}
