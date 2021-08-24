package com.music.android.sensilence.common;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

    @Inject
    public CompositeDisposable compositeDisposable;

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
