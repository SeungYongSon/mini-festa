package com.kkori.mini_festa.data.mapper;

import com.kkori.mini_festa.data.database.entity.LocationRoomEntity;
import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Location;

public class LocationRoomMapper implements Mapper<Location, LocationRoomEntity> {

    @Override
    public LocationRoomEntity mapFrom(Location from) {
        return new LocationRoomEntity(from.getName(), from.getDescription(), from.getAddress());
    }
}
