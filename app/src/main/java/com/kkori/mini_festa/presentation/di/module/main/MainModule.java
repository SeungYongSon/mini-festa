package com.kkori.mini_festa.presentation.di.module.main;

import com.kkori.mini_festa.data.datasource.EventRemoteDataSource;
import com.kkori.mini_festa.data.datasource.EventRemoteDataSourceImp;
import com.kkori.mini_festa.data.mapper.EventMapper;
import com.kkori.mini_festa.data.remote.API;
import com.kkori.mini_festa.data.repository.EventRepositoryImp;
import com.kkori.mini_festa.domain.entity.EventRepository;
import com.kkori.mini_festa.domain.entity.Service;
import com.kkori.mini_festa.domain.entity.ServiceImp;
import com.kkori.mini_festa.domain.usecase.GetEventListUseCase;
import com.kkori.mini_festa.presentation.di.scope.ActivityScope;
import com.kkori.mini_festa.presentation.mapper.EventModelMapper;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class MainModule {

    @ActivityScope
    @Provides
    EventRemoteDataSource provideEventRemoteDataSource(API api) {
        return new EventRemoteDataSourceImp(api);
    }


    @ActivityScope
    @Provides
    EventRepository provideEventRepository(EventRemoteDataSource eventRemoteDataSource, EventMapper eventMapper) {
        return new EventRepositoryImp(eventRemoteDataSource, eventMapper);
    }

    @ActivityScope
    @Provides
    Service provideService(EventRepository eventRepository) {
        return new ServiceImp(eventRepository);
    }

    @ActivityScope
    @Provides
    GetEventListUseCase provideGetEventListUseCase(CompositeDisposable disposable, Service service) {
        return new GetEventListUseCase(disposable, service);
    }

    @ActivityScope
    @Provides
    EventMapper provideEventMapper() {
        return new EventMapper();
    }

    @ActivityScope
    @Provides
    EventModelMapper provideEventModelMapper() {
        return new EventModelMapper();
    }

}
