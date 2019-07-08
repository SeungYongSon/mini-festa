package com.kkori.mini_festa.domain.base;

import com.kkori.mini_festa.domain.entity.Service;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class UseCase<P, D> {

    private CompositeDisposable disposable;
    protected Service service;

    public UseCase(CompositeDisposable disposable, Service service) {
        this.disposable = disposable;
        this.service = service;
    }

    public void execute(P data, D disposableObserver) {
        disposable.add((Disposable) createSubscriber(data, disposableObserver));
    }

    public void dispose() {
        disposable.dispose();
    }

    protected abstract D createSubscriber(P data, D disposableObserver);

}
