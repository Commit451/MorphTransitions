package com.commit451.morphtransitions.sample;

import android.content.Context;
import android.util.TypedValue;

/**
 * Simple util to retrieve attribute colors
 */
public class ColorUtil {

    private static TypedValue sTypedValue = new TypedValue();

    public static int getThemeAttrColor(Context context, int attributeColor) {
        context.getTheme().resolveAttribute(attributeColor, sTypedValue, true);
        return sTypedValue.data;
    }
}
