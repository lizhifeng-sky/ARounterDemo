package lzf.moduleone;

import android.content.Context;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/5/12 0012.
 */
public class MyTextView extends TextView {
    MyTextListener myTextListener;

    public MyTextView(Context context) {
        super(context);
    }

    public MyTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        isOverFlowed();
    }

    @Override
    public boolean onPreDraw() {
        Log.e("lzf_text",getLineCount()+"");
        return super.onPreDraw();
    }

    public void setMyTextListener(MyTextListener myTextListener) {
        this.myTextListener = myTextListener;
    }

    public boolean isOverFlowed() {
        Paint paint = getPaint();
        float width = paint.measureText(getText().toString());
        if (width > getAvailableWidth()) {
            return true;
        } else {
            return false;
        }
    }

    private int getAvailableWidth() {
        return getWidth() - getPaddingLeft() - getPaddingRight();
    }

    interface MyTextListener {
        boolean isOverFlowed();
    }
}
