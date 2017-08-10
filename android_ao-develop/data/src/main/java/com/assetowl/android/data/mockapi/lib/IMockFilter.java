package com.assetowl.android.data.mockapi.lib;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by patrickyin on 22/3/17.
 */

public interface IMockFilter {
    void setRequest(Request request);
    Response buildResponse();
    int getResponseStatusCode();
    void setResponseStatusCode(int RespCode);
    String getResponseString();
    Response getCustomResponse();
    Response generateTimeoutResponse();
    void setContentType(String contentType);
    String getContentType();
}
