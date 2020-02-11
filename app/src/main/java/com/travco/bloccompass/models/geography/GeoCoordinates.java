package com.travco.bloccompass.models.geography;

public class GeoCoordinates {

    private double latitude;
    private double longitude;

    public GeoCoordinates(double latitude, double longitude)
    {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() { return this.latitude; }
    public double getLongitude() { return this.longitude; }
}
