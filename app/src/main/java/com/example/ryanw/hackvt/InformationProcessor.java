package com.example.ryanw.hackvt;

/**
 * Created by edu-2_000 on 2/18/2017.
 */

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.Calendar;
import java.util.Date;

public class InformationProcessor extends Service implements LocationListener
{
    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    boolean userHasSP;

    //Setting up Location
    LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    double longitude = location.getLongitude();
    double latitude = location.getLatitude();



    public double getLatitude()
    {
        return latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    //Setting up Calendar.
    Date now = new Date();
    Calendar calendar = Calendar.getInstance();
    //calendar.setTime(now);
    int dayOfTheWeek = calendar.get(Calendar.DAY_OF_WEEK);

    /*Helper method.
        Name: isTodayAWeekday
        Purpose:
        Parameters
            dayOfTheWeek - Integer between 1-7 corresponding to Sudnay-Saturday.
        Output
            Boolean - True if today is Monday - Friday. False otherwise.
     */
    /**
    public boolean isTodayAWeekday(int dayInt)
    {
        if (dayInt > 1 && dayInt < 7)
        {
            return true;
        } else
        {
            return false;
        }
    }

    /*Method
    Name: isUserWithinParkingLotLimits
    Purpose: Determine if the given user’s coordinates are within the limits of a given max and min coordinates.
    Parameters:
        currentLot - The lot the program wants to check if the user is in.
        userID - The unique ID of the current user.
    Output
        Boolean - True when the user is within the parking lot’s limit. False otherwise.*/
    /**
    public boolean isUserWithinParkingLotLimits(ParkingLot currentLot, String userID)
    {
        if ((float) latitude < currentLot.getlotMinLatitude() && (float) latitude > currentLot.getlotMaxLatitude() &&
                (float) longitude < currentLot.getlotMaxLongitude() && (float) longitude > currentLot.getlotMinLongitude())
        {
            return true;
        } else
        {
            return false;
        }
    }

    /*Method
    Name: canUserParkInCurrentLot
    Purpose: To determine at what times the user can park in currentLot on the present day.
        Parameters
    currentLot - The lot the program wants to check if the user can park in.
        userID - The unique ID of the current user.
        Output
    String - A statement stating if the user can or cannot park in currentLot in the present day. If the user can park in currentLot in the present day, display the times of day the user can park in currentLot.*/
    /**
    public String canUserParkInCurrentLot(ParkingLot currentLot, String userID) {
        boolean canParkWeedaysSP = Boolean.parseBoolean(currentLot.getLotAllowedOnWeekdaysSP());
        boolean canParkWeekendsSP = Boolean.parseBoolean(currentLot.getLotAllowedOnWeekendsSP());
        boolean canParkWeekdaysNSP = Boolean.parseBoolean(currentLot.getLotAllowedOnWeekdaysNSP());
        boolean canParkWeekendsNSP = Boolean.parseBoolean(currentLot.getLotAllowedOnWeekendsNSP());

        String canPark = "You can park in this parking lot from ";
        String cannotPark = "You cannot park in this parking lot.";

        if (userHasSP)
        {
            if (isTodayAWeekday(dayOfTheWeek)) //Check if today is a weekday.
            {
                if (canParkWeedaysSP) //Check if a SP can park in this lot on weekdays
                {
                    return canPark + currentLot.getLotTimesAllowedOnWeekdaysSP();
                } else if(!canParkWeedaysSP){
                    return "You cannot park in this parking lot.";
                }
            } else if (!isTodayAWeekday(dayOfTheWeek)) //Check if today is a weekend.
            {
                if (canParkWeekendsSP) //Check if a SP can park in this lot on weekends.
                {
                    return canPark + currentLot.getLotTimesAllowedOnWeekendsSP();
                } else if (!canParkWeekendsSP) {
                    return cannotPark;
                }
            } else if (!userHasSP) {
                if (isTodayAWeekday(dayOfTheWeek)) {
                    if (canParkWeekdaysNSP) {
                        return canPark + currentLot.getLotTimesAllowedOnWeekdaysNSP();
                    } else if( !canParkWeekdaysNSP){
                        return cannotPark;
                    }
                } else if (!isTodayAWeekday(dayOfTheWeek)) {
                    if (canParkWeekendsNSP) {
                        return canPark + currentLot.getLotTimesAllowedOnWeekendsNSP();
                    } else if (!canParkWeekendsNSP) {
                        return cannotPark;
                    }
                }
            }
        }
    }
*/
}


