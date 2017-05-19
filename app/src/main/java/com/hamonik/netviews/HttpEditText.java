package com.hamonik.netviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;

/**
 * Created by Moses Gitau on 5/19/17.
 */

public class HttpEditText extends AppCompatEditText implements FormInput {

    private String name;

    public HttpEditText(Context context) {
        super(context);
    }

    public HttpEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs);
    }

    public HttpEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(attrs);
    }

    @Override
    public void setAttributes(AttributeSet attributeSet) {
        TypedArray array = getContext().obtainStyledAttributes(attributeSet, R.styleable.HttpEditText);

        setName(array.getString(R.styleable.HttpEditText_name));

        array.recycle();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return getText().toString();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setValue(Object value) {
        setText(value.toString());
    }

}
