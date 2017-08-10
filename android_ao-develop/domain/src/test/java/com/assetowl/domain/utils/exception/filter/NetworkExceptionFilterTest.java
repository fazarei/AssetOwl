package com.assetowl.domain.utils.exception.filter;

import com.assetowl.domain.utils.exception.AssetOwlException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;
import rx.Observer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * Created by patrickyin on 16/3/17.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Response.class)
public class NetworkExceptionFilterTest {
    Response<?> response;


    @Before
    public void setUp() throws Exception {
        response = PowerMockito.mock(Response.class);
    }

    @Test
    public void shouldReplaceRetrofit401HTTPExceptionWithAssetOwlException() throws Exception {
        when(response.code()).thenReturn(401);
        Observable.error(new HttpException(response))
                .onErrorResumeNext(new NetworkExceptionFilter<Throwable, Observable<?>>())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        assertTrue(e instanceof AssetOwlException);
                        assertEquals(((AssetOwlException) e).getErrorCode(), AssetOwlException.AUTH_INVALID_USERNAME_OR_PASSWORD);
                        assertTrue(((AssetOwlException) e).isInvalidUsernameOrPasswordError());
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }

    @Test
    public void shouldReplaceRetrofit500HTTPExceptionWithAssetOwlException() throws Exception {
        when(response.code()).thenReturn(500);
        Observable.error(new HttpException(response))
                .onErrorResumeNext(new NetworkExceptionFilter<Throwable, Observable<?>>())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        assertTrue(e instanceof AssetOwlException);
                        assertEquals(((AssetOwlException) e).getErrorCode(), AssetOwlException.NETWORK_ISSUE);
                        assertTrue(((AssetOwlException) e).isNetworkError());
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }

    @Test
    public void shouldReplaceSocketTimeoutExceptionWithAssetOwlException() throws Exception {
        Observable.error(new SocketTimeoutException())
                .onErrorResumeNext(new NetworkExceptionFilter<Throwable, Observable<?>>())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        assertTrue(e instanceof AssetOwlException);
                        assertEquals(((AssetOwlException) e).getErrorCode(), AssetOwlException.NETWORK_TIME_OUT);
                        assertTrue(((AssetOwlException) e).isNetworkError());
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }

    @Test
    public void shouldReplaceIOExceptionWithAssetOwlException() throws Exception {
        Observable.error(new IOException())
                .onErrorResumeNext(new NetworkExceptionFilter<Throwable, Observable<?>>())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        assertTrue(e instanceof AssetOwlException);
                        assertEquals(((AssetOwlException) e).getErrorCode(), AssetOwlException.NETWORK_ISSUE);
                        assertTrue(((AssetOwlException) e).isNetworkError());
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }

    @Test
    public void shouldPassThroughOtherException() throws Exception {
        Observable.error(new NullPointerException())
                .onErrorResumeNext(new NetworkExceptionFilter<Throwable, Observable<?>>())
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        assertTrue(e instanceof NullPointerException);
                    }

                    @Override
                    public void onNext(Object o) {

                    }
                });
    }

}