package com.kkori.mini_festa.domain.usecase;

import com.kkori.mini_festa.domain.base.UseCase;
import com.kkori.mini_festa.domain.entity.event.EventService;

import io.reactivex.Flowable;
import io.reactivex.subscribers.DisposableSubscriber;

public class GetLocalEventListUseCase extends UseCase<Void, DisposableSubscriber> {

    private EventService service;

    public GetLocalEventListUseCase(EventService service) {
        this.service = service;
    }

    @Override
    protected DisposableSubscriber createSubscriber(Void data, DisposableSubscriber disposableObserver) {
        Flowable flowable = service.getLocalEventList();

        return (DisposableSubscriber) flowable.subscribeWith(disposableObserver);
    }

}
