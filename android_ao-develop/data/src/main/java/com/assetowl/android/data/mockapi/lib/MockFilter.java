package com.assetowl.android.data.mockapi.lib;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;

/**
 * Created by patrickyin on 22/3/17.
 */

public abstract class MockFilter implements IMockFilter {
    protected static final String TAG = FakeInterceptor.class.getSimpleName();
    protected Gson gson;
    protected String requestString;
    protected Context context;
    protected Request request;
    private String contentType = "application/json";
    private int responseStatusCode = 200; // default return HTTP 200 response

    public MockFilter(Context context, Gson gson) {
        this.context = context;
        this.gson = gson;

    }


    public void setRequest(Request request) {
        this.request = request;
        if(request.body() != null) {
            Buffer buffer = new Buffer();
            try {
                request.body().writeTo(buffer);
                requestString = buffer.readUtf8();
            } catch (IOException e) {
                requestString = "";
                e.printStackTrace();
            }
        }
    }


    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return contentType;
    }

    public Response buildResponse() {
        if(getCustomResponse() == null) {
            String responseString = getResponseString();
            Log.d(TAG, "Response: " + responseString);
            return new Response.Builder()
                    .code(getResponseStatusCode())
                    .message(responseString)
                    .request(request)
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse(contentType), responseString.getBytes()))
                    .addHeader("content-type", contentType)
                    .build();
        } else {
            return getCustomResponse();
        }
    }

    public Response generateTimeoutResponse() {
        setResponseStatusCode(504);
        String contentType = "application/json";
        try {
            Thread.sleep(5_000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new Response.Builder()
                .code(getResponseStatusCode())
                .message("null")
                .request(request)
                .protocol(Protocol.HTTP_1_0)
                .body(ResponseBody.create(MediaType.parse(contentType), "null".getBytes()))
                .addHeader("content-type", contentType)
                .build();
    }

    public int getResponseStatusCode() {
        return responseStatusCode;
    }
    public void setResponseStatusCode(int respCode) {
        responseStatusCode = respCode;
    }

    public abstract String getResponseString();

    public abstract Response getCustomResponse();
}
