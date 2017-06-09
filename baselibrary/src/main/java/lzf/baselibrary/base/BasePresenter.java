package lzf.baselibrary.base;

import android.content.Context;

import lzf.baselibrary.model.BaseRequestMode;
import rx.Observable;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public interface BasePresenter {
    /*
    * 获取数据
    * */
    <T> void getData(Observable<BaseRequestMode<T>> t, Context context);
}
