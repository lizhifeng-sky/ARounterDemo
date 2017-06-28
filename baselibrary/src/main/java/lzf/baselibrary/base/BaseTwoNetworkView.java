package lzf.baselibrary.base;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public interface BaseTwoNetworkView<T,R> extends BaseView{
    void getDataT(T t);
    void getDataR(R t);
}
