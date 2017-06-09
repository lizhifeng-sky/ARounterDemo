package lzf.baselibrary.model;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class BaseRequestMode<T> {
    public int error;
    public String msg;
    public T data;

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
