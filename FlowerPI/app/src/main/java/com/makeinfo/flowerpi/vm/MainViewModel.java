package com.makeinfo.flowerpi.vm;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

import com.makeinfo.flowerpi.BR;

/**
 * Created by Andy on 1/25/2016.
 */
public class MainViewModel extends BaseObservable {
    private String text;
    private boolean pb;

    @Bindable
    public String getText() {
        return text;
    }

    @Bindable
    public boolean isPb() {
        return pb;
    }

    public void setPb(boolean pb) {
        this.pb = pb;
        notifyPropertyChanged(BR.pb);
    }

    public void setText(String text) {
        this.text = text;
        notifyPropertyChanged(BR.text);
    }
}
