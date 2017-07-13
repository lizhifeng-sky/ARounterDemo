package lzf.baselibrary.image;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;


/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class ImageLoaderManager implements ImageLoader {
    private static final ImageLoaderManager INSTANCE=new ImageLoaderManager();
    private  ImageLoader imageLoader;
    private ImageLoaderManager(){
    }
    public static ImageLoaderManager getInstance(){
        return INSTANCE;
    }

    public  void setImageLoader(ImageLoader strategy){
        imageLoader=strategy;
    }

    /*
     *  可创建默认的Options设置，假如不需要使用ImageView ，
     *  请自行new一个ImageView传入即可
     *  内部只需要获取Context
     */
    public static ImageLoaderOptions getDefaultOptions(@NonNull View container, @NonNull String url){
        return new ImageLoaderOptions.Builder(container,url).isCrossFade(true).build();
    }

    @Override
    public void showImage(@NonNull ImageLoaderOptions options) {
        if (imageLoader != null) {
            imageLoader.showImage(options);
        }
    }


    @Override
    public void cleanMemory(Context context) {
        imageLoader.cleanMemory(context);
    }

    // 在application的onCreate中初始化
    @Override
    public void init(Context context) {
        imageLoader=new FrescoImageLoader();
//      imageLoader=new GlideImageLoader();
        imageLoader.init(context);
    }

}
