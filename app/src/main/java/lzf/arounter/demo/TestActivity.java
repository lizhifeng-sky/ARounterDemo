package lzf.arounter.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;

import java.util.List;

import lzf.baselibrary.bean.GuideBean;
import lzf.baselibrary.bean.User;
import lzf.baselibrary.network.normal.APIService;
import lzf.baselibrary.network.normal.BaseRequestMode;
import lzf.baselibrary.network.normal.MyCallBack;
import lzf.baselibrary.network.normal.SingleRetrofit;
import lzf.baselibrary.network.rx.LoadSubscriber;
import lzf.baselibrary.network.rx.OnSuccessListener;
import lzf.baselibrary.network.rx.RxSingleRetrofit;
import lzf.baselibrary.network.rx.SubscriberOnNextListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
@Route(path = "/moduleMain/test")
public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(lzf.baselibrary.R.layout.activity_test);
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
        //rx封装2
        final TextView textView= (TextView) findViewById(R.id.start);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                SubscriberOnNextListener<BaseRequestMode<List<GuideBean>>> subscriberOnNextListener1=new SubscriberOnNextListener<BaseRequestMode<List<GuideBean>>>() {
//                    @Override
//                    public void onNext(BaseRequestMode<List<GuideBean>> listBaseRequestMode) {
//                        StringBuilder sb=new StringBuilder();
//                        for (int i = 0; i < listBaseRequestMode.getData().size(); i++) {
//                           sb.append(listBaseRequestMode.getData().get(i).toString());
//                        }
//
//                    }
//                };
//                RxSingleRetrofit.getInstance().getStartView(2,new LoadSubscriber<>(subscriberOnNextListener1,TestActivity.this,null));
                OnSuccessListener<List<GuideBean>> onSuccessListener=new OnSuccessListener<List<GuideBean>>() {
                    @Override
                    public void onSuccess(List<GuideBean> guideBeen) {
                        StringBuilder sb=new StringBuilder();
                        for (int i = 0; i < guideBeen.size(); i++) {
                            sb.append(guideBeen.get(i).toString());
                        }
                        textView.setText(sb);
                    }
                };
                RxSingleRetrofit.getInstance().getText2(2,onSuccessListener,TestActivity.this);
            }
        });
//        SubscriberOnNextListener<BaseRequestMode<User>> subscriberOnNextListener=new SubscriberOnNextListener<BaseRequestMode<User>>() {
//            @Override
//            public void onNext(BaseRequestMode<User> userBaseRequestMode) {
//
//            }
//        };
//        RxSingleRetrofit.getInstance().getUser("username", new LoadSubscriber<>(subscriberOnNextListener,this,null) );


    }
}
