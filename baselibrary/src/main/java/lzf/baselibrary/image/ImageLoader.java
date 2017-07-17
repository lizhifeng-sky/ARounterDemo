package lzf.baselibrary.image;

import android.content.Context;
import android.support.annotation.NonNull;

/**
 * Created by Administrator on 2017/6/28 0028.
 */
public interface ImageLoader {
    void showImage(@NonNull ImageLoaderOptions options);
    void cleanMemory(Context context);
    void init(Context context);
}
