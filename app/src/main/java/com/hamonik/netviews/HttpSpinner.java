package com.hamonik.netviews;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;

/**
 * Created by Moses Gitau on 5/19/17.
 */

public class HttpSpinner extends AppCompatSpinner implements FormInput {

    private String name;
    private Object value;

    public HttpSpinner(Context context) {
        super(context);
    }

    public HttpSpinner(Context context, int mode) {
        super(context, mode);
    }

    public HttpSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs);
    }

    public HttpSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(attrs);
    }

    public HttpSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode) {
        super(context, attrs, defStyleAttr, mode);
        setAttributes(attrs);
    }

    public HttpSpinner(Context context, AttributeSet attrs, int defStyleAttr, int mode, Resources.Theme popupTheme) {
        super(context, attrs, defStyleAttr, mode, popupTheme);
        setAttributes(attrs);
    }

    @Override
    public void setAttributes(AttributeSet attributeSet) {
        TypedArray array = getContext().obtainStyledAttributes(attributeSet, R.styleable.HttpSpinner);

        setName(array.getString(R.styleable.HttpSpinner_name));

        array.recycle();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getValue() {
        return getSelectedItem();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setValue(Object value) {
    }
}
