package com.kkori.mini_festa.presentation.event.detail;

import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.domain.usecase.GetEventThroughIdUseCase;
import com.kkori.mini_festa.domain.usecase.SetFavoriteEventUseCase;
import com.kkori.mini_festa.presentation.base.BasePresenter;
import com.kkori.mini_festa.presentation.mapper.EventModelMapper;
import com.kkori.mini_festa.presentation.model.EventModel;

import javax.inject.Inject;

import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableMaybeObserver;

public class EventDetailPresenter extends BasePresenter<EventDetailContract.View> implements EventDetailContract.Presenter {

    private GetEventThroughIdUseCase getEventThroughIdUseCase;
    private SetFavoriteEventUseCase setFavoriteEventUsecase;
    private EventModelMapper eventModelMapper;

    private EventModel eventModel;

    @Inject
    EventDetailPresenter(EventDetailFragment eventDetailFragment,
                         GetEventThroughIdUseCase getEventThroughIdUseCase,
                         SetFavoriteEventUseCase setFavoriteEventUsecase,
                         EventModelMapper eventModelMapper) {
        this.getEventThroughIdUseCase = getEventThroughIdUseCase;
        this.setFavoriteEventUsecase = setFavoriteEventUsecase;
        this.eventModelMapper = eventModelMapper;
        createView(eventDetailFragment);
        eventDetailFragment.setPresenter(this);
    }

    @Override
    public void initEvent(int id) {
        getEventThroughIdUseCase.execute(id, new DisposableMaybeObserver<Event>() {
            @Override
            public void onSuccess(Event event) {
                eventModel = eventModelMapper.mapFrom(event);
                getView().initUI(eventModel);
            }

            @Override
            public void onError(Throwable e) {
                getView().showToast("에러~~");
            }

            @Override
            public void onComplete() {

            }
        });
    }

    @Override
    public void setFavoriteEvent() {
        setFavoriteEventUsecase.execute(
                new SetFavoriteEventUseCase.Param(eventModel.getEventId(), !eventModel.isFavorite()),
                new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        eventModel.setIsFavorite(!eventModel.isFavorite());
                        getView().setLikeBtnText(eventModel.isFavorite());
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showToast("좋아요 에러~~");
                    }
                });
    }
}
