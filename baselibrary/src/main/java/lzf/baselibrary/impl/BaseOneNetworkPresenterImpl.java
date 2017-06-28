package lzf.baselibrary.impl;

import android.content.Context;
import android.util.Log;

import lzf.baselibrary.base.BaseModel;
import lzf.baselibrary.base.BasePresenter;
import lzf.baselibrary.base.BaseFourNetworkView;
import lzf.baselibrary.model.BaseRequestMode;
import lzf.baselibrary.network.OnLoadStateListener;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public class BaseOneNetworkPresenterImpl<T> implements BasePresenter,OnLoadStateListener<T>{
    private BaseModel baseModel;
    private BaseFourNetworkView baseView;
    private Observable currentObservable;
    private Context currentContext;

    public BaseOneNetworkPresenterImpl(BaseFourNetworkView baseView) {
        this.baseView = baseView;
        baseModel = new BaseModelImpl();
    }

    @Override
    public <T> void getData(Observable<BaseRequestMode<T>> t, Context context) {
        currentObservable=t;
        currentContext=context;
        load();
    }

    @Override
    public <T> void getData(Context context, Observable<BaseRequestMode<T>>... observables) {
        baseModel.loadData(this, context, observables);
    }

    @Override
    public void onSuccess(T t) {
        baseView.getDataT(t);
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
