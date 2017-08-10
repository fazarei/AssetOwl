package com.assetowl.mvp.presenter;

/**
 * Created by jamespott on 7/2/17.
 */

public abstract class BasePresenter<T> implements Presenter<T> {
    private T view;


    @Override
    public void reload() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        detachView();
    }

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    public T getView() {
        return view;
    }

    public void showError(String message) {

    }
}
