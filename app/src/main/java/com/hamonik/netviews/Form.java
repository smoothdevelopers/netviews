package com.hamonik.netviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
/*
 * Created by Moses Gitau on 5/16/17.
 */

public abstract class Form extends LinearLayout {

    public static final String TAG = Form.class.getName();
    public static final int GET = 1;
    public static final int POST = 2;
    protected final ArrayList<FormInput> inputs = new ArrayList<>();
    private int method;
    private String action;
    private String baseUrl;
    protected FormData bodyObject;
    protected FormListener formListener;
    protected static WebInterface webInterface;
    private final HashMap<String, Object> extras = new HashMap<>();
    private final ArrayList<String> ignores = new ArrayList<>();

    public Form(Context context, AttributeSet attrs) {
        super(context, attrs);
        setFormAttributes(attrs);
    }

    public Form(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFormAttributes(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Form(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setFormAttributes(attrs);
    }


    private void setFormAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.Form);

        //GET by default
        method = typedArray.getInt(R.styleable.Form_method, 1);
        action = typedArray.getString(R.styleable.Form_action);
        baseUrl = typedArray.getString(R.styleable.Form_baseUrl);

        typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        findInputs(this);
        webInterface = RetrofitClient.getClient(baseUrl).create(WebInterface.class);
    }

    private void findInputs(ViewGroup viewGroup) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View child = viewGroup.getChildAt(i);
            if (child instanceof FormInput) {
                inputs.add((FormInput) child);
            } else if (child instanceof ViewGroup && !(child instanceof Form)) {
                findInputs((ViewGroup) child);
            }
        }
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
        webInterface = RetrofitClient.getClient(baseUrl).create(WebInterface.class);
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    protected void constructFormData() throws JSONException {
        bodyObject = new FormData();
        for (FormInput formInput : inputs) {
            if (formInput.getValue() != null)
                bodyObject.put(formInput.getName(), formInput.getValue());
        }
    }


    public void addIncludeParam(String key, Object value) {
        extras.put(key, value);
    }

    public void removeIncludeParam(String key) {
        extras.remove(key);
    }

    public void addIgnoreParam(String key) {
        ignores.add(key);
    }

    public void removeIgnoreParam(String key) {
        ignores.remove(key);
    }

    protected Map<String, Object> createParamsMap() {
        HashMap<String, Object> params = (HashMap<String, Object>) bodyObject.toMap();
        params.putAll(extras);
        for (String key : ignores) {
            params.remove(key);
        }
        return params;
    }

    public void setFormListener(FormListener formListener) {
        this.formListener = formListener;
    }

    public void removeFormListener(FormListener formListener) {
        this.formListener = null;
    }

    public interface FormListener {
        void onSubmitButtonClicked();

        boolean validate(FormData formData);

        void onResponse(FormData response);

        void onFailure(Throwable throwable);
    }

    protected interface WebInterface {

        @GET
        Call<Map<String, Object>> get(@Url String url, @QueryMap Map<String, Object> body);

        @FormUrlEncoded
        @POST
        Call<Map<String, Object>> post(@Url String url, @Body Map<String, Object> body);
    }

    private static class RetrofitClient {

        private static Retrofit retrofit = null;

        public static Retrofit getClient(String baseUrl) {
            if (retrofit == null) {
                retrofit = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }
    }
}