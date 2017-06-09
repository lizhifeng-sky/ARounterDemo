package lzf.module_two;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import lzf.baselibrary.base.BaseActivity;

@Route(path = "/module2/activity")
public class ModuleTwoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module_two);
    }
    public void toModuleOne(View view){
        ARouter.getInstance().build("/module1/activity").navigation();
    }
}
