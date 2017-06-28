package lzf.arounter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.ArrayList;
import java.util.List;

import lzf.baselibrary.base.BaseFourNetworkView;
import lzf.baselibrary.base.BaseOneNetworkView;
import lzf.baselibrary.base.BaseTwoNetworkView;
import lzf.baselibrary.bean.CityBean;
import lzf.baselibrary.bean.ConfigBean;
import lzf.baselibrary.bean.GuideBean;
import lzf.baselibrary.impl.BasePresenterImpl;

@Route(path = "/moduleMain/test")
public class TestActivity extends AppCompatActivity implements BaseTwoNetworkView<List<GuideBean>, ConfigBean> {
    private TextView start, second;
    private BasePresenterImpl mBasePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
    }

    public void next(final View view) {
        mBasePresenter.getData(apiService.getStartView(2), this);
    }

    @Override
    public void setListener() {
        //todo 设置监听
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBasePresenter.getData(apiService.getConfig(), TestActivity.this);
            }
        });
    }

    @Override
    public void getDataT(List<GuideBean> guideBeanList) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < guideBeanList.size(); i++) {
            sb.append(guideBeanList.get(i).toString());
        }
        second.setText(sb);
    }

    @Override
    public void getDataR(ConfigBean t) {
        StringBuilder sb = new StringBuilder();
        sb.append(t.toString());
        start.setText(sb);
    }


    @Override
    public void prepareData() {
        //todo 数据准备

    }

    @Override
    public void findView() {
        //todo findView
        start = (TextView) findViewById(R.id.start);
        second = (TextView) findViewById(R.id.second);
    }

    @Override
    public void setPresenter() {
        //todo 创建presenter
        mBasePresenter = new BasePresenterImpl(TestActivity.this);
    }

}
