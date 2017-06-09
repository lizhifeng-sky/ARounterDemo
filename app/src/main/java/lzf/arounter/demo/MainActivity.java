package lzf.arounter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import lzf.baselibrary.base.BaseActivity;
@Route(path = "/moduleMain/activity")
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test1);
    }
    public void onClick(View view){
        ARouter.getInstance().build("/moduleMain/test").navigation();
    }
}
