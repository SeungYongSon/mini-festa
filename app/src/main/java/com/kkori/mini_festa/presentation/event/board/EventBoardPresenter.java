package com.kkori.mini_festa.presentation.event.board;

import android.util.Log;

import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.domain.usecase.GetFavoriteEventListUseCase;
import com.kkori.mini_festa.domain.usecase.GetLocalEventListUseCase;
import com.kkori.mini_festa.domain.usecase.GetRemoteEventListUseCase;
import com.kkori.mini_festa.presentation.base.BasePresenter;
import com.kkori.mini_festa.presentation.mapper.EventModelMapper;
import com.kkori.mini_festa.presentation.model.EventModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.subscribers.DisposableSubscriber;

public class EventBoardPresenter extends BasePresenter<EventBoardContract.View> implements EventBoardContract.Presenter {

    private GetRemoteEventListUseCase getRemoteEventListUseCase;
    private GetLocalEventListUseCase getLocalEventListUseCase;
    private GetFavoriteEventListUseCase getFavoriteEventListUsecase;
    private EventModelMapper eventModelMapper;

    private boolean isFirstLoading = true;
    private boolean isCanLoadingEvent = true;
    private int pageIndex = 1;

    @Inject
    EventBoardPresenter(EventBoardFragment eventFragment,
                        GetRemoteEventListUseCase getRemoteEventListUseCase,
                        GetLocalEventListUseCase getLocalEventListUseCase,
                        GetFavoriteEventListUseCase getFavoriteEventListUsecase,
                        EventModelMapper eventModelMapper) {

        this.getRemoteEventListUseCase = getRemoteEventListUseCase;
        this.getLocalEventListUseCase = getLocalEventListUseCase;
        this.getFavoriteEventListUsecase = getFavoriteEventListUsecase;
        this.eventModelMapper = eventModelMapper;

        createView(eventFragment);
        eventFragment.setPresenter(this);
    }

    @Override
    public void initEvent() {
        if (!isFirstLoading) {
            getView().showIntroduce(false);
            getView().showLoadingProgress(false);
            getView().clearEventList();

            pageIndex = 1;
        }

        if (getView().checkNetWork()) {
            loadRemoteEvents();
        } else {
            loadLocalEvents();
        }
    }

    @Override
    public void loadMoreEvent() {
        if (isCanLoadingEvent) {
            isCanLoadingEvent = false;

            if (getView().checkNetWork()) {
                loadRemoteEvents();
            } else {
                loadLocalEvents();
            }
        }
    }

    @Override
    public void loadFavoriteEvent() {
        getView().showIntroduce(true);
        getView().clearEventList();

        hideLoadingProgress();
        getView().showLoadingProgress(false);

        getRemoteEventListUseCase.clear();
        getLocalEventListUseCase.clear();

        getFavoriteEventListUsecase.execute(new DisposableSingleObserver<List<Event>>() {

            @Override
            public void onSuccess(List<Event> eventList) {
                for (Event event : eventList) {
                    getView().addEventToAdapter(eventModelMapper.mapFrom(event));
                }

                isFirstLoading = false;
                isCanLoadingEvent = false;

                getView().hideLoadingProgress(false);
            }

            @Override
            public void onError(Throwable t) {
                isFirstLoading = false;
                isCanLoadingEvent = false;

                getView().hideLoadingProgress(false);

                getView().showToast("행사들을 불러올 수 없습니다!");
                Log.e("Error!!", t.getLocalizedMessage());
            }

        });
    }

    @Override
    public void createView(EventBoardContract.View view) {
        super.createView(view);
    }

    @Override
    public void destroyView() {
        super.destroyView();
        getRemoteEventListUseCase.clear();
        getLocalEventListUseCase.clear();
    }

    private void loadRemoteEvents() {
        if (pageIndex > 1) {
            getView().showLoadingProgress(true);
        }

        getRemoteEventListUseCase.execute(pageIndex, new DisposableSubscriber<List<Event>>() {
            ArrayList<EventModel> eventModels = new ArrayList<>();

            @Override
            public void onNext(List<Event> eventList) {
                for (Event event : eventList) {
                    eventModels.add(eventModelMapper.mapFrom(event));
                }
            }

            @Override
            public void onComplete() {
                hideLoadingProgress();

                if (!eventModels.isEmpty()) {
                    getView().addEventToAdapter(eventModels);
                    pageIndex++;
                }

                isFirstLoading = false;
                isCanLoadingEvent = true;
            }

            @Override
            public void onError(Throwable t) {
                getView().showToast("행사들을 불러올 수 없습니다!");
                Log.e("Error!!", t.getLocalizedMessage());

                hideLoadingProgress();

                isFirstLoading = false;
                isCanLoadingEvent = true;
            }
        });
    }

    private void loadLocalEvents() {
        getLocalEventListUseCase.execute(new DisposableSingleObserver<List<Event>>() {

            @Override
            public void onSuccess(List<Event> eventList) {
                for (Event event : eventList) {
                    getView().addEventToAdapter(eventModelMapper.mapFrom(event));
                }

                getView().hideLoadingProgress(false);
                getView().showToast("네트워크 상태를 확인 해주세요!");
            }

            @Override
            public void onError(Throwable t) {
                getView().showToast("행사들을 불러올 수 없습니다!");
                Log.e("Error!!", t.getLocalizedMessage());
            }

        });
    }

    private void hideLoadingProgress() {
        if (pageIndex > 1) getView().hideLoadingProgress(true);
        else getView().hideLoadingProgress(false);

        isCanLoadingEvent = true;
    }
}
