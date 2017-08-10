package com.assetowl.android.data.mockapi;

import android.content.Context;

import com.assetowl.android.data.mockapi.lib.MockFilter;
import com.google.gson.Gson;

import okhttp3.Response;


/**
 * Created by patrickyin on 24/4/17.
 */

public class PostLogoutMockFilter extends MockFilter {


    public PostLogoutMockFilter(Context context, Gson gson) {
        super(context, gson);

    }

    @Override
    public String getResponseString() {
        return "null";
    }

    @Override
    public Response getCustomResponse() {
        return null;
    }
}
