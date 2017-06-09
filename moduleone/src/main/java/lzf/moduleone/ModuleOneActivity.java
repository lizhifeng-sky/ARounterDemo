package lzf.moduleone;

import android.os.Bundle;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

import lzf.baselibrary.base.BaseActivity;
@Route(path = "/module1/activity")
public class ModuleOneActivity extends BaseActivity {
    MyTextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_one_activity_module_one);
//        GifView gif1 = (GifView) findViewById(R.id.gif1);
//        // 设置背景gif图片资源
//        gif1.setMovieResource(R.raw.test);
//        GifView gif2 = (GifView) findViewById(R.id.gif2);
//        gif2.setMovieResource(R.raw.test);
        textView= (MyTextView) findViewById(R.id.text);
//        Log.e("lzf_text",textView.isOverFlowed()+"");
//        textView.isOverFlowed();

    }
    public void onClick(View view){
        ARouter.getInstance().build("/moduleMain/activity").navigation();
    }
}
