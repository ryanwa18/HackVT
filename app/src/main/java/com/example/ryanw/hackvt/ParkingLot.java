package com.example.ryanw.hackvt;

/**
 * Created by edu-2_000 on 2/17/2017.
 */
public class ParkingLot
{
    private String lotID;
    private String lotName;
    private String lotAllowedOnWeekdaysSP;
    private String lotAllowedOnWeekdaysNSP;
    private String lotAllowedOnWeekendsSP;
    private String lotAllowedOnWeekendsNSP;
    private String lotTimesAllowedOnWeekdaysSP;
    private String lotTimesAllowedOnWeekdaysNSP;
    private String lotTimesAllowedOnWeekendsSP;
    private String lotTimesAllowedOnWeekendsNSP;
    private float lotMinLongitude;
    private float lotMaxLongitude;
    private float lotMinLatitude;
    private float lotMaxLatitude;
    //private Blob lotPicture;

    //Default constructor
    public ParkingLot()
    {}

    //Lot ID and name.
    public void setLotID(String id)
    {
        lotID = id;
    }

    public String getlotID()
    {
        return lotID;
    }

    public void setName(String name)
    {
        lotName = name;
    }

    public String getName()
    {
        return lotName;
    }

    //Lot allowed on weekdays with and without SP.
    public void setLotAllowedOnWeekdaysSP(String allowedOnWeekdaysSP)
    {
        lotAllowedOnWeekdaysSP = allowedOnWeekdaysSP;
    }

    public String getLotAllowedOnWeekdaysSP()
    {
        return lotAllowedOnWeekdaysSP;
    }

    public void setLotAllowedOnWeekdaysNSP(String allowedOnWeekdaysNSP)
    {
        lotAllowedOnWeekdaysNSP = allowedOnWeekdaysNSP;
    }

    public String getLotAllowedOnWeekdaysNSP()
    {
        return lotAllowedOnWeekdaysNSP;
    }

    //Lot allowed on weekends with and without SP.
    public void setLotAllowedOnWeekendsSP(String allowedOnWeekendsSP)
    {
        lotAllowedOnWeekendsSP = allowedOnWeekendsSP;
    }

    public String getLotAllowedOnWeekendsSP()
    {
        return lotAllowedOnWeekendsSP;
    }

    public void setLotAllowedOnWeekendsNSP(String allowedOnWeekendsNSP)
    {
        lotAllowedOnWeekendsNSP = allowedOnWeekendsNSP;
    }

    public String getLotAllowedOnWeekendsNSP()
    {
        return lotAllowedOnWeekendsNSP;
    }


    //Lot times allowed on weekdays with and without SP.
    public void setLotTimesAllowedOnWeekdaysSP(String timesAllowedOnWeekdaysSP)
    {
        lotTimesAllowedOnWeekdaysSP = timesAllowedOnWeekdaysSP;
    }

    public String getLotTimesAllowedOnWeekdaysSP()
    {
        return lotTimesAllowedOnWeekdaysSP;
    }

    public void setlotTimesAllowedOnWeekdaysNSP(String timesAllowedOnWeekdaysNSP)
    {
        lotTimesAllowedOnWeekdaysNSP = timesAllowedOnWeekdaysNSP;
    }

    public String getLotTimesAllowedOnWeekdaysNSP()
    {
        return lotTimesAllowedOnWeekdaysNSP;
    }


    //Lot times allowed on weekends with and without SP.
    public void setLotTimesAllowedOnWeekendsSP(String timesAllowedOnWeekendsSP)
    {
        lotTimesAllowedOnWeekendsSP = timesAllowedOnWeekendsSP;
    }

    public String getLotTimesAllowedOnWeekendsSP()
    {
        return lotTimesAllowedOnWeekendsSP;
    }

    public void setlotTimesAllowedOnWeekendsNSP(String timesAllowedOnWeekendsNSP)
    {
        lotTimesAllowedOnWeekendsNSP = timesAllowedOnWeekendsNSP;
    }

    public String getLotTimesAllowedOnWeekendsNSP()
    {
        return lotTimesAllowedOnWeekendsNSP;
    }


    //Latitude and longitude limits.
    public void setlotMinLongitude(float minLongitude)
    {
        lotMinLongitude = minLongitude;
    }

    public float getlotMinLongitude()
    {
        return lotMinLongitude;
    }

    public void setlotMaxLongitude(float maxLongitude)
    {
        lotMaxLongitude = maxLongitude;
    }

    public float getlotMaxLongitude()
    {
        return lotMaxLongitude;
    }

    public void setlotMinLatitude(float minLatitude)
    {
        lotMinLatitude = minLatitude;
    }

    public float getlotMinLatitude()
    {
        return lotMinLatitude;
    }

    public void setlotMaxLatitude(float maxLatitude)
    {
        lotMaxLatitude = maxLatitude;
    }

    public float getlotMaxLatitude()
    {
        return lotMaxLatitude;
    }

    /*public void setPicture(Blob picture)
    {
        lotPicture = picture;
    }

    public double getlotPicture()
    {
        return lotPicture;
    }*/
}
