package com.tubitv.ui.demo;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by stoyan on 5/23/17.
 */
public class DemoObservable extends BaseObservable{
    private boolean play = true;

    @Bindable
    public boolean isPlay() {
        return play;
    }

    public void setPlay(boolean play) {
        this.play = play;
        notifyPropertyChanged(BR.play);
    }
}