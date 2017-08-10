package com.assetowl.android.data.mockapi;

import android.content.Context;

import com.assetowl.android.data.mockapi.lib.MockFilter;
import com.google.gson.Gson;

import okhttp3.Response;


/**
 * Created by farzanehzarei on 6/4/17.
 */

public class GetLatestMockFilter extends MockFilter {


    public GetLatestMockFilter(Context context, Gson gson) {
        super(context, gson);

    }

    @Override
    public String getResponseString() {
        if (request.header("Authorization").equals("Bearer " + PostLoginMockFilter.ACCESS_TOKEN_T_AND_C_NETWORK_ISSUE)) {
            return "";
        }
        return getSuccessResponseString();
    }

    private String getSuccessResponseString() {
        return "{\n" +
                "  \"id\": 98,\n" +
                "  \"version\": 2,\n" +
                "  \"type\": \"EULA\",\n" +
                "  \"addedDate\": 1466139859000,\n" +
                "  \"mustAccept\": true,\n" +
                "  \"lang\": \"en-AU\",\n" +
                "  \"contentType\": \"text/html\",\n" +
                "  \"title\": \"End User License Agreement\",\n" +
                "  \"body\": \"<h1>End User License Agreement</h1>\"\n" +
                "}";
    }

    @Override
    public Response getCustomResponse() {
        return null;
    }
}
