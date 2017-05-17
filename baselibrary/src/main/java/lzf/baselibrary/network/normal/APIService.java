package lzf.baselibrary.network.normal;

import lzf.baselibrary.bean.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public interface APIService {
    String BASE_URL="   ";
    /*
     * http://base_url/springmvc_users/user/zhy
     * */
    @GET("springmvc_users/user/{username}")
    Call<BaseRequestMode<User>> getUser(@Path("username") String username);

    @GET("user/login")
    Call<BaseRequestMode<User>> doLogin(@Query("email") String email, @Query("password") String pwd);
}
