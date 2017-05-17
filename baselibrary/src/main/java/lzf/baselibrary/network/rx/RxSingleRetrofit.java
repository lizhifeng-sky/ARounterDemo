package lzf.baselibrary.network.rx;

import android.content.Context;

import java.util.List;
import java.util.concurrent.TimeUnit;

import lzf.baselibrary.bean.GuideBean;
import lzf.baselibrary.bean.User;
import lzf.baselibrary.network.normal.BaseRequestMode;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class RxSingleRetrofit {
    public static final int DEFAULT_TIMEOUT = 5;
    private RxAPIService apiService;
    private static RxSingleRetrofit mInstance;
    private OnSuccessListener onSuccessListener;

    private RxSingleRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        Retrofit mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(RxAPIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = mRetrofit.create(RxAPIService.class);
    }

    public static RxSingleRetrofit getInstance() {
        if (mInstance == null) {
            synchronized (RxSingleRetrofit.class) {
                mInstance = new RxSingleRetrofit();
            }
        }
        return mInstance;
    }

    public void getUser(String username, User user) {
        Observable observable = Observable
                .just(user)
                .map(new Func1<User, BaseRequestMode<User>>() {
                    @Override
                    public BaseRequestMode<User> call(User user) {
                        BaseRequestMode<User> baseRequestMode = new BaseRequestMode<User>();
                        baseRequestMode.setData(user);
                        return baseRequestMode;
                    }
                });
        observable.map(new Func1<String, BaseRequestMode<String>>() {

            @Override
            public BaseRequestMode<String> call(String s) {
                BaseRequestMode<String> b = new BaseRequestMode<String>();
                b.setData(s);
                return b;
            }
        });
//        apiService.getUser(username)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
    }

    public void getStartView(int type, Subscriber<BaseRequestMode<List<GuideBean>>> subscriber) {
//        apiService.getStartView(type)
//                .subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
    }

    public void getStartView2(int type,
                              SubscriberOnNextListener list_guide,
                              Context context) {
        LoadSubscriber<BaseRequestMode> subscriber = new LoadSubscriber<>(list_guide, context, null);
        apiService.getStartView(type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public <T> void getText(int type, final OnSuccessListener on, Context context) {
        Observable<T> o = apiService.getStartView(type).map(
                new Func1<BaseRequestMode, T>() {
                    @Override
                    public T call(BaseRequestMode tBaseRequestMode) {
                        return (T) tBaseRequestMode.getData();
                    }
                });
        LoadSubscriber<T> sub = new LoadSubscriber<>(new SubscriberOnNextListener<T>() {
            @Override
            public void onNext(T t) {
                on.onSuccess(t);
            }
        }, context, null);
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sub);
    }

    public <T> void getText2(int type, final OnSuccessListener<T> on, Context context) {
        LoadSubscriber sub = new LoadSubscriber<>(new SubscriberOnNextListener<BaseRequestMode<T>>() {
            @Override
            public void onNext(BaseRequestMode<T> tBaseRequestMode) {
                on.onSuccess(tBaseRequestMode.getData());
            }
        }, context, null);
        apiService.getStartView(type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(sub);
    }
}
