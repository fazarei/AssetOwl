package com.assetowl.domain.featureToggle.repository;

import com.assetowl.domain.featureToggle.model.FeatureToggleInfo;
import com.assetowl.domain.login.repository.LoggedInDescriptor;
import com.assetowl.domain.login.repository.LoggedInDescriptor.LoggedInDef;

import java.util.List;

import dagger.Provides;
import rx.Observable;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

/**
 * Created by farzanehzarei on 17/5/17.
 */

public interface FeatureToggleRepository {
    Observable<List<? extends FeatureToggleInfo>> featureToggle();
}
