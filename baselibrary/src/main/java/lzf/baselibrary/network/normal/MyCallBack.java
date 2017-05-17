package lzf.baselibrary.network.normal;


import java.net.ConnectException;
import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public abstract class MyCallBack<T extends BaseRequestMode> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.raw().code() == 200) {
            //200是服务器有合理响应
            if(response.body().error == 0){
                onSuccess(response);
            } else if (response.body().error == 2){
                onCustomBack();
            } else {
                onFailure(response.body().msg);
            }
        } else {
            //失败响应
            onFailure(call, new RuntimeException("response error,detail = " + response.raw().toString()));
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        if(t instanceof SocketTimeoutException){
            //
        }else if(t instanceof ConnectException){
            //
        }else if(t instanceof RuntimeException){
            //
        }
        onFailure(t.getMessage());
    }
    public abstract void onSuccess(Response<T> response);

    public abstract void onFailure(String message);

    public abstract void onCustomBack();
}
