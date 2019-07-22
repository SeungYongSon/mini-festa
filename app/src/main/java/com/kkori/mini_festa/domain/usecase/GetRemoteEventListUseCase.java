package com.kkori.mini_festa.domain.usecase;

import com.kkori.mini_festa.domain.base.UseCase;
import com.kkori.mini_festa.domain.entity.event.EventService;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subscribers.DisposableSubscriber;

public class GetRemoteEventListUseCase extends UseCase<Integer, DisposableSubscriber> {

    private EventService service;

    public GetRemoteEventListUseCase(EventService service) {
        this.service = service;
    }

    @Override
    protected DisposableSubscriber createSubscriber(Integer data, DisposableSubscriber disposableObserver) {
        Flowable flowable = service.getRemoteEventList(data, 24);

        return (DisposableSubscriber) flowable.subscribeWith(disposableObserver);
    }
}
