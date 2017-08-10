package com.assetowl.domain.utils.exception.filter;
import com.assetowl.domain.utils.exception.AssetOwlException;

import java.io.IOException;
import java.net.SocketTimeoutException;

import rx.functions.Func1;
import retrofit2.adapter.rxjava.HttpException;
import rx.Observable;

/**
 * Created by patrickyin on 15/3/17.
 */

public class NetworkExceptionFilter<T, O extends rx.Observable<?>> implements Func1<T, O> {

    @SuppressWarnings({"unchecked"})
    @Override
    public O call(T o) {
        Observable<?> observable;
        if(o instanceof HttpException) {
            String errorMessage = ((HttpException) o).getMessage() != null ? ((HttpException) o).getMessage() : "";
            if(((HttpException) o).code() == 401) {
                observable = Observable.error(new AssetOwlException(errorMessage, AssetOwlException.AUTH_INVALID_USERNAME_OR_PASSWORD));
            } else {
                observable = Observable.error(new AssetOwlException(errorMessage, AssetOwlException.NETWORK_ISSUE));
            }
        } else if(o instanceof SocketTimeoutException) {
            observable = Observable.error(new AssetOwlException("", AssetOwlException.NETWORK_TIME_OUT));
        } else if(o instanceof IOException) {
            observable = Observable.error(new AssetOwlException("", AssetOwlException.NETWORK_ISSUE));
        } else {
            observable = Observable.error((Throwable)o);
        }
        ((Throwable)o).printStackTrace();
        return (O) observable;
    }
}
