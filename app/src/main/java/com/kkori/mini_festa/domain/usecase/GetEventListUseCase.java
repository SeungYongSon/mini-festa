package com.kkori.mini_festa.domain.usecase;

import com.kkori.mini_festa.domain.base.UseCase;
import com.kkori.mini_festa.domain.entity.Service;

import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.subscribers.DisposableSubscriber;
import kotlin.Pair;

public class GetEventListUseCase extends UseCase<Pair<Integer, Integer>, DisposableSubscriber> {

    public GetEventListUseCase(CompositeDisposable disposable, Service service) {
        super(disposable, service);
    }

    @Override
    protected DisposableSubscriber createSubscriber(Pair<Integer, Integer> data, DisposableSubscriber disposableObserver) {
        Flowable flowable = service.getEventList(data.getFirst(), data.getSecond());

        return (DisposableSubscriber) flowable.subscribeWith(disposableObserver);
    }
}
