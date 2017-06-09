package lzf.baselibrary.network;

import android.content.Context;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import lzf.baselibrary.model.SubscriberOnNextListener;
import rx.Subscriber;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class LoadSubscriber<T> extends Subscriber<T> implements CancelLoadListener{
    private OnLoadStateListener<T> onLoadStateListener;
    private Context context;
    private LoadDialogHandler loadDialogHandler;

    public LoadSubscriber(OnLoadStateListener<T> onLoadStateListener,
                          Context context,
                          LoadDialogHandler loadDialogHandler) {
        this.onLoadStateListener = onLoadStateListener;
        this.context = context;
        if (loadDialogHandler == null) {
            this.loadDialogHandler = new LoadDialogHandler(context);
        } else {
            this.loadDialogHandler = new LoadDialogHandler(context, this, true);
        }
    }

    @Override
    public void onCancelLoad() {
        if (!this.isUnsubscribed()) {
            this.unsubscribe();
        }
    }

    @Override
    public void onCompleted() {
        loadDialogHandler.dismiss();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            e.printStackTrace();
            Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        loadDialogHandler.onFailure(e);
        onLoadStateListener.onFailure(e);
    }

    @Override
    public void onNext(T t) {
        loadDialogHandler.onSuccess();
        if (onLoadStateListener != null) {
            onLoadStateListener.onSuccess(t);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        loadDialogHandler.onLoading();
    }

//    private void show() {
//        if (loadDialogHandler != null) {
//            loadDialogHandler.obtainMessage(LoadDialogHandler.SHOW).sendToTarget();
//        }
//    }

//    private void dismiss() {
//        if (loadDialogHandler != null) {
//            loadDialogHandler.obtainMessage(LoadDialogHandler.DISMISS).sendToTarget();
//            loadDialogHandler = null;
//            onCancelLoad();
//        }
//    }
}
