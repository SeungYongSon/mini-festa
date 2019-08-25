package com.kkori.mini_festa.presentation.di.module.event;

import com.kkori.mini_festa.domain.entity.event.EventService;
import com.kkori.mini_festa.domain.usecase.GetEventThroughIdUseCase;
import com.kkori.mini_festa.domain.usecase.SetFavoriteEventUseCase;
import com.kkori.mini_festa.presentation.di.scope.EventFragmentScope;
import com.kkori.mini_festa.presentation.ui.event.detail.EventDetailContract;
import com.kkori.mini_festa.presentation.ui.event.detail.EventDetailPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class EventDetailModule {

    @Provides
    @EventFragmentScope
    GetEventThroughIdUseCase provideGetEventThroughIdUseCase(EventService eventService) {
        return new GetEventThroughIdUseCase(eventService);
    }

    @Provides
    @EventFragmentScope
    SetFavoriteEventUseCase provideSetFavoriteEventUseCase(EventService eventService) {
        return new SetFavoriteEventUseCase(eventService);
    }

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
