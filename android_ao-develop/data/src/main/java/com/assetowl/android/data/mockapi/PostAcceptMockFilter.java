package com.assetowl.android.data.mockapi;

import android.content.Context;

import com.assetowl.android.data.mockapi.lib.MockFilter;
import com.google.gson.Gson;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by farzanehzarei on 6/4/17.
 */

public class PostAcceptMockFilter extends MockFilter {


    public PostAcceptMockFilter(Context context, Gson gson) {
        super(context, gson);
    }

    @Override
    public String getResponseString() {
        if(request.header("Authorization").equals("Bearer " + PostLoginMockFilter.ACCESS_TOKEN_T_AND_C_ACCEPT_NETWORK_ISSUE)) {
            return "";
        }
        return getSuccessResponseString();
    }

    private String getSuccessResponseString() {
        return "{\n" +
                "  \"accessToken\": \"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE0OTAxMzk3MzUsImlhdCI6MT\",\n" +
                "  \"refreshToken\": \"eyJ2YWx1ZSI6ImRhMjU2ODkzLWY2YTAtNDdmMy05NmFlLTVlMDgzZmMzODU3YiIsImlkIjoiNTBkNjA0MTQtZTdjZC00NWYwLWI4YWItODYyZTMzNjJlMDNjIn0=\",\n" +
                "  \"firstName\": \"AssetOwl\",\n" +
                "  \"lastName\": \"Support\"\n" +
                "}";
    }

    @Override
    public Response getCustomResponse() {
        return null;
    }

}
