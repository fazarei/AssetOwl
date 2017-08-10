package com.assetowl.android.data.utils;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.Map;
import java.util.StringTokenizer;

import io.intercom.retrofit2.http.Header;
import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by patrickyin on 14/2/17.
 */

public final class HTTPRequestBodyHelper {
    public final static RequestBody fromGson(Object src) {
        Gson gson = new Gson();
        String strRequestBody = gson.toJson(src);
        return RequestBody.create(MediaType.parse("application/json"), strRequestBody);
    }

    public final static RequestBody fromJSONObject(JSONObject parameters) {
        return RequestBody.create(MediaType.parse("application/json"), parameters.toString());
    }

}
