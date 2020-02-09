package com.travco.bloccompass.viewmap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory;
import com.mapbox.mapboxsdk.geometry.LatLngBounds;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.Style;
import com.travco.bloccompass.BuildConfig;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.offline.OfflineManager;
import com.mapbox.mapboxsdk.offline.OfflineRegion;

import com.travco.bloccompass.home.HomeActivity;
import com.travco.bloccompass.timberTrees.NotLoggingTree;

import timber.log.Timber;

import com.travco.bloccompass.R;

import java.util.ArrayList;
import java.util.List;

public class ViewMapActivity extends AppCompatActivity implements PermissionsListener {

    private Bundle extras;
    private MapView mapView;
    private MapboxMap map;
    private PermissionsManager permissionsManager;
    private OfflineManager offlineManager;
    //ToDo: Not sure if this needs to be here: private OfflineRegion offlineRegion;

    public ViewMapActivity()
    {
        Intent intent = getIntent();
        extras = intent.getExtras();

        offlineManager = OfflineManager.getInstance(ViewMapActivity.this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        plantTreesForLogging();

        //ToDo:Not sure if I need to do this again here
        //Mapbox.getInstance(this, getString(R.string.mapbox_api_key));

        setContentView(R.layout.activity_home);

        setupMapView(savedInstanceState);

        MoveCameraToSelectedOfflineRegion();
    }

    private void setupMapView(Bundle savedInstanceState)
    {
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync(mapboxMap -> {
            map = mapboxMap;
            mapboxMap.setStyle(
                    Style.SATELLITE,
                    style -> enableLocationComponent(style)
            );
        });
    }

    private void MoveCameraToSelectedOfflineRegion()
    {
        int regionSelectionId = getOfflineRegionSelectionId();

        offlineManager.listOfflineRegions(new OfflineManager.ListOfflineRegionsCallback() {
            @Override
            public void onList(final OfflineRegion[] offlineRegions) {
                if (offlineRegions == null || offlineRegions.length == 0) {
                    Toast.makeText(getApplicationContext(), getString(R.string.no_maps_message), Toast.LENGTH_SHORT).show();
                    return;
                }

                try{
                    LatLngBounds bounds = (offlineRegions[regionSelectionId].getDefinition()).getBounds();
                    double regionZoom = (offlineRegions[regionSelectionId].getDefinition()).getMinZoom();

                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(bounds.getCenter())
                            .zoom(regionZoom)
                            .build();

                    map.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                }
                catch (Exception ex)
                {
                    Toast.makeText(
                            getApplicationContext(),
                            getString(R.string.region_load_failed), Toast.LENGTH_SHORT
                    ).show();
                }
            }
            @Override
            public void onError(String error) {
                Timber.e( "Error: %s", error);
            }
        });
    }

    private int getOfflineRegionSelectionId()
    {
        return extras.getInt("REGION_SELECTION");
    }

    private void plantTreesForLogging()
    {
        if (BuildConfig.DEBUG)
            Timber.plant(new Timber.DebugTree());
        else
            Timber.plant(new NotLoggingTree());
    }

    //-----------------------------//
    //     Map Location Component
    //-----------------------------//

    private void enableLocationComponent(Style mapStyle) {
        if (PermissionsManager.areLocationPermissionsGranted(this)) {

            LocationComponent locationComponent = map.getLocationComponent();

            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(this, mapStyle).build());

            locationComponent.setLocationComponentEnabled(true);

            locationComponent.setCameraMode(CameraMode.TRACKING);

            locationComponent.setRenderMode(RenderMode.COMPASS);
        }
        else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(this);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(this, R.string.user_location_permission_explanation, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            map.getStyle(style -> enableLocationComponent(style));
        } else {
            Toast.makeText(this, R.string.user_location_permission_not_granted, Toast.LENGTH_LONG).show();
            finish();
        }
    }
}
