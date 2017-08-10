package com.assetowl.android.data.mockapi;

import android.content.Context;

import com.assetowl.android.data.mockapi.lib.MockFilter;
import com.google.gson.Gson;

import okhttp3.Response;

/**
 * Created by farzanehzarei on 17/5/17.
 */

public class GetFeatureToggleMockFilter extends MockFilter {

    public GetFeatureToggleMockFilter(Context context, Gson gson) {
        super(context,gson);
    }

    @Override
    public String getResponseString() {
        if (request.header("Authorization").equals("Bearer " + PostLoginMockFilter.ACCESS_TOKEN)) {
            return "";
        }
        return getSuccessResponseString();
    }

    private String getSuccessResponseString() {
        return "[\n" +
                "{\n" +
                "  \"name\": \"floorplanbuilde\",\n" +
                "  \"displayName\": \"Store floorplan builder\",\n" +
                "  \"description\": \"Allow users to modify floorplan\",\n" +
                "  \"status\": \"ENABLED\",\n" +
                "  \"allowUserOverride\": false\n" +
                "},\n"+
                "{\n" +
                "  \"name\": \"s3-mbtiles\",\n" +
                "  \"displayName\": \"S3 MBTile\",\n" +
                "  \"description\": \"Fetch mbtiles from S3 rather than\",\n" +
                "  \"status\": \"ENABLED\",\n" +
                "  \"allowUserOverride\": false\n" +
                "}\n" +
                "]";
    }

    @Override
    public Response getCustomResponse() {
        return null;
    }
}
