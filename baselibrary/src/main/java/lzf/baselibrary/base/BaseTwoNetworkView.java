package lzf.baselibrary.base;

import lzf.baselibrary.network.RxAPIService;
import lzf.baselibrary.network.RxSingleRetrofit;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public interface BaseTwoNetworkView<T,R> extends BaseView{
    RxAPIService apiService= RxSingleRetrofit.getInstance().create();
    void getDataT(T t);
    void getDataR(R t);
}
