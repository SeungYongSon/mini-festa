package com.kkori.mini_festa.domain.usecase;

import com.kkori.mini_festa.domain.base.UseCase;
import com.kkori.mini_festa.domain.entity.Service;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.DisposableSubscriber;

public class GetRemoteEventListUseCase extends UseCase<Integer, DisposableSubscriber> {

    public GetRemoteEventListUseCase(CompositeDisposable disposable, Service service) {
        super(disposable, service);
    }

    @Override
    protected DisposableSubscriber createSubscriber(Integer data, DisposableSubscriber disposableObserver) {
        Flowable flowable = service.getRemoteEventList(data, 24);

        return (DisposableSubscriber) flowable.subscribeWith(disposableObserver);
    }
}
