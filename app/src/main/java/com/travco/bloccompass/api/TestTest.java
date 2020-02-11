package com.travco.bloccompass.api;

import com.travco.bloccompass.models.mountainproject.MountainProjectRoute;

import java.util.List;
import java.util.Map;

import io.reactivex.Single;

public class TestTest implements MountainProjectApiService {
    public Single<List<MountainProjectRoute>> getMountainProjectRoutes(Map<String, String> options) {
        return null;
    }
}
