package com.kkori.mini_festa.domain.usecase;

import com.kkori.mini_festa.domain.base.UseCase;
import com.kkori.mini_festa.domain.entity.event.EventService;

import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;

public class GetFavoriteEventListUseCase extends UseCase<Void, DisposableSingleObserver> {

    private EventService service;

    public GetFavoriteEventListUseCase(EventService service) {
        this.service = service;
    }

    @Override
    protected DisposableSingleObserver createSubscriber(Void data, DisposableSingleObserver disposableObserver) {
        Single single = service.getFavoriteEventList();

        return (DisposableSingleObserver) single.subscribeWith(disposableObserver);
    }
}
