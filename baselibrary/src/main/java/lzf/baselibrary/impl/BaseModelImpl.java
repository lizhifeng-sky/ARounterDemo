package lzf.baselibrary.impl;

import android.content.Context;

import lzf.baselibrary.base.BaseModel;
import lzf.baselibrary.model.BaseRequestMode;
import lzf.baselibrary.network.LoadSubscriber;
import lzf.baselibrary.network.OnLoadStateListener;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public class BaseModelImpl implements BaseModel {

    @Override
    public <T> void loadData(Observable observable, OnLoadStateListener<T> onLoadStateListener, Context context) {
        LoadSubscriber subscriber = new LoadSubscriber<>(onLoadStateListener, context, null);
//        observable.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(subscriber);
        test(observable,subscriber);
    }
    public  <T> void test(Observable<BaseRequestMode<T>> observable, Subscriber<T> s) {
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
