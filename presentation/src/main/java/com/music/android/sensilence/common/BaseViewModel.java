package com.music.android.sensilence.common;

import androidx.lifecycle.ViewModel;

import io.reactivex.disposables.CompositeDisposable;

public abstract class BaseViewModel extends ViewModel {

   public CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
    }
}
