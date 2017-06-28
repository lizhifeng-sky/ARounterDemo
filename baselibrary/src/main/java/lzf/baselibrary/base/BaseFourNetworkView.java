package lzf.baselibrary.base;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public interface BaseFourNetworkView<T,R,Q,W> {
    /*
    * 设置view数据
    * */
    void getDataT(T t);
    void getDataR(R t);
    void getDataQ(Q t);
    void getDataW(W t);
}
