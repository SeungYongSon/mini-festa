package com.kkori.mini_festa.domain.base;

import io.reactivex.disposables.Disposable;

public abstract class UseCase<P, D> {

    private Disposable disposable;

    public void execute(P data, D disposableObserver) {
        disposable = (Disposable) createSubscriber(data, disposableObserver);
    }

    public void clear() {
        if (disposable != null) {
            disposable.dispose();
        }
    }

    protected abstract D createSubscriber(P data, D disposableObserver);

}
