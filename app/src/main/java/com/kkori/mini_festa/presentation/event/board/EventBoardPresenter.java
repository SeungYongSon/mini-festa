package com.kkori.mini_festa.presentation.event.board;

import android.util.Log;

import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.domain.usecase.GetLocalEventListUseCase;
import com.kkori.mini_festa.domain.usecase.GetRemoteEventListUseCase;
import com.kkori.mini_festa.presentation.base.BasePresenter;
import com.kkori.mini_festa.presentation.mapper.EventModelMapper;
import com.kkori.mini_festa.presentation.model.EventModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.subscribers.DisposableSubscriber;

public class EventBoardPresenter extends BasePresenter<EventBoardContract.View> implements EventBoardContract.Presenter {

    private GetRemoteEventListUseCase getRemoteEventListUseCase;
    private GetLocalEventListUseCase getLocalEventListUseCase;
    private EventModelMapper eventModelMapper;

    private int pageIndex = 1;
    private boolean isLoadingEvent = false;

    @Inject
    EventBoardPresenter(EventBoardFragment eventFragment,
                        GetRemoteEventListUseCase getRemoteEventListUseCase,
                        GetLocalEventListUseCase getLocalEventListUseCase,
                        EventModelMapper eventModelMapper) {

        this.getRemoteEventListUseCase = getRemoteEventListUseCase;
        this.getLocalEventListUseCase = getLocalEventListUseCase;
        this.eventModelMapper = eventModelMapper;

        createView(eventFragment);
        eventFragment.setPresenter(this);
    }

    @Override
    public void loadEvent() {
        if (!isLoadingEvent) {
            isLoadingEvent = true;

            if (getView().checkNetWork()) {
                loadRemoteEvents();
            } else {
                loadLocalEvents();
            }
        }
    }

    private void loadRemoteEvents() {
        if (pageIndex > 1) {
            getView().showMoreLoadingProgress();
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
                hideProgress();
                if (!eventModels.isEmpty()) {
                    getView().addEventsToAdapter(eventModels);
                    pageIndex++;
                }
            }

            @Override
            public void onError(Throwable t) {
                getView().showToast("행사들을 불러올 수 없습니다!");
                Log.e("Error!!", t.getLocalizedMessage());

                hideProgress();
            }
        });
    }

    private void loadLocalEvents() {
        getLocalEventListUseCase.execute(new DisposableSubscriber<List<Event>>() {
            ArrayList<EventModel> eventModels = new ArrayList<>();

            @Override
            public void onNext(List<Event> eventList) {
                for (Event event : eventList) {
                    eventModels.add(eventModelMapper.mapFrom(event));
                }
                onComplete();
            }

            @Override
            public void onComplete() {
                getView().addEventsToAdapter(eventModels);
                getView().hideProgress(false);

                getView().showToast("네트워크 상태를 확인 해주세요!");
            }

            @Override
            public void onError(Throwable t) {
                getView().showToast("행사들을 불러올 수 없습니다!");
                Log.e("Error!!", t.getLocalizedMessage());
            }
        });
    }

    private void hideProgress() {
        if (pageIndex > 1) getView().hideProgress(true);
        else getView().hideProgress(false);

        isLoadingEvent = false;
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

}
