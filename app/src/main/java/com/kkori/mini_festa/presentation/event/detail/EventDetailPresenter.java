package com.kkori.mini_festa.presentation.event.detail;

import com.kkori.mini_festa.presentation.base.BasePresenter;

import javax.inject.Inject;

public class EventDetailPresenter extends BasePresenter<EventDetailContract.View> implements EventDetailContract.Presenter {

    @Inject
    EventDetailPresenter(EventDetailFragment eventDetailFragment) {
        createView(eventDetailFragment);
        eventDetailFragment.setPresenter(this);
    }

}
