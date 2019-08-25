package com.kkori.mini_festa.domain.usecase;

import com.kkori.mini_festa.domain.base.UseCase;
import com.kkori.mini_festa.domain.entity.event.EventService;

import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;

public class GetLocalEventListUseCase extends UseCase<Void, DisposableSingleObserver> {

    private EventService service;

    public GetLocalEventListUseCase(EventService service) {
        this.service = service;
    }

    @Override
    protected DisposableSingleObserver createSubscriber(Void data, DisposableSingleObserver disposableObserver) {
        Single single = service.getLocalEventList();

        return (DisposableSingleObserver) single.subscribeWith(disposableObserver);
    }
}
