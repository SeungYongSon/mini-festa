package com.kkori.mini_festa.presentation.di.module.event;

import com.kkori.mini_festa.data.database.dao.EventDao;
import com.kkori.mini_festa.data.datasource.EventLocalDataSource;
import com.kkori.mini_festa.data.datasource.EventLocalDataSourceImp;
import com.kkori.mini_festa.data.datasource.EventRemoteDataSource;
import com.kkori.mini_festa.data.datasource.EventRemoteDataSourceImp;
import com.kkori.mini_festa.data.mapper.EventMapper;
import com.kkori.mini_festa.data.mapper.EventRoomMapper;
import com.kkori.mini_festa.data.API;
import com.kkori.mini_festa.data.repository.EventRepositoryImp;
import com.kkori.mini_festa.domain.entity.EventRepository;
import com.kkori.mini_festa.domain.entity.Service;
import com.kkori.mini_festa.domain.entity.ServiceImp;
import com.kkori.mini_festa.domain.usecase.GetRemoteEventListUseCase;
import com.kkori.mini_festa.domain.usecase.GetLocalEventListUseCase;
import com.kkori.mini_festa.presentation.di.scope.EventFragmentScope;
import com.kkori.mini_festa.presentation.event.EventContract;
import com.kkori.mini_festa.presentation.event.EventPresenter;
import com.kkori.mini_festa.presentation.mapper.EventModelMapper;
import com.kkori.mini_festa.presentation.ui.event.EventFragment;
import com.kkori.mini_festa.presentation.ui.event.EventListAdapter;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

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
    Service provideService(EventRepository eventRepository) {
        return new ServiceImp(eventRepository);
    }

    @Provides
    @EventFragmentScope
    GetRemoteEventListUseCase provideGetEventListUseCase(CompositeDisposable disposable, Service service) {
        return new GetRemoteEventListUseCase(disposable, service);
    }

    @Provides
    @EventFragmentScope
    GetLocalEventListUseCase provideGetLocalEventListUseCase(CompositeDisposable disposable, Service service) {
        return new GetLocalEventListUseCase(disposable, service);
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
