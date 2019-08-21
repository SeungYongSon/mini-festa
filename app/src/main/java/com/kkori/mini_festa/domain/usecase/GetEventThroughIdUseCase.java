package com.kkori.mini_festa.domain.usecase;

import com.kkori.mini_festa.domain.base.UseCase;
import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.domain.entity.event.EventService;

import io.reactivex.Maybe;
import io.reactivex.observers.DisposableMaybeObserver;

public class GetEventThroughIdUseCase extends UseCase<Integer, DisposableMaybeObserver<Event>> {

    private EventService service;

    public GetEventThroughIdUseCase(EventService service) {
        this.service = service;
    }

    @Override
    protected DisposableMaybeObserver<Event> createSubscriber(Integer data, DisposableMaybeObserver<Event> disposableObserver) {
        Maybe<Event> maybe = service.getEventThroughId(data);

        return maybe.subscribeWith(disposableObserver);
    }
}
