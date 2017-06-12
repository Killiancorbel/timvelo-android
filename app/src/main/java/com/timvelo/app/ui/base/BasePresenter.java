package com.timvelo.app.ui.base;

import android.support.annotation.NonNull;

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by admin on 06/06/2017.
 */

public class BasePresenter<V extends MvpView> extends MvpBasePresenter<V> {

    protected CompositeDisposable disposables = new CompositeDisposable();

    @NonNull
    public V getViewOrThrow() {
        final V view = getView();
        if (null == view) throw new IllegalStateException("view not attached");
        return view;
    }

    @Override
    public void detachView(boolean retainInstance) {
        disposables.clear();
    }
}