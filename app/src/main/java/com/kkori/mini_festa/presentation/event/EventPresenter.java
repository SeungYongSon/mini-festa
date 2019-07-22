package com.kkori.mini_festa.presentation.event;

import android.util.Log;

import com.kkori.mini_festa.domain.entity.Event;
import com.kkori.mini_festa.domain.usecase.GetLocalEventListUseCase;
import com.kkori.mini_festa.domain.usecase.GetRemoteEventListUseCase;
import com.kkori.mini_festa.presentation.base.BasePresenter;
import com.kkori.mini_festa.presentation.mapper.EventModelMapper;
import com.kkori.mini_festa.presentation.model.EventModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.subscribers.DisposableSubscriber;

public class EventPresenter extends BasePresenter<EventContract.View> implements EventContract.Presenter {

    private GetRemoteEventListUseCase getRemoteEventListUseCase;
    private GetLocalEventListUseCase getLocalEventListUseCase;
    private EventModelMapper eventModelMapper;

    public EventPresenter(GetRemoteEventListUseCase getRemoteEventListUseCase,
                          GetLocalEventListUseCase getLocalEventListUseCase,
                          EventModelMapper eventModelMapper) {
        this.getRemoteEventListUseCase = getRemoteEventListUseCase;
        this.getLocalEventListUseCase = getLocalEventListUseCase;
        this.eventModelMapper = eventModelMapper;
    }

    @Override
    public void initEvent() {
        if (getView().checkNetWork()) {
            loadRemoteEvents();
        } else {
            loadLocalEvents();
        }
    }

    private void loadRemoteEvents() {
        getRemoteEventListUseCase.execute(1, new DisposableSubscriber<List<Event>>() {
            ArrayList<EventModel> eventModels = new ArrayList<>();

            @Override
            public void onNext(List<Event> eventList) {
                for (Event event : eventList) {
                    eventModels.add(eventModelMapper.mapFrom(event));
                }
            }

            @Override
            public void onComplete() {
                getView().addEventsToAdapter(eventModels);
                getView().hideProgress();
            }

            @Override
            public void onError(Throwable t) {
                getView().showToast("행사들을 불러올 수 없습니다!");

                Log.e("Error!!", t.getLocalizedMessage());
            }
        });
    }

    private void loadLocalEvents() {
        getLocalEventListUseCase.execute(null, new DisposableSubscriber<List<Event>>() {
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
                getView().hideProgress();

                getView().showToast("네트워크 상태를 확인 해주세요!");
            }

            @Override
            public void onError(Throwable t) {
                getView().showToast("행사들을 불러올 수 없습니다!");
                Log.e("Error!!", t.getLocalizedMessage());
            }
        });
    }


    @Override
    public void createView(EventContract.View view) {
        super.createView(view);
    }

    @Override
    public void destroyView() {
        super.destroyView();
        getRemoteEventListUseCase.clear();
        getLocalEventListUseCase.clear();
    }

}
