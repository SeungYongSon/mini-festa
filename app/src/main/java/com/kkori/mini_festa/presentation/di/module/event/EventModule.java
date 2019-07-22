package com.kkori.mini_festa.presentation.di.module.event;

import com.kkori.mini_festa.data.API;
import com.kkori.mini_festa.data.database.dao.EventDao;
import com.kkori.mini_festa.data.datasource.EventLocalDataSource;
import com.kkori.mini_festa.data.datasource.EventLocalDataSourceImp;
import com.kkori.mini_festa.data.datasource.EventRemoteDataSource;
import com.kkori.mini_festa.data.datasource.EventRemoteDataSourceImp;
import com.kkori.mini_festa.data.mapper.EventMapper;
import com.kkori.mini_festa.data.mapper.EventRoomMapper;
import com.kkori.mini_festa.data.repository.EventRepositoryImp;
import com.kkori.mini_festa.domain.entity.event.EventRepository;
import com.kkori.mini_festa.domain.entity.event.EventService;
import com.kkori.mini_festa.domain.entity.event.EventServiceImp;
import com.kkori.mini_festa.domain.usecase.GetLocalEventListUseCase;
import com.kkori.mini_festa.domain.usecase.GetRemoteEventListUseCase;
import com.kkori.mini_festa.presentation.di.scope.EventFragmentScope;
import com.kkori.mini_festa.presentation.event.EventContract;
import com.kkori.mini_festa.presentation.event.EventFragment;
import com.kkori.mini_festa.presentation.event.EventListAdapter;
import com.kkori.mini_festa.presentation.event.EventPresenter;
import com.kkori.mini_festa.presentation.mapper.EventModelMapper;

import dagger.Module;
import dagger.Provides;

@Module
public class EventModule {

    @Provides
    @EventFragmentScope
    EventRemoteDataSource provideEventRemoteDataSource(API api) {
        return new EventRemoteDataSourceImp(api);
    }

    @Provides
    @EventFragmentScope
    EventLocalDataSource provideEventLocalDataSource(EventDao eventDao) {
        return new EventLocalDataSourceImp(eventDao);
    }

    @Provides
    @EventFragmentScope
    EventRepository provideEventRepository(EventRemoteDataSource eventRemoteDataSource,
                                           EventLocalDataSource eventLocalDataSource,
                                           EventMapper eventMapper,
                                           EventRoomMapper eventRoomMapper) {
        return new EventRepositoryImp(eventRemoteDataSource, eventLocalDataSource, eventMapper, eventRoomMapper);
    }

    @Provides
    @EventFragmentScope
    EventService provideEventService(EventRepository eventRepository) {
        return new EventServiceImp(eventRepository);
    }

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
    EventMapper provideEventMapper() {
        return new EventMapper();
    }

    @Provides
    @EventFragmentScope
    EventModelMapper provideEventModelMapper() {
        return new EventModelMapper();
    }

    @Provides
    @EventFragmentScope
    EventRoomMapper provideEventRoomMapper() {
        return new EventRoomMapper();
    }

    @Provides
    @EventFragmentScope
    EventContract.Presenter provideMainPresenter(EventFragment eventFragment,
                                                 GetRemoteEventListUseCase getRemoteEventListUseCase,
                                                 GetLocalEventListUseCase getLocalEventListUseCase,
                                                 EventModelMapper eventModelMapper) {

        EventPresenter eventPresenter = new EventPresenter(getRemoteEventListUseCase, getLocalEventListUseCase, eventModelMapper);

        eventPresenter.createView(eventFragment);
        eventFragment.setPresenter(eventPresenter);

        return eventPresenter;
    }

    @Provides
    @EventFragmentScope
    EventListAdapter provideEventListAdapter() {
        return new EventListAdapter();
    }

}
