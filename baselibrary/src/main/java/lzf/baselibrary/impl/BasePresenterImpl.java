package lzf.baselibrary.impl;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

import lzf.baselibrary.base.BaseOneNetworkView;
import lzf.baselibrary.base.BasePresenter;
import lzf.baselibrary.base.BaseFourNetworkView;
import lzf.baselibrary.base.BaseModel;
import lzf.baselibrary.base.BaseThreeNetworkView;
import lzf.baselibrary.base.BaseTwoNetworkView;
import lzf.baselibrary.base.BaseView;
import lzf.baselibrary.model.BaseRequestMode;
import lzf.baselibrary.network.OnLoadStateListener;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public class BasePresenterImpl<T> implements BasePresenter, OnLoadStateListener<T> {
    private BaseModel baseModel;
    private BaseFourNetworkView baseFourNetworkView = null;
    private BaseThreeNetworkView baseThreeNetworkView = null;
    private BaseTwoNetworkView baseTwoNetworkView = null;
    private BaseOneNetworkView baseOneNetworkView = null;
    private BaseView baseView;
    private Observable currentObservable;
    private Context currentContext;

    public BasePresenterImpl(BaseView baseView) {
        this.baseView = baseView;
        baseModel = new BaseModelImpl();
    }

    public BasePresenterImpl(BaseFourNetworkView baseView) {
        this.baseFourNetworkView = baseView;
        baseModel = new BaseModelImpl();
    }

    public BasePresenterImpl(BaseThreeNetworkView baseView) {
        this.baseThreeNetworkView = baseView;
        baseModel = new BaseModelImpl();
    }

    public BasePresenterImpl(BaseTwoNetworkView baseView) {
        this.baseTwoNetworkView = baseView;
        baseModel = new BaseModelImpl();
    }

    public BasePresenterImpl(BaseOneNetworkView baseView) {
        this.baseOneNetworkView = baseView;
        baseModel = new BaseModelImpl();
    }

    @Override
    public <T> void getData(Observable<BaseRequestMode<T>> observable,
                            Context context) {
        currentObservable = observable;
        currentContext = context;
        load();
    }

    @Override
    public <T> void getData(Context context, Observable<BaseRequestMode<T>>... observables) {
        baseModel.loadData(this, context, observables);
    }

    @Override
    public void onSuccess(T o) {
        //// TODO: 2017/6/28 0028 类型判断需要改 妹的  还没改完 
        if (baseOneNetworkView != null) {
            baseOneNetworkView.getDataT(o);
        } else if (baseTwoNetworkView != null) {
//            Class ca = baseTwoNetworkView.getClass();
//            Type[] genType = ca.getGenericInterfaces();
//            Type[] params = null;
//            for (Type aGenType : genType) {
//                if (aGenType.toString().contains("BaseTwoNetworkView")) {
//                    params = ((ParameterizedType) aGenType).getActualTypeArguments();
//                }
//            }
//            /*
//            * params 获取泛型中的类型数组
//            * */
////            String className = o.getClass().getName();
//            //获取返回值的类型
//            Class list = o.getClass();
//            Field[] fields=list.getDeclaredFields();
//            Class<?> field=o.getClass().getComponentType();
//            Type[] genType_result = list.getGenericInterfaces();
//            Type[] params_list=null;
//            for (Type list_result:genType_result
//                 ) {
//                if (list.toString().contains("List<E>")){
//                    params_list=((ParameterizedType) list_result).getActualTypeArguments();
//                }
//            }
            try {
                baseTwoNetworkView.getDataT(o);
            } catch (Exception e) {
                baseTwoNetworkView.getDataR(o);
            }
        } else if (baseThreeNetworkView != null) {
            try {
                baseThreeNetworkView.getDataT(o);
            } catch (Exception e) {
                try {
                    baseThreeNetworkView.getDataR(o);
                } catch (Exception e1) {
                    baseThreeNetworkView.getDataQ(o);
                }
            }
        } else if (baseFourNetworkView != null) {
            try {
                baseFourNetworkView.getDataT(o);
            } catch (Exception e) {
                try {
                    baseFourNetworkView.getDataR(o);
                } catch (Exception e1) {
                    try {
                        baseFourNetworkView.getDataQ(o);
                    } catch (Exception e2) {
                        baseFourNetworkView.getDataW(o);
                    }
                }
            }
        }

    }

    @Override
    public void onFailure(Throwable throwable) {
        Log.e("BasePresenterImpl", "onFailure   " + throwable.getMessage());
    }

    @Override
    public void retry() {
        load();
    }

    public void load() {
        baseModel.loadData(this, currentContext, currentObservable);
    }
}
