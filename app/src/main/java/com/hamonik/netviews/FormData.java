package com.hamonik.netviews;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Moses Gitau on 5/19/17.
 */

public class FormData extends JSONObject {

    public FormData() {

    }

    public FormData(Map copyFrom) {
        super(copyFrom);
    }

    public FormData(JSONTokener tokener) throws JSONException {
        super(tokener);
    }

    public FormData(String json) throws JSONException {
        super(json);
    }

    public FormData(JSONObject copyFrom, String[] names) throws JSONException {
        super(copyFrom, names);

    }

    public Map<String, Object> toMap() {
        return new Gson().fromJson(
                this.toString(),
                new TypeToken<HashMap<String, Object>>() {
                }.getType()
        );
    }
}
