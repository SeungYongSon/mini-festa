package com.kkori.mini_festa.presentation.mapper;

import com.kkori.mini_festa.domain.base.Mapper;
import com.kkori.mini_festa.domain.entity.Location;
import com.kkori.mini_festa.presentation.model.LocationModel;

public class LocationModelMapper implements Mapper<Location, LocationModel> {

    @Override
    public LocationModel mapFrom(Location from) {
        return new LocationModel(from.getName(), from.getDescription(), from.getAddress());
    }
}
