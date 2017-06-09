package lzf.baselibrary.network;

import android.os.Environment;

import java.io.File;
import java.util.concurrent.TimeUnit;

import lzf.baselibrary.model.BaseRequestMode;
import okhttp3.Cache;
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
    private static RxAPIService apiService;
    private static RxSingleRetrofit mInstance;

    private RxSingleRetrofit() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        File httpCacheDirectory = new File(Environment.getExternalStorageDirectory(), "responses");
        builder.cache(new Cache(httpCacheDirectory,10 * 1024 * 1024));
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

    public RxAPIService create() {
        return apiService;
    }

//    public <T> void getStartView2(int type,
//                                  SubscriberOnNextListener<T> list_guide,
//                                  Context context) {
//        LoadSubscriber<T> subscriber = new LoadSubscriber<>(list_guide, context, null);
//        test(apiService.getStartView(type),subscriber);
//    }

    public <T> void getStartView(Observable<BaseRequestMode<T>> observable, Subscriber<T> subscriber) {
//        LoadSubscriber<T> subscriber = new LoadSubscriber<>(list_guide, context, null);
        test(observable,subscriber);
    }

    //处理线程调度的变换


    //剥开BaseRequestMode
    <T> Observable.Transformer<BaseRequestMode<T>, T> maps() {
        return new Observable.Transformer<BaseRequestMode<T>, T>() {
            @Override
            public Observable<T> call(Observable<BaseRequestMode<T>> observable) {
                return observable.map(new Func1<BaseRequestMode<T>, T>() {
                    @Override
                    public T call(BaseRequestMode<T> tBaseRequestMode) {
                        return tBaseRequestMode.getData();
                    }
                });
            }
        };
    }

    /*I
    Observable<T>
    * Observable<BaseRequestMode<O>>
    * Observable<O>
    *
    * */
    public  <T> void test(Observable<BaseRequestMode<T>> observable,Subscriber<T> s) {
        Observable o = observable.map(new Func1<BaseRequestMode<T>, T>() {
            @Override
            public T call(BaseRequestMode<T> tBaseRequestMode) {
                return tBaseRequestMode.getData();
            }
        });
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }
}
