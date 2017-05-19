package com.hamonik.netviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.AttributeSet;

/**
 * Created by Moses Gitau on 5/19/17.
 */

public class HttpCheckBox extends AppCompatCheckBox implements FormInput {

    private String name;
    private Object value;

    public HttpCheckBox(Context context) {
        super(context);
    }

    public HttpCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs);
    }

    public HttpCheckBox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(attrs);
    }

    @Override
    public void setAttributes(AttributeSet attributeSet) {
        TypedArray array = getContext().obtainStyledAttributes(attributeSet, R.styleable.HttpCheckBox);

        setName(array.getString(R.styleable.HttpCheckBox_name));
        setValue(array.getString(R.styleable.HttpCheckBox_value));

        array.recycle();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Object getValue() {
        return isChecked() ? (value == null ? true : value) : null;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }
}
