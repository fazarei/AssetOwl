package com.assetowl.android.ui.test;

import cucumber.api.CucumberOptions;

/**
 * Created by patrickyin on 2/3/17.
 */

@CucumberOptions(features = "features",
        glue = {"com.assetowl.android.ui.cucumber.steps"})
public class RunTest {
}
