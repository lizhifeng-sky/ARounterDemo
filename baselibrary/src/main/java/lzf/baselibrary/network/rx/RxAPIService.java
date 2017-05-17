package lzf.baselibrary.network.rx;

import java.util.List;
import java.util.Map;

import lzf.baselibrary.bean.GuideBean;
import lzf.baselibrary.bean.User;
import lzf.baselibrary.network.normal.BaseRequestMode;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface RxAPIService {
    String BASE_URL="http://new.antwk.com/";
    /*
     * http://base_url/springmvc_users/user/zhy
     * */
    @GET("springmvc_users/user/{username}")
    Observable<BaseRequestMode<User>> getUser(@Path("username") String username);

    @GET("user/login")
    Observable<BaseRequestMode<User>> doLogin(@Query("email") String email, @Query("password") String pwd);

    //请求静态url地址
    //无参数
    /*
    @post比@GET多了一个@FromUrlEncoded的注解。
    如果去掉@FromUrlEncoded
    在post请求中使用@Field和@FieldMap，
    那么程序会抛出Java.lang.IllegalArgumentException: @Field parameters can only be used with form encoding. (parameter #1)的错误异常。
    如果将@FromUrlEncoded
    添加在@GET上面呢，
    同样的也会抛出java.lang.IllegalArgumentException:FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).的错误异常。
    * */
    @POST("users/111/222")
    Observable<User> getPost();
    //少数参数
     /*
    *1:android X ；2:android XX；3:IOS X ；4:IOS XX ；5:IOS XXX
    * */
    @FormUrlEncoded
    @POST("api/app/startView")
    Observable<BaseRequestMode<List<GuideBean>>> getStartView(@Field("appType") int type);
    //参数较多
    @FormUrlEncoded
    @POST("users//111/222")
    Observable<User> getPost(@FieldMap Map<String, String> params);

    //无参数
    @GET("users/111/222")
    Observable<User> getGet();
    //少数参数
    @GET("users/111/222")
    Observable<User> getGet(@Query("time") long time);
    //参数较多
    @GET("users/111/222")
    Observable<User> getGet(@QueryMap Map<String, String> params);

    /*
    * @Body  直接传入一个对象  需要后端配合
    * @Url   不清楚  应该是一个完整的请求地址  此时base_url应该失效
    * @Path  不清楚
    * */

}
