package lzf.api;

/**
 * Created by Administrator on 2017/7/18 0018.
 */
public interface HttpBinder<T> {
    void binder(T activity,HttpFinder find);
    void unBinder(T host);
}
