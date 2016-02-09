package com.commit451.morphtransitions;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.transition.ArcMotion;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

/**
 * Handles the hard bits of morphing so that you do not have to.
 */
public class MorphManager {

    /**
     * Morphs from a FAB to a dialog. Be sure to call after view hierarchy has been set up (typically at the end of {@link android.app.Activity#onCreate(Bundle)}
     * @param root the root view, which should also have the {@link View#getTransitionName()} set
     * @param fabColor the fab color
     * @param dialogColor the dialog color
     * @param dialogCornerRadius the corner radius of the dialog
     */
    public static void morph(Activity activity, View root, int fabColor, int dialogColor, int dialogCornerRadius) {
        if (root == null) {
            throw new IllegalStateException("Cannot pass an empty view");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setupSharedElementTransitionsFab(activity, root,
                    fabColor,
                    dialogColor,
                    dialogCornerRadius);
        }
    }

    public static void morph(Activity activity, View root, int buttonColor, int dialogColor) {
        if (root == null) {
            throw new IllegalStateException("Cannot pass an empty view");
        }
        if (Build.VERSION.SDK_INT >= 21) {
            setupSharedElementTransitionsButton(activity,
                    root,
                    buttonColor,
                    dialogColor);
        }
    }

    @TargetApi(21)
    private static void setupSharedElementTransitionsFab(@NonNull Activity activity,
                                                 @Nullable View target,
                                                 int fabColor,
                                                 int dialogColor,
                                                 int dialogCornerRadius) {
        ArcMotion arcMotion = new ArcMotion();
        arcMotion.setMinimumHorizontalAngle(50f);
        arcMotion.setMinimumVerticalAngle(50f);
        Interpolator easeInOut = AnimationUtils.loadInterpolator(activity, android.R.interpolator.fast_out_slow_in);
        MorphFabToDialog sharedEnter = new MorphFabToDialog(fabColor, dialogColor, dialogCornerRadius);
        sharedEnter.setPathMotion(arcMotion);
        sharedEnter.setInterpolator(easeInOut);
        MorphDialogToFab sharedReturn = new MorphDialogToFab(dialogColor, fabColor, dialogCornerRadius);
        sharedReturn.setPathMotion(arcMotion);
        sharedReturn.setInterpolator(easeInOut);
        if (target != null) {
            sharedEnter.addTarget(target);
            sharedReturn.addTarget(target);
        }
        activity.getWindow().setSharedElementEnterTransition(sharedEnter);
        activity.getWindow().setSharedElementReturnTransition(sharedReturn);
    }

    @TargetApi(21)
    private static void setupSharedElementTransitionsButton(@NonNull Activity activity,
                                                         @Nullable View target,
                                                         @ColorInt int buttonColor,
                                                         @ColorInt int dialogColor) {
        ArcMotion arcMotion = new ArcMotion();
        arcMotion.setMinimumHorizontalAngle(50f);
        arcMotion.setMinimumVerticalAngle(50f);
        Interpolator easeInOut = AnimationUtils.loadInterpolator(activity, android.R.interpolator.fast_out_slow_in);
        MorphButtonToDialog sharedEnter = new MorphButtonToDialog(buttonColor, dialogColor);
        sharedEnter.setPathMotion(arcMotion);
        sharedEnter.setInterpolator(easeInOut);
        MorphDialogToButton sharedReturn = new MorphDialogToButton(buttonColor, dialogColor);
        sharedReturn.setPathMotion(arcMotion);
        sharedReturn.setInterpolator(easeInOut);
        if (target != null) {
            sharedEnter.addTarget(target);
            sharedReturn.addTarget(target);
        }
        activity.getWindow().setSharedElementEnterTransition(sharedEnter);
        activity.getWindow().setSharedElementReturnTransition(sharedReturn);
    }
}
