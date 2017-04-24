package com.tubitv.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * Created by stoyan on 4/24/17.
 */
public class TubiLoadingView extends android.support.v7.widget.AppCompatImageView {

    public TubiLoadingView(Context context) {
        super(context);
    }

    public TubiLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TubiLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setImageDrawable(getResources().getDrawable(R.drawable.ic_tubi_tv_loading_spinner));

//        if (isInEditMode()) {
//            return;
//        }

        Animation rotateAnim = AnimationUtils.loadAnimation(getContext(), R.anim.tubi_tv_loading_spinner_rotate);
//        rotateAnim.setRepeatMode(Animation.INFINITE);
        startAnimation(rotateAnim);
    }
}
