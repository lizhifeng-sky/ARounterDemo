package lzf.baselibrary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2017/4/26 0026.
 */
public abstract class BaseActivity extends AppCompatActivity {
    public void startLibActivity(String activityName) {
        Intent intent = new Intent();
        intent.setClassName(this, activityName);
        startActivity(intent);
    }
}
