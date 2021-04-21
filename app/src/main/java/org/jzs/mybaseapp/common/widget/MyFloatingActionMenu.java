package org.jzs.mybaseapp.common.widget;

import android.content.Context;
import android.os.Build;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;

import com.ddz.floatingactionbutton.FloatingActionMenu;

import org.jzs.zhihudaily.R;

/**
 * Created by Jzs on 2017/9/20 0020.
 */

public class MyFloatingActionMenu extends FloatingActionMenu {

    private static final Interpolator INTERPOLATOR = new FastOutSlowInInterpolator();
    private boolean mIsShow = true;

    public MyFloatingActionMenu(Context context) {
        this(context, null);
    }

    public MyFloatingActionMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyFloatingActionMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // Same animation that FloatingActionButton.Behavior uses to hide the FAB when the AppBarLayout exits
    public void animateOut() {
        if (mIsShow == false) {
            return;
        }
        mIsShow = false;
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.push_right_out);
        anim.setInterpolator(INTERPOLATOR);
        anim.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationStart(Animation animation) {
            }

            public void onAnimationEnd(Animation animation) {
                setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(final Animation animation) {
            }
        });
        startAnimation(anim);
    }

    // Same animation that FloatingActionButton.Behavior uses to show the FAB when the AppBarLayout enters
    public void animateIn() {
        if (mIsShow == true) {
            return;
        }
        mIsShow = true;
        setVisibility(View.VISIBLE);
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.push_right_in);
        anim.setInterpolator(INTERPOLATOR);
        startAnimation(anim);
    }
}
