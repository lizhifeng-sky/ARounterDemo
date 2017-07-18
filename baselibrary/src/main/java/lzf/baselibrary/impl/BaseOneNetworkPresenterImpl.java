package lzf.baselibrary.impl;

import android.content.Context;
import android.util.Log;

import lzf.baselibrary.base.BaseModel;
import lzf.baselibrary.base.BaseOneNetworkView;
import lzf.baselibrary.base.BasePresenter;
import lzf.baselibrary.base.BaseFourNetworkView;
import lzf.baselibrary.base.BaseThreeNetworkView;
import lzf.baselibrary.base.BaseTwoNetworkView;
import lzf.baselibrary.base.BaseView;
import lzf.baselibrary.model.BaseRequestMode;
import lzf.baselibrary.network.OnLoadStateListener;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/26 0026.
 * 这是一个示例  自定义presenter
 */
public class BaseOneNetworkPresenterImpl<T> extends BasePresenterImpl<T>{
    private BaseModel baseModel;
    private BaseOneNetworkView<T> baseView;
    private Observable currentObservable;
    private Context currentContext;

    public BaseOneNetworkPresenterImpl(BaseOneNetworkView baseView) {
        super(baseView);
    }
}
