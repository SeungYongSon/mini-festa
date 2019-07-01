package com.kkori.mini_festa.domain.usecase;

import com.kkori.mini_festa.domain.base.UseCase;
import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.domain.entity.Service;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;

public class SaveLocalEventUseCase extends UseCase<Event, DisposableCompletableObserver> {

    public SaveLocalEventUseCase(CompositeDisposable disposable, Service service) {
        super(disposable, service);
    }

    @Override
    protected DisposableCompletableObserver createSubscriber(Event data, DisposableCompletableObserver disposableObserver) {
        Completable completable = service.saveLocalEvent(data);

        return (DisposableCompletableObserver) completable.subscribeWith((CompletableObserver) disposableObserver);
    }

}
