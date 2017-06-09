package lzf.arounter.demo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;

import lzf.baselibrary.base.BaseView;
import lzf.baselibrary.bean.GuideBean;
import lzf.baselibrary.impl.BasePresenterImpl;

@Route(path = "/moduleMain/test")
public class TestActivity extends AppCompatActivity implements BaseView<List<GuideBean>> {
    private TextView start;
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
        setPresenter();
//                SubscriberOnNextListener<List<GuideBean>> subscriberOnNextListener= new SubscriberOnNextListener<List<GuideBean>>() {
//                    @Override
//                    public void onNext(List<GuideBean> listBaseRequestMode) {
//                        StringBuilder sb=new StringBuilder();
//                        for (int i = 0; i < listBaseRequestMode.size(); i++) {
//                            sb.append(listBaseRequestMode.get(i).toString());
//                        }
//                        ((TextView)view).setText(sb);
//                    }
//                };
//        Observable observable=RxSingleRetrofit.getInstance().create().getStartView(2);
//        LoadSubscriber subscriber = new LoadSubscriber<>(subscriberOnNextListener, this, null);
//        RxSingleRetrofit.getInstance().getStartView(observable,subscriber);

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
                mBasePresenter.getData(apiService.getCityInfo(),getApplicationContext());
            }
        });
    }

    BasePresenterImpl mBasePresenter;

    @Override
    public void setPresenter() {
        mBasePresenter=new BasePresenterImpl(this);
        //todo 创建presenter
//        if (mBasePresenter instanceof BasePresenter) {
        mBasePresenter.getData(apiService.getStartView(2), this);
//        }
    }

    @Override
    public void setViewData(List<GuideBean> guideBeanList) {
        //todo 将数据展示
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < guideBeanList.size(); i++) {
            sb.append(guideBeanList.get(i).toString());
        }
        start.setText(sb);
    }
}
