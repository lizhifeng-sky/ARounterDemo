package lzf.baselibrary.impl;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        //// TODO: 2017/6/28 0028 这里可能会出现类型转换异常
        try {
            if (baseOneNetworkView != null) {
                baseOneNetworkView.getDataT(o);
            } else if (baseTwoNetworkView != null) {
                Class ca = baseTwoNetworkView.getClass();
                List<String> resultType = getRequestType(ca, "BaseTwoNetworkView");
                String className = getResultClassName(o);
                if (resultType != null) {
                    Log.e("lzf_className", className + "  " + resultType.get(0) + "   " + resultType.get(1));
                    if (className.equals(resultType.get(0))) {
                        baseTwoNetworkView.getDataT(o);
                    } else if (className.equals(resultType.get(1))) {
                        baseTwoNetworkView.getDataR(o);
                    }
                }
            } else if (baseThreeNetworkView != null) {
                Class ca = baseThreeNetworkView.getClass();
                List<String> resultType = getRequestType(ca, "BaseThreeNetworkView");
                String className = getResultClassName(o);
                if (resultType != null) {
                    Log.e("lzf_className", className + "  " + resultType.get(0) + "   " + resultType.get(1));
                    if (className.equals(resultType.get(0))) {
                        baseThreeNetworkView.getDataT(o);
                    } else if (className.equals(resultType.get(1))) {
                        baseThreeNetworkView.getDataR(o);
                    } else if (className.equals(resultType.get(2))){
                        baseThreeNetworkView.getDataQ(o);
                    }
                }
            } else if (baseFourNetworkView != null) {
                Class ca = baseFourNetworkView.getClass();
                List<String> resultType = getRequestType(ca, "BaseFourNetworkView");
                String className = getResultClassName(o);
                Log.e("lzf_className", className + "  " + resultType.get(0) + "   " + resultType.get(1));
                if (className.equals(resultType.get(0))) {
                    baseFourNetworkView.getDataT(o);
                } else if (className.equals(resultType.get(1))) {
                    baseFourNetworkView.getDataR(o);
                } else if (className.equals(resultType.get(2))){
                    baseFourNetworkView.getDataQ(o);
                } else if (className.equals(resultType.get(3))){
                    baseFourNetworkView.getDataW(o);
                }
            }
        } catch (ClassCastException | NullPointerException e) {
            e.printStackTrace();
        }

    }
    private List<String> getRequestType(Class ca, String baseViewType) {
        List<String> types = new ArrayList<>();
        Type[] genType = ca.getGenericInterfaces();
        Type[] params = null;
             /*
            * params 获取泛型中的类型数组
            * */
        for (Type aGenType : genType) {
            if (aGenType.toString().contains(baseViewType)) {
                params = ((ParameterizedType) aGenType).getActualTypeArguments();
                if (params[0] instanceof ParameterizedType) {
                    Type[] test = ((ParameterizedType) params[0]).getActualTypeArguments();
                    if (test[0] instanceof Class) {
                        if (!types.contains(((Class) test[0]).getName())) {
                            types.add("java.util.List<" + ((Class) test[0]).getName() + ">");
                        }
                    }
                }else if (params[0] instanceof Class){
                    if (!types.contains(((Class) params[0]).getName())) {
                        types.add(((Class) params[0]).getName());
                    }
                }

                if (params.length>=2) {
                    if (params[1] instanceof ParameterizedType) {
                        Type[] test = ((ParameterizedType) params[1]).getActualTypeArguments();
                        if (test[1] instanceof Class) {
                            if (!types.contains(((Class) test[1]).getName())) {
                                types.add("java.util.List<" + ((Class) test[1]).getName() + ">");
                            }
                        }
                    }else if (params[1] instanceof Class){
                        if (!types.contains(((Class) params[1]).getName())) {
                            types.add(((Class) params[1]).getName());
                        }
                    }
                }

                if (params.length>=3) {
                    if (params[2] instanceof ParameterizedType) {
                        Type[] test = ((ParameterizedType) params[2]).getActualTypeArguments();
                        if (test[2] instanceof Class) {
                            if (!types.contains(((Class) test[2]).getName())) {
                                types.add("java.util.List<" + ((Class) test[2]).getName() + ">");
                            }
                        }
                    }else if (params[2] instanceof Class){
                        if (!types.contains(((Class) params[2]).getName())) {
                            types.add(((Class) params[2]).getName());
                        }
                    }
                }

                if (params.length>=4) {
                    if (params[3] instanceof ParameterizedType) {
                        Type[] test = ((ParameterizedType) params[3]).getActualTypeArguments();
                        if (test[3] instanceof Class) {
                            if (!types.contains(((Class) test[3]).getName())) {
                                types.add("java.util.List<" + ((Class) test[3]).getName() + ">");
                            }
                        }
                    }else if (params[3] instanceof Class){
                        if (!types.contains(((Class) params[3]).getName())) {
                            types.add(((Class) params[3]).getName());
                        }
                    }
                }


            }
        }
        return types;
    }

    private String getResultClassName(T o) {
        String className;
        if (o instanceof ArrayList) {
            Class c = ((ArrayList) o).get(0).getClass();
            className = c.getName();
            className = "java.util.List<" + className + ">";
        } else {
            className = o.getClass().getName();
        }
        return className;
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
