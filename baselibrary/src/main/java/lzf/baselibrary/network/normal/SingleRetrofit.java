package lzf.baselibrary.network.normal;

import java.util.concurrent.TimeUnit;

import lzf.baselibrary.bean.User;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class SingleRetrofit {
    public static final int DEFAULT_TIMEOUT=5;
    private Retrofit mRetrofit;
    private APIService apiService;
    private static SingleRetrofit mInstance;
    private SingleRetrofit(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        mRetrofit = new Retrofit.Builder()
                .client(builder.build())
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiService = mRetrofit.create(APIService.class);
    }
    public static SingleRetrofit getInstance(){
        if (mInstance == null){
            synchronized (SingleRetrofit.class){
                mInstance = new SingleRetrofit();
            }
        }
        return mInstance;
    }
    public Call<BaseRequestMode<User>> getUser(String username){
        return apiService.getUser(username);
    }
}
