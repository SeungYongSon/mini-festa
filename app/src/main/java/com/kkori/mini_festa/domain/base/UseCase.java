package com.kkori.mini_festa.domain.base;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;

public abstract class UseCase<T, E> {

    private CompositeDisposable disposable;

    UseCase(CompositeDisposable disposable) {
        this.disposable = disposable;
    }

    public void execute(E data, DisposableSubscriber<T> disposableObserver) {
        Flowable observable = createFlowable(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        DisposableSubscriber observer = (DisposableSubscriber) observable.subscribeWith(disposableObserver);

        disposable.add(observer);
    }

    public void dispose() {
        disposable.dispose();
    }

    abstract Flowable<T> createFlowable(E data);
}
