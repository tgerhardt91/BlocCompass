package com.travco.bloccompass.models.mountainproject;

import com.travco.bloccompass.BuildConfig;
import com.travco.bloccompass.api.MountainProjectApiService;
import com.travco.bloccompass.timberTrees.NotLoggingTree;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

import timber.log.Timber;


public class MountainProjectSyncAdapter {

    private MountainProjectRouteGateway routeGateway;

    public MountainProjectSyncAdapter(MountainProjectRouteGateway routeGateway)
    {
        this.routeGateway = routeGateway;

        plantTreesForLogging();
    }

    public void SyncMountainProjectRoutes(MountainProjectSyncRequest syncRequest)
    {
        routeGateway.GetMountainProjectRoutes(
                syncRequest.getCenterOfMap(), syncRequest.getSyncRangeInMiles())
        .subscribe(routeData -> {
            Sync(routeData);
        }, throwable -> {
            Timber.e("Call to mountain project api failed");
        });
    }

    private void Sync(List<MountainProjectRoute> routes)
    {
        int t = 2;
    }

    private void plantTreesForLogging()
    {
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
        else
            Timber.plant(new NotLoggingTree());
    }
}

