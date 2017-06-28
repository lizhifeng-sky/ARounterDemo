package lzf.baselibrary.image;

import android.support.annotation.DrawableRes;

/**
 * Created by Administrator on 2017/6/28 0028.
 */
public interface ImageLoadInterface {
    /*
    * 加载drawable图片
    * */
    void loadResouresImage(@DrawableRes int id);

    /*
    * 加载网络图片
    * */
    void loadImage(String url);

    /*
    * 加载本地的图片
    * */
    void loadLocalImage(String filePath);

    /*
    * 圆形
    * */
    void asCircel();

    /*
    * 圆角
    * */
    void asRound(int r);

    /*
    * 圆环 颜色 宽度
    * */
    void setCorner(int width, int color);

    /*
    * 获取bitmap
    * */
    void asBitmap();

    /*
    *把资源作为GifDrawable对待。如果资源不是gif动画将会失败，会回调.error()
    * */
    void asGif();

    /*
    * 重新设置大小
    * */
    void resize(int width, int height);

    /*
    * 占位图
    * */
    void placeHolder(int id);

    /*
    * 失败图
    * */
    void error(int id);
}
