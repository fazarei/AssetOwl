package com.assetowl.mvp.presenter;

/**
 * Created by jamespott on 4/11/16.
 */

public interface Presenter<V> {

    void start();

    void reload();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onResume() method.
     */
    void resume();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onPause() method.
     */
    void pause();

    /**
     * Method that control the lifecycle of the view. It should be called in the view's
     * (Activity or Fragment) onDestroy() method.
     */
    void destroy();

    void attachView(V view);

    void detachView();

}
