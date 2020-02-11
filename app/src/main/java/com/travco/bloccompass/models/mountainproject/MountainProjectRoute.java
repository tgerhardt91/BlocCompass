package com.travco.bloccompass.models.mountainproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MountainProjectRoute {
    @Expose
    @SerializedName("id")
    private Integer id;

    @Expose
    @SerializedName("name")
    private String name;

    @Expose
    @SerializedName("type")
    private String type;

    @Expose
    @SerializedName("rating")
    private String rating;

    @Expose
    @SerializedName("stars")
    private double stars;

    @Expose
    @SerializedName("starVotes")
    private int starVotes;

    @Expose
    @SerializedName("location")
    private String[] location;

    @Expose
    @SerializedName("url")
    private String url;

    @Expose
    @SerializedName("longitude")
    private double longitude;

    @Expose
    @SerializedName("latitude")
    private double latitude;

    //-----------------------//

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getRating() {
        return rating;
    }
    public void setRating(String rating) {
        this.rating = rating;
    }

    public double getStars() {
        return stars;
    }
    public void setStars(double stars) {
        this.stars = stars;
    }

    public int getStarVotes() {
        return starVotes;
    }
    public void setStarVotes(int starVotes) {
        this.starVotes = starVotes;
    }

    public String[] getLocation() {
        return location;
    }
    public void setRating(String[] location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }

    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
