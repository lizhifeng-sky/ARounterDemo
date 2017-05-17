package lzf.baselibrary.network.rx;

import android.content.Context;
import android.widget.Toast;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class LoadSubscriber<T> extends Subscriber<T> implements CancelLoadListener {
    private SubscriberOnNextListener<T> subscriberOnNextListener;
    private Context context;
    private LoadDialogHandler loadDialogHandler;

    public LoadSubscriber(SubscriberOnNextListener<T> subscriberOnNextListener,
                          Context context,
                          LoadDialogHandler loadDialogHandler) {
        this.subscriberOnNextListener = subscriberOnNextListener;
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
        dismiss();
    }

    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        dismiss();
    }

    @Override
    public void onNext(T t) {
        if (subscriberOnNextListener != null) {
            subscriberOnNextListener.onNext(t);
        }
    }


    @Override
    public void onStart() {
        super.onStart();
        show();
    }

    private void show() {
        if (loadDialogHandler != null) {
            loadDialogHandler.obtainMessage(LoadDialogHandler.SHOW).sendToTarget();
        }
    }

    private void dismiss() {
        if (loadDialogHandler != null) {
            loadDialogHandler.obtainMessage(LoadDialogHandler.DISMISS).sendToTarget();
            loadDialogHandler = null;
            onCancelLoad();
        }
    }
}
