package com.assetowl.android.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import com.assetowl.android.BuildConfig;
import com.assetowl.android.data.authorization.AuthInterceptor;
import com.assetowl.android.data.mockapi.lib.FakeInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by patrickyin on 14/2/17.
 */

@Module
public class AssetOwlIOModule {
    private final OkHttpClient okHttpClient;
    private final Gson gson;
    private final Retrofit retrofit;
    private final SharedPreferences sharedPreferences;
    private final static String FILE_NAME_SHARED_PREFERENCES = "assetowl";

    public AssetOwlIOModule(Context context) {
        // Add caching strategy to okHttpClient

        this.gson = new GsonBuilder()
                .create();

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(BuildConfig.NETWORKING_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(BuildConfig.NETWORKING_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(BuildConfig.NETWORKING_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new AuthInterceptor());

        this.okHttpClient = BuildConfig.FLAVOR.equals("stub") ?
                builder.addInterceptor(new FakeInterceptor(context, gson)).build() :
                builder.build();

        this.retrofit = new Retrofit.Builder()
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(BuildConfig.BACKEND_URL)
                .client(okHttpClient)
                .build();
        this.sharedPreferences = context.getSharedPreferences(FILE_NAME_SHARED_PREFERENCES, Context.MODE_PRIVATE);
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient() {
        return okHttpClient;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return gson;
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit() {
        return retrofit;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences() {
        return sharedPreferences;
    }
}
