package lzf.baselibrary.image;

import android.support.annotation.DrawableRes;

/**
 * Created by Administrator on 2017/6/28 0028.
 */
public interface ImageLoadInterface {
    void loadResouresImage(@DrawableRes int id);
    void loadImage(String url);

}
