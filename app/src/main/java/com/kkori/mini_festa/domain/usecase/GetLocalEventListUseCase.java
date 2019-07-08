package com.kkori.mini_festa.domain.usecase;

import com.kkori.mini_festa.domain.base.UseCase;
import com.kkori.mini_festa.domain.entity.Service;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.DisposableSubscriber;

public class GetLocalEventListUseCase extends UseCase<Void, DisposableSubscriber> {

    public GetLocalEventListUseCase(CompositeDisposable disposable, Service service) {
        super(disposable, service);
    }

    @Override
    protected DisposableSubscriber createSubscriber(Void data, DisposableSubscriber disposableObserver) {
        Flowable flowable = service.getLocalEventList();

        return (DisposableSubscriber) flowable.subscribeWith(disposableObserver);
    }

}
