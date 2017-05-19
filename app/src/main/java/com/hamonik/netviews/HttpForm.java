package com.hamonik.netviews;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Moses Gitau on 5/19/17.
 */

public class HttpForm extends Form implements View.OnClickListener {


    private HttpButton submitButton;

    public HttpForm(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HttpForm(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HttpForm(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        findHttpButton(this);
        if (submitButton != null) {
            submitButton.setOnClickListener(this);
        }
    }


    private void findHttpButton(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof HttpButton) {
                submitButton = (HttpButton) child;
            } else if (child instanceof ViewGroup && !(child instanceof Form)) {
                findHttpButton((ViewGroup) child);
            }
        }
    }

    public void submitStarted() {
        if (submitButton != null)
            submitButton.startAnimation();
    }

    public void submitEnded() {
        if (submitButton != null)
            submitButton.revertAnimation();
    }

    public void submitData() {
        try {
            submitStarted();
            if (formListener != null) {
                formListener.onSubmitButtonClicked();
            }
            constructFormData();
            if (formListener == null || formListener.validate(bodyObject)) {
                Call<Map<String, Object>> webCall;
                Map<String, Object> paramsMap = createParamsMap();
                if (getMethod() == GET) {
                    webCall = webInterface.get(getAction(), paramsMap);
                } else if (getMethod() == POST) {
                    webCall = webInterface.post(getAction(), paramsMap);
                } else {
                    webCall = null;
                }
                webCall.enqueue(new Callback<Map<String, Object>>() {
                    @Override
                    public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                        if (formListener != null) {
                            formListener.onResponse(new FormData(response.body()));
                        }
                        submitEnded();
                    }

                    @Override
                    public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                        t.printStackTrace();
                        if (formListener != null) {
                            formListener.onFailure(t);
                        }
                        submitEnded();
                    }
                });
            } else {
                submitEnded();
            }
        } catch (JSONException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void onClick(View view) {
        if (view == submitButton) {
            submitData();
        }
    }
}
