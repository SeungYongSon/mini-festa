package com.kkori.mini_festa.presentation.di.module.event;

import com.kkori.mini_festa.presentation.di.scope.EventFragmentScope;
import com.kkori.mini_festa.presentation.event.detail.EventDetailContract;
import com.kkori.mini_festa.presentation.event.detail.EventDetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class EventDetailModule {

    @Provides
    @EventFragmentScope
    EventDetailContract.Presenter provideEventDetailContractPresenter(EventDetailPresenter eventDetailPresenter) {
        return eventDetailPresenter;
    }

    @Provides
    @EventFragmentScope
    EventDetailContract.View provideEventDetailContractView(EventDetailPresenter eventDetailPresenter) {
        return eventDetailPresenter.getView();
    }

}
