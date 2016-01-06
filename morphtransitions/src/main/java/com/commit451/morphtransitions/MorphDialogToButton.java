package com.commit451.morphtransitions;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.ColorInt;
import android.transition.ChangeBounds;
import android.transition.TransitionValues;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

/**
 * A transition that morphs a rectangle into a circle, changing it's background color.
 */
@TargetApi(21)
public class MorphDialogToButton extends ChangeBounds {

    private static final String PROPERTY_COLOR = "plaid:rectMorph:color";
    private static final String PROPERTY_CORNER_RADIUS = "plaid:rectMorph:cornerRadius";
    private static final String[] TRANSITION_PROPERTIES = {
            PROPERTY_COLOR,
            PROPERTY_CORNER_RADIUS
    };
    private @ColorInt
    int endColor = Color.TRANSPARENT;

    private @ColorInt
    int startColor = Color.TRANSPARENT;

    public MorphDialogToButton(@ColorInt int endColor, @ColorInt int startColor) {
        super();
        setEndColor(endColor);
        setStartColor(startColor);
    }

    public MorphDialogToButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setEndColor(@ColorInt int endColor) {
        this.endColor = endColor;
    }

    public void setStartColor(@ColorInt int startColor) {
        this.startColor = startColor;
    }

    @Override
    public String[] getTransitionProperties() {
        return TRANSITION_PROPERTIES;
    }

    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        super.captureStartValues(transitionValues);
        final View view = transitionValues.view;
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            return;
        }
        transitionValues.values.put(PROPERTY_COLOR, startColor);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        super.captureEndValues(transitionValues);
        final View view = transitionValues.view;
        if (view.getWidth() <= 0 || view.getHeight() <= 0) {
            return;
        }
        transitionValues.values.put(PROPERTY_COLOR, endColor);
    }

    @Override
    public Animator createAnimator(final ViewGroup sceneRoot,
                                   TransitionValues startValues,
                                   TransitionValues endValues) {
        Animator changeBounds = super.createAnimator(sceneRoot, startValues, endValues);
        if (startValues == null || endValues == null || changeBounds == null) {
            return null;
        }

        Integer startColor = (Integer) startValues.values.get(PROPERTY_COLOR);
        Integer endColor = (Integer) endValues.values.get(PROPERTY_COLOR);

        if (startColor == null || endColor == null) {
            return null;
        }

        MorphDrawable background = new MorphDrawable(startColor, 0);
        endValues.view.setBackground(background);

        Animator color = ObjectAnimator.ofArgb(background, MorphDrawable.COLOR, endColor);

        // hide child views (offset down & fade_fast out)
        if (endValues.view instanceof ViewGroup) {
            ViewGroup vg = (ViewGroup) endValues.view;
            for (int i = 0; i < vg.getChildCount(); i++) {
                View v = vg.getChildAt(i);
                v.animate()
                        .alpha(0f)
                        .translationY(v.getHeight() / 3)
                        .setStartDelay(0L)
                        .setDuration(50L)
                        .setInterpolator(AnimationUtils.loadInterpolator(vg.getContext(),
                                android.R.interpolator.fast_out_linear_in))
                        .start();
            }
        }

        AnimatorSet transition = new AnimatorSet();
        transition.playTogether(changeBounds, color);
        transition.setDuration(300);
        transition.setInterpolator(AnimUtils.getMaterialInterpolator());
        return transition;
    }
}
