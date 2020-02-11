package com.travco.bloccompass.models.bloc;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "bloc")
public class Bloc {

    @PrimaryKey
    private UUID id;

    private String name;
    private double latitude;
    private double longitude;

    public Bloc(int id, String name, double latitude, double longitude)
    {
        setId(id);

        InitFields(name, latitude, longitude);
    }

    public Bloc(String name, double latitude, double longitude)
    {
        setId();

        InitFields(name, latitude, longitude);
    }

    private void InitFields(String name, double latitude, double longitude)
    {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //------------------//

    public UUID getId() { return id; }

    //Generate UUID with mountain project integer id as seed
    public void setId(int idSeed)
    {
        String seedString = Integer.toString(idSeed);
        this.id = UUID.nameUUIDFromBytes(seedString.getBytes());
    }

    public void setId() { this.id = UUID.randomUUID(); }

    //

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    //

    public double getLatitude() { return latitude; }

    public void setLatitude(double latitude) { this.latitude = latitude; }

    //

    public double getLongitude() { return longitude; }

    public void setLongitude(double longitude) { this.longitude = longitude; }
}
