package com.kkori.mini_festa.presentation.di.module.event;

import com.kkori.mini_festa.domain.entity.event.EventService;
import com.kkori.mini_festa.domain.usecase.GetFavoriteEventListUseCase;
import com.kkori.mini_festa.domain.usecase.GetLocalEventListUseCase;
import com.kkori.mini_festa.domain.usecase.GetRemoteEventListUseCase;
import com.kkori.mini_festa.presentation.di.scope.EventFragmentScope;
import com.kkori.mini_festa.presentation.event.board.EventBoardContract;
import com.kkori.mini_festa.presentation.event.board.EventBoardPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class EventBoardModule {

    @Provides
    @EventFragmentScope
    GetRemoteEventListUseCase provideGetEventListUseCase(EventService eventService) {
        return new GetRemoteEventListUseCase(eventService);
    }

    @Provides
    @EventFragmentScope
    GetLocalEventListUseCase provideGetLocalEventListUseCase(EventService eventService) {
        return new GetLocalEventListUseCase(eventService);
    }

    @Provides
    @EventFragmentScope
    GetFavoriteEventListUseCase provideGetFavoriteEventListUsecase(EventService eventService) {
        return new GetFavoriteEventListUseCase(eventService);
    }

    @Provides
    @EventFragmentScope
    EventBoardContract.Presenter provideEventBoardContractPresenter(EventBoardPresenter eventBoardPresenter) {
        return eventBoardPresenter;
    }

    @Provides
    @EventFragmentScope
    EventBoardContract.View provideEventBoardContractView(EventBoardPresenter eventBoardPresenter) {
        return eventBoardPresenter.getView();
    }

}
