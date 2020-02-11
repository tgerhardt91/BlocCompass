package com.travco.bloccompass.models.mountainproject;

import com.travco.bloccompass.api.MountainProjectApiService;

public class MountainProjectSyncAdapter {

    private MountainProjectApiService apiService;

    public MountainProjectSyncAdapter(MountainProjectApiService apiService)
    {
        this.apiService = apiService;
    }
}

