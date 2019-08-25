package com.kkori.mini_festa.domain.usecase;

import com.kkori.mini_festa.domain.base.UseCase;
import com.kkori.mini_festa.domain.entity.event.EventService;

import io.reactivex.Completable;
import io.reactivex.observers.DisposableCompletableObserver;

public class SetFavoriteEventUseCase extends UseCase<SetFavoriteEventUseCase.Param, DisposableCompletableObserver> {

    private EventService service;

    public SetFavoriteEventUseCase(EventService service) {
        this.service = service;
    }

    @Override
    protected DisposableCompletableObserver createSubscriber(Param data, DisposableCompletableObserver disposableObserver) {
        Completable completable = service.setFavoriteEvent(data.id, data.isFavorite);

        return completable.subscribeWith(disposableObserver);
    }

    public static class Param {
        private int id;
        private boolean isFavorite;

        public Param(int id, boolean isFavorite) {
            this.id = id;
            this.isFavorite = isFavorite;
        }
    }

}
