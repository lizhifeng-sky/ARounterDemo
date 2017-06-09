package lzf.baselibrary.network;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public interface OnDialogStateListener {
    void onSuccess();
    void onFailure(Throwable throwable);
    void onLoading();
    void retry();
}
