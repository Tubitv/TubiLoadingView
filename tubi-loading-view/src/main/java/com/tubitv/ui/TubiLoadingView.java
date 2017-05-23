package com.tubitv.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.databinding.BindingAdapter;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

/**
 * A simple rotating spinner with custom attributes to set duration of the rotation
 * <p>
 * Created by stoyan on 4/24/17.
 */
public class TubiLoadingView extends android.support.v7.widget.AppCompatImageView {
    /**
     * The default duration of the rotation animation {@link #mRotateAnim}
     */
    private static final int DEFAULT_ROTATION_DURATION_MS = 1000;

    /**
     * The duration of the the rotation animation {@link #mRotateAnim},
     *
     * @see #DEFAULT_ROTATION_DURATION_MS
     */
    private int mRotationDuration = DEFAULT_ROTATION_DURATION_MS;

    /**
     * The rotation animation that we apply to this view
     */
    private Animation mRotateAnim;

    /**
     * The state of whether this view. True if this is animating and visible
     */
    private boolean mIsRunning;

    public TubiLoadingView(Context context) {
        super(context);
        init(null);
    }

    public TubiLoadingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public TubiLoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * Initialize all of the images and animations as well as apply attributes if set
     *
     * @param attrs
     */
    private void init(@Nullable AttributeSet attrs) {
        setImageDrawable(getResources().getDrawable(R.drawable.ic_tubi_tv_loading_spinner));

        if (isInEditMode()) {
            return;
        }

        if (attrs != null) {
            TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs,
                    R.styleable.TubiLoadingView, 0, 0);
            try {
                mRotationDuration = a.getInt(R.styleable.TubiLoadingView_rotation_duration_ms, DEFAULT_ROTATION_DURATION_MS);
            } finally {
                a.recycle();
            }
        }

        mRotateAnim = AnimationUtils.loadAnimation(getContext(), R.anim.tubi_tv_loading_spinner_rotate);
        start();
    }

    /**
     * Binding method for setting a observable object through databinding on this view allowing
     * it to automatically change the play state of this view from {@link #start()} and {@link #stop()}
     *
     * @param loadingView The view to toggle
     * @param start       True if we want the rotating spinner, false otherwise
     */
    @BindingAdapter("bind:tubi_loading_toggle")
    public static void onTubiLoadingViewToggle(TubiLoadingView loadingView, boolean start) {
        if (start) {
            loadingView.start();
        } else {
            loadingView.stop();
        }
    }

    /**
     * Toggles the visibility and animation.
     */
    public void toggle() {
        if (mIsRunning) {
            stop();
        } else {
            start();
        }
    }

    /**
     * Stops the animation and makes the view gone
     */
    public void stop() {
        if (!mIsRunning) {//already stopped
            return;
        }
        clearAnimation();
        setVisibility(View.GONE);
        mIsRunning = false;
    }

    /**
     * Starts the animation and makes the view visible
     */
    public void start() {
        if (mIsRunning) {//already running
            return;
        }
        mRotateAnim.setDuration(mRotationDuration);
        setVisibility(View.VISIBLE);
        startAnimation(mRotateAnim);
        mIsRunning = true;
    }
}
