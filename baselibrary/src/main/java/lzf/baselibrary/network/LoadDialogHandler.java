package lzf.baselibrary.network;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/**
 * Created by Administrator on 2017/5/17 0017.
 */
public class LoadDialogHandler extends Handler implements OnDialogStateListener{
    private ProgressDialog progressDialog;
    private Context context;
    private boolean cancelable=false;
    private CancelLoadListener cancelLoadListener;

    public LoadDialogHandler(Context context, CancelLoadListener cancelLoadListener, boolean cancelable) {
        super();
        this.cancelable = cancelable;
        this.context = context;
        this.cancelLoadListener = cancelLoadListener;
    }
    public LoadDialogHandler(Context context) {
        super();
        //默认不可取消
        this.context = context;
    }
    public void show() {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
            progressDialog.setCancelable(cancelable);
            if (cancelable) {
                progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        cancelLoadListener.onCancelLoad();
                    }
                });
            }
        }
        if (!progressDialog.isShowing()) {
            progressDialog.show();
        }
    }

    public void dismiss() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    @Override
    public void onSuccess() {
        dismiss();
    }

    @Override
    public void onFailure(Throwable throwable) {
        progressDialog.setMessage(throwable.getMessage());
    }

    @Override
    public void onLoading() {
        show();
    }

    @Override
    public void retry() {

    }
}
