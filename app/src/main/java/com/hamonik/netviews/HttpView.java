package com.hamonik.netviews;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Moses Gitau on 5/19/17.
 */

public class HttpView extends Form {

    public HttpView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HttpView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HttpView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    public void loadData() {
        Call<Map<String, Object>> webCall;
        Map<String, Object> paramsMap = createParamsMap();
        if (getMethod() == GET) {
            webCall = webInterface.get(getAction(), paramsMap);
        } else if (getMethod() == POST) {
            webCall = webInterface.get(getAction(), paramsMap);
        } else {
            webCall = null;
        }
        webCall.enqueue(new Callback<Map<String, Object>>() {
            @Override
            public void onResponse(Call<Map<String, Object>> call, Response<Map<String, Object>> response) {
                if (formListener != null) {
                    formListener.onResponse(new FormData(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Map<String, Object>> call, Throwable t) {
                t.printStackTrace();
                if (formListener != null) {
                    formListener.onFailure(t);
                }
            }
        });
    }
}
