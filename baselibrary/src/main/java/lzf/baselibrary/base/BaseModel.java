package lzf.baselibrary.base;

import android.content.Context;

import lzf.baselibrary.network.OnLoadStateListener;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public interface BaseModel{
    <T> void loadData(OnLoadStateListener<T> onLoadStateListener,
                      Context context, Observable... observable);
    <T> void loadData(OnLoadStateListener<T> onLoadStateListener,
                      Context context, Observable observable);
}
