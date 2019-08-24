package com.kkori.mini_festa.data.mapper;

import com.kkori.mini_festa.data.database.entity.LocationRoomEntity;
import com.kkori.mini_festa.data.entity.LocationEntity;
import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Location;

public class LocationMapper implements Mapper<LocationEntity, Location> {

    @Override
    public Location mapFrom(LocationEntity from) {
        return new Location(from.getName(), from.getDescription(), from.getAddress());
    }

    public Location mapFrom(LocationRoomEntity from) {
        return new Location(from.getLocationName(), from.getDescription(), from.getAddress());
    }

}
