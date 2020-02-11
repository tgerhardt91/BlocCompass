package com.travco.bloccompass.api;
import com.travco.bloccompass.models.mountainproject.MountainProjectRoute;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface MountainProjectApiService {

    @GET("get-routes-for-lat-lon")
    Single<List<MountainProjectRoute>> getMountainProjectRoutes(
            @QueryMap Map<String, String> options);
}
