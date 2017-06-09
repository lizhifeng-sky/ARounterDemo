package lzf.baselibrary.model;

import android.content.Context;

import java.util.List;

import lzf.baselibrary.bean.GuideBean;
import lzf.baselibrary.network.LoadSubscriber;
import lzf.baselibrary.network.RxSingleRetrofit;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public class DataModel {
    /*
        多个网络请求进行合并
    *   Observable placeObservable = repository.getPlaceList(context);
        Observable weatherObservable =  ServiceManager.getInstance().getApiService().getWeatherInfo(place, Constants.BAIDU_AK);
        Observable.merge(placeObservable, weatherObservable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Object>() {
                    @Override
                    public void onCompleted() {
                        mMainView.hideProgress();
                    }
                    @Override
                    public void onError(Throwable e) {
                        mLogger.error(e.getMessage(), e);
                        mMainView.hideProgress();
                    }
                    @Override
                    public void onNext(Object obj) {
                        if (obj instanceof List) {
                            mMainView.setupPlaceData((List<Place>) obj);
                        } else if (obj instanceof WeatherResponse) {
                            mMainView.setupWeatherData((WeatherResponse) obj);
                        }
                    }
                });
    * */

    public void getStartView(int type, Context context){
//        SubscriberOnNextListener<List<GuideBean>> subscriberOnNextListener= new SubscriberOnNextListener<List<GuideBean>>() {
//            @Override
//            public void onNext(List<GuideBean> listBaseRequestMode) {
//
//            }
//        };
//        Observable observable= RxSingleRetrofit.getInstance().create().getStartView(2);
//        LoadSubscriber subscriber = new LoadSubscriber<>(subscriberOnNextListener, context, null);
//        RxSingleRetrofit.getInstance().getStartView(observable,subscriber);
    }
}
