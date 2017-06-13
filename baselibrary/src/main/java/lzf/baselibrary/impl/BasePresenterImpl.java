package lzf.baselibrary.impl;

import android.content.Context;
import android.util.Log;

import lzf.baselibrary.base.BasePresenter;
import lzf.baselibrary.base.BaseView;
import lzf.baselibrary.base.BaseModel;
import lzf.baselibrary.model.BaseRequestMode;
import lzf.baselibrary.network.OnLoadStateListener;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public class BasePresenterImpl implements BasePresenter, OnLoadStateListener {
    private BaseModel baseModel;
    private BaseView baseView;
    private Observable currentObservable;
    private Context currentContext;

    public BasePresenterImpl(BaseView baseView) {
        this.baseView = baseView;
        baseModel = new BaseModelImpl();
    }

    @Override
    public <T> void getData(Observable<BaseRequestMode<T>> observable,
                            Context context) {
        //
        currentObservable=observable;
        currentContext=context;
        load();
    }

    @Override
    public  <T> void getData(Context context, Observable<BaseRequestMode<T>>... observables) {
        baseModel.loadData(this, context, observables);
    }

    @Override
    public void onSuccess(Object o) {
        baseView.setViewData(o);
    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e("BasePresenterImpl", "onFailure   " + throwable.getMessage());
    }

    @Override
    public void retry() {
        load();
    }
    public void load(){
        baseModel.loadData(this, currentContext, currentObservable);
    }
}
