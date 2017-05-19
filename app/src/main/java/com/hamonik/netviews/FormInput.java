package com.hamonik.netviews;

import android.util.AttributeSet;

/**
 * Created by Moses Gitau on 5/19/17.
 */

public interface FormInput {


    void setAttributes(AttributeSet attributeSet);

    String getName();

    Object getValue();

    void setName(String name);

    void setValue(Object value);
}
