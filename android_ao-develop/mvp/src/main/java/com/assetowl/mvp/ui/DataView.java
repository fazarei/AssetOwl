package com.assetowl.mvp.ui;

/**
 * Render view model data on screen
 *
 * Created by jamespott on 31/1/17.
 */

public interface DataView<M> {
    void render(M viewModel);
}
