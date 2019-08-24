package com.kkori.mini_festa.presentation.di.module.event;

import com.kkori.mini_festa.data.API;
import com.kkori.mini_festa.data.database.dao.EventDao;
import com.kkori.mini_festa.data.datasource.EventLocalDataSource;
import com.kkori.mini_festa.data.datasource.EventLocalDataSourceImp;
import com.kkori.mini_festa.data.datasource.EventRemoteDataSource;
import com.kkori.mini_festa.data.datasource.EventRemoteDataSourceImp;
import com.kkori.mini_festa.data.mapper.EventMapper;
import com.kkori.mini_festa.data.mapper.EventRoomMapper;
import com.kkori.mini_festa.data.mapper.LocationMapper;
import com.kkori.mini_festa.data.mapper.LocationRoomMapper;
import com.kkori.mini_festa.data.mapper.TicketsMapper;
import com.kkori.mini_festa.data.mapper.TicketsRoomMapper;
import com.kkori.mini_festa.data.repository.EventRepositoryImp;
import com.kkori.mini_festa.domain.entity.event.EventRepository;
import com.kkori.mini_festa.domain.entity.event.EventService;
import com.kkori.mini_festa.domain.entity.event.EventServiceImp;
import com.kkori.mini_festa.presentation.di.scope.EventFragmentScope;
import com.kkori.mini_festa.presentation.mapper.EventFormat;
import com.kkori.mini_festa.presentation.mapper.EventModelMapper;
import com.kkori.mini_festa.presentation.mapper.LocationModelMapper;
import com.kkori.mini_festa.presentation.mapper.TicketModelsMapper;

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
    EventMapper provideEventMapper() {
        return new EventMapper(new TicketsMapper(), new LocationMapper());
    }

    @Provides
    @EventFragmentScope
    EventModelMapper provideEventModelMapper(EventFormat eventFormat) {
        return new EventModelMapper(new TicketModelsMapper(eventFormat), new LocationModelMapper(), eventFormat);
    }

    @Provides
    @EventFragmentScope
    EventRoomMapper provideEventRoomMapper() {
        return new EventRoomMapper(new TicketsRoomMapper(), new LocationRoomMapper());
    }

}
