package com.agape.datacatalog;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;

/**
 * {@link android.view.View.OnClickListener} used to translate the product grid sheet downward on
 * the Y-axis when the navigation icon in the toolbar is pressed.
 */
public class NavigationIconClickListener implements View.OnClickListener {

    private final AnimatorSet animatorSet = new AnimatorSet();
    private Context context;
    private View sheet;
    private Interpolator interpolator;
    private int height;
    private int width;
    private boolean backdropShown = false;
    private Drawable openIcon;
    private Drawable closeIcon;

    public NavigationIconClickListener(Context context, View sheet) {
        this(context, sheet, null);
    }

    NavigationIconClickListener(Context context, View sheet, @Nullable Interpolator interpolator) {
        this(context, sheet, interpolator, null, null);
    }

    NavigationIconClickListener(
            Context context, View sheet, @Nullable Interpolator interpolator,
            @Nullable Drawable openIcon, @Nullable Drawable closeIcon) {
        this.context = context;
        this.sheet = sheet;
        this.interpolator = interpolator;
        this.openIcon = openIcon;
        this.closeIcon = closeIcon;

        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    @Override
    public void onClick(View view) {
        backdropShown = !backdropShown;

        // Cancel the existing animations
        animatorSet.removeAllListeners();
        animatorSet.end();
        animatorSet.cancel();

        updateIcon(view);

        final int translateY = height -
                context.getResources().getDimensionPixelSize(R.dimen.package_grid_reveal_height);
        final int translateX = width -
                context.getResources().getDimensionPixelSize(R.dimen.package_grid_reveal_width);

        ObjectAnimator animatorDown = ObjectAnimator.ofFloat(sheet, "translationY", backdropShown ? translateY : 0);
        animatorDown.setDuration(500);
        if (interpolator != null) {
            animatorDown.setInterpolator(interpolator);
        }
        animatorSet.play(animatorDown);
        animatorDown.start();

//        ObjectAnimator animatorRight = ObjectAnimator.ofFloat(sheet, "translationX", backdropShown ? translateX : 0);
//        animatorRight.setDuration(500);
//        if (interpolator != null) {
//            animatorRight.setInterpolator(interpolator);
//        }
//        animatorSet.play(animatorRight);
//        animatorRight.start();
    }

    private void updateIcon(View view) {
        if (openIcon != null && closeIcon != null) {
            if (!(view instanceof ImageView)) {
                throw new IllegalArgumentException("updateIcon() must be called on an ImageView");
            }
            if (backdropShown) {
                ((ImageView) view).setImageDrawable(closeIcon);
            } else {
                ((ImageView) view).setImageDrawable(openIcon);
            }
        }
    }
}
