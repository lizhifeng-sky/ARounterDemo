package lzf.arounter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;

import lzf.baselibrary.base.BaseView;
import lzf.baselibrary.bean.CityBean;
import lzf.baselibrary.bean.GuideBean;
import lzf.baselibrary.impl.BasePresenterImpl;

@Route(path = "/moduleMain/test")
public class TestActivity extends AppCompatActivity implements BaseView {
    private TextView start;
    private StringBuilder sb = new StringBuilder();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //常规的方式
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("xxx")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        APIService apiService = retrofit.create(APIService.class);
//        Call<BaseRequestMode<User>> call=apiService.getUser("username");
//        call.enqueue(new MyCallBack<BaseRequestMode<User>>() {
//            @Override
//            public void onSuc(Response<BaseRequestMode<User>> response) {
//
//            }
//
//            @Override
//            public void onFail(String message) {
//
//            }
//
//            @Override
//            public void onAutoLogin() {
//
//            }
//        });
        //常规封装
//        SingleRetrofit.getInstance().getUser("username").enqueue(new MyCallBack<BaseRequestMode<User>>() {
//            @Override
//            public void onSuccess(Response<BaseRequestMode<User>> response) {
//
//            }
//
//            @Override
//            public void onFailure(String message) {
//
//            }
//
//            @Override
//            public void onCustomBack() {
//
//            }
//        });
        //rx 封装
//        RxSingleRetrofit.getInstance().getUser("username", new Subscriber<BaseRequestMode<User>>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(BaseRequestMode<User> userBaseRequestMode) {
//
//            }
//        });

    }

    public void next(final View view) {
        mBasePresenter.getData(apiService.getStartView(2), this);
    }

    @Override
    public void prepareData() {
        //todo 数据准备

    }

    @Override
    public void findView() {
        //todo findView
        start= (TextView) findViewById(R.id.start);
    }

    @Override
    public void setListener() {
        //todo 设置监听
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mBasePresenter.getData(apiService.getCityInfo(),TestActivity.this);
            }
        });
    }

    BasePresenterImpl mBasePresenter;

    @Override
    public void setPresenter() {
        //todo 创建presenter
        mBasePresenter=new BasePresenterImpl(TestActivity.this);
    }

    @Override
    public void setViewData(Object o) {
        if (o instanceof List){
            List list= (List) o;
            if (list.get(0) instanceof GuideBean){
                for (int i = 0; i < list.size(); i++) {
                    sb.append(list.get(i).toString());
                }
                start.setText(sb);
            }else if (list.get(0) instanceof CityBean){
                for (int i = 0; i < list.size(); i++) {
                    sb.append(list.get(i).toString());
                }
                start.setText(sb);
            }
        }
    }

}
