package com.hamonik.netviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Created by Moses Gitau on 5/18/17.
 */

public class HttpTextView extends AppCompatTextView implements FormInput {

    private String name;

    public HttpTextView(Context context) {
        super(context);
    }

    public HttpTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs);
    }

    public HttpTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setAttributes(attrs);
    }

    @Override
    public void setAttributes(AttributeSet attributeSet) {
        TypedArray array = getContext().obtainStyledAttributes(attributeSet, R.styleable.HttpTextView);

        setName(array.getString(R.styleable.HttpTextView_name));

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
