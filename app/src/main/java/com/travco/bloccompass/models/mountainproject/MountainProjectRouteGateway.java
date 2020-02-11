package com.travco.bloccompass.models.mountainproject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.travco.bloccompass.R;
import com.travco.bloccompass.api.MountainProjectApiService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MountainProjectRouteGateway{

    public Single<List<MountainProjectRoute>> GetMountainProjectRoutes(
            double lat,
            double lon,
            int maxDistance)
    {
        MountainProjectApiService apiService = GetApiService();


        return apiService.getMountainProjectRoutes(
                BuildQueryMap(lat, lon, maxDistance));
    }


    private Map<String, String> BuildQueryMap(double lat, double lon, int maxDistance)
    {
        Map<String, String> data = new HashMap<>();
        data.put("lat", String.valueOf(lat));
        data.put("lon", String.valueOf(lon));
        data.put("maxDistance", String.valueOf(maxDistance));
        data.put("key", String.valueOf(R.string.mountain_project_api_key));

        return data;
    }

    private MountainProjectApiService GetApiService()
    {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(MountainProjectApiService.class);
    }
}
