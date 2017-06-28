package lzf.baselibrary.base;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public interface BaseThreeNetworkView<T,R,Q> {
    void getDataT(T t);
    void getDataR(R t);
    void getDataQ(Q t);
}
