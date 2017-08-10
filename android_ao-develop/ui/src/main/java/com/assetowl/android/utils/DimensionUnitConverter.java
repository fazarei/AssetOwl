package com.assetowl.android.utils;

import android.content.res.Resources;

/**
 * Created by patrickyin on 24/5/17.
 */

public final class DimensionUnitConverter {
    public static int dpToPx(int dp)
    {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    public static int pxToDp(int px)
    {
        return (int) (px / Resources.getSystem().getDisplayMetrics().density);
    }
}
