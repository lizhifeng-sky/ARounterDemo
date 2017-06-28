package lzf.baselibrary.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/4/26 0026.
 */
public abstract class BaseActivity<T> extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
