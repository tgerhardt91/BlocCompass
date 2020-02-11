package com.travco.bloccompass.models.mountainproject;

import com.travco.bloccompass.models.geography.GeoCoordinates;

public class MountainProjectSyncRequest {

    private GeoCoordinates centerOfMap;
    private int syncRangeInMiles;

    public MountainProjectSyncRequest(GeoCoordinates centerOfMap, int syncRangeInMiles)
    {
        this.centerOfMap = centerOfMap;
        this.syncRangeInMiles = syncRangeInMiles;
    }
}
