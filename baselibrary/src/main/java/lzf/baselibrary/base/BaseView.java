package lzf.baselibrary.base;

import lzf.baselibrary.network.RxAPIService;
import lzf.baselibrary.network.RxSingleRetrofit;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public interface BaseView<T> {
    RxAPIService apiService= RxSingleRetrofit.getInstance().create();
    /*
    * 准备数据 获得传值
    * */
    void prepareData();
    /*
    * 获取view 如果用注解的话 这就不需要了
    * */
    void findView();
    /*
    * 设置监听
    * */
    void setListener();
    /*
    * 设置presenter
    * */
    void setPresenter();
//    /*
//    * 获取数据
//    * */
//    void getViewData();
    /*
    * 设置view数据
    * */
    void setViewData(T t);
}
