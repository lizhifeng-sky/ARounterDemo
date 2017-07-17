package lzf.arounter.demo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
import lzf.baselibrary.bean.UserBean;
import lzf.baselibrary.impl.BasePresenterImpl;
import lzf.baselibrary.model.BaseRequestMode;
import lzf.baselibrary.network.RxAPIService;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Route(path = "/moduleMain/test")
public class TestActivity extends AppCompatActivity implements BaseTwoNetworkView<List<GuideBean>, ConfigBean> {
    private TextView start, second;
    private BasePresenterImpl mBasePresenter;
    Looper
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
                test();
//                mBasePresenter.getData(apiService.getConfig(), TestActivity.this);
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

    public void test() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://new.antwk.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RxAPIService apiService = retrofit.create(RxAPIService.class);
        apiService.getStartView(2)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BaseRequestMode<List<GuideBean>>>() {
                    @Override
                    public void onCompleted() {
                        Log.e("lzf", "onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("lzf", e.getMessage());
                    }

                    @Override
                    public void onNext(BaseRequestMode<List<GuideBean>> userBeanBaseRequestMode) {
                        Message message = Message.obtain();
                        message.obj = userBeanBaseRequestMode;
                        myHandler.sendMessage(message);
                    }
                });
    }

    private Handler myHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
        }
    };

    class MyHttp {
        private Handler myHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle bundle = msg.getData();
            }
        };
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://new.antwk.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        RxAPIService apiService = retrofit.create(RxAPIService.class);

        public void test() {
            apiService.getStartView(2)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Subscriber<BaseRequestMode<List<GuideBean>>>() {
                                          @Override
                                          public void onCompleted() {
                                              Log.e("lzf", "onCompleted");
                                          }

                                          @Override
                                          public void onError(Throwable e) {
                                              Log.e("lzf", e.getMessage());
                                          }

                                          @Override
                                          public void onNext(BaseRequestMode<List<GuideBean>> userBeanBaseRequestMode) {
                                              Message message = Message.obtain();
                                              message.obj = userBeanBaseRequestMode;
                                              myHandler.sendMessage(message);
                                          }
                                      }
                            );
        }
    }
}
