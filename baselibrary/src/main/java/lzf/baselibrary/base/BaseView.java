package lzf.baselibrary.base;

import lzf.baselibrary.network.RxAPIService;
import lzf.baselibrary.network.RxSingleRetrofit;

/**
 * Created by Administrator on 2017/6/26 0026.
 */
public interface BaseView {
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
}
