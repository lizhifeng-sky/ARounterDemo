package lzf.baselibrary.network;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public interface OnLoadStateListener<T> {
     void onSuccess(T t);
    void onFailure(Throwable throwable);
    void retry();
}
