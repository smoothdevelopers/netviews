package com.hamonik.netviews;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;

/**
 * Created by Moses Gitau on 5/19/17.
 */

public class HttpButton extends CircularProgressButton {

    public HttpButton(Context context) {
        super(context);
    }

    public HttpButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HttpButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HttpButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
