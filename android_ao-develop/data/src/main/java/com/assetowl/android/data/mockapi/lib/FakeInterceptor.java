package com.assetowl.android.data.mockapi.lib;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URI;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class FakeInterceptor implements Interceptor {
    private static final String TAG = FakeInterceptor.class.getSimpleName();
    private Context context;
    private Gson gson;
    private IMockFilter mockFilter;

    private String mContentType = "application/json";

    public FakeInterceptor(Context context, Gson gson) {
        this.context = context;
        this.gson = gson;
    }

    public FakeInterceptor setContentType(String contentType) {
        mContentType = contentType;
        return this;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        String method = chain.request().method().toLowerCase();

        Response response;
        final URI uri = chain.request().url().uri();
        Log.d(TAG, "--> Request url: [" + method.toUpperCase() + "]" + uri.toString());

        IMockFilter currentMockFilter;

        try {
            if(mockFilter != null) {
                currentMockFilter = mockFilter;
                mockFilter = null;
            } else {
                String packageName = this.getClass().getPackage().getName();
                currentMockFilter = (IMockFilter) Class.forName(packageName.substring(0, packageName.lastIndexOf(".") + 1) + upCaseFirstLetter(method) + upCaseFirstLetter(getFileNameWithoutExtension(chain)) + "MockFilter").getConstructor(Context.class, Gson.class).newInstance(context, gson);
            }
            currentMockFilter.setRequest(chain.request());
            response = currentMockFilter.buildResponse();
        } catch (ClassNotFoundException | NoSuchMethodException | InstantiationException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            response = new Response.Builder()
                    .code(500)
                    .request(chain.request())
                    .protocol(Protocol.HTTP_1_0)
                    .body(ResponseBody.create(MediaType.parse(mContentType), "".getBytes()))
                    .addHeader("content-type", mContentType)
                    .build();
        }

        Log.d(TAG, "<-- END [" + method.toUpperCase() + "]" + uri.toString());
        return response;
    }

    private String upCaseFirstLetter(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    private String getFileNameWithoutExtension(Chain chain) {
        String fileName = chain.request().url().pathSegments().get(chain.request().url().pathSegments().size() - 1);
        int indexOfDash = fileName.indexOf("-");
        if(indexOfDash != -1) {
            StringBuilder fileNameBuilder = new StringBuilder(fileName);
            if(indexOfDash + 1 < fileName.length()) {
                fileNameBuilder.setCharAt(indexOfDash + 1, fileName.toUpperCase().charAt(indexOfDash + 1));
            }
            fileNameBuilder.deleteCharAt(indexOfDash);
            fileName = fileNameBuilder.toString();
        }
        return fileName.isEmpty() ? "index" : fileName;
    }

    void setMockFilter(IMockFilter mockFilter) {
        this.mockFilter = mockFilter;
    }

}
