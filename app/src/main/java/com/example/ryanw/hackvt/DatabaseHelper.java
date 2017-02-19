package com.example.ryanw.hackvt;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by ryanw on 2/17/2017.
 */


public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    // Database Info
    private static final String DATABASE_NAME = "ParkingLots";
    private static final int DATABASE_VERSION = 1;

    //Table Names
    private static final String PARKING_LOTS = "parkingLots";


    //ParkingLot Table Columns
    private static final String _ID = "_id";
    private static final String NAME = "name";
    private static final String MAX_LATITUDE = "maxLatitude";
    private static final String MAX_LONGITUDE = "maxLongitude";
    private static final String MIN_LATITUDE = "minLatitude";
    private static final String MIN_LONGITUDE = "minLongitude";
    private static final String TIME_RANGE_WITH_PASS_WEEKDAY = "timeRangeWithPassWeekday";
    private static final String TIME_RANGE_WITHOUT_PASS_WEEKDAY = "timeRangeWithoutPassWeekday";
    private static final String TIME_RANGE_WITH_PASS_WEEKEND = "timeRangeWithPassWeekend";
    private static final String TIME_RANGE_WITHOUT_PASS_WEEKEND = "timeRangeWithoutPassWeekend";
    private static final String ALLOWED_ON_WEEKDAYS_WITH_PASS = "allowedOnWeekdaysWithPass";
    private static final String ALLOWED_ON_WEEKDAYS_WITHOUT_PASS = "allowedOnWeekdaysWithoutPass";
    private static final String ALLOWED_ON_WEEKENDS_WITH_PASS = "allowedOnWeekendsWithPass";
    private static final String ALLOWED_ON_WEEKENDS_WITHOUT_PASS = "allowedOnWeekendsWithoutPass";
    private static final String PICTURE = "picture";

    private static DatabaseHelper myDatabaseHelper;

    private static ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();

    public static synchronized DatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.

        if (myDatabaseHelper == null) {
            myDatabaseHelper = new DatabaseHelper(context.getApplicationContext());
        }
        return myDatabaseHelper;
    }

    DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PARKING_LOTS_TABLE = "CREATE TABLE " + PARKING_LOTS +
                "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                NAME + " TEXT," +
                MAX_LATITUDE + " FLOAT," +
                MAX_LONGITUDE + " FLOAT," +
                MIN_LATITUDE + " FLOAT," +
                MIN_LONGITUDE + " FLOAT," +
                TIME_RANGE_WITH_PASS_WEEKDAY + " TEXT," +
                TIME_RANGE_WITHOUT_PASS_WEEKDAY + " TEXT," +
                TIME_RANGE_WITH_PASS_WEEKEND + " TEXT," +
                TIME_RANGE_WITHOUT_PASS_WEEKEND + " TEXT," +
                ALLOWED_ON_WEEKDAYS_WITH_PASS + " TEXT," +
                ALLOWED_ON_WEEKENDS_WITH_PASS + " TEXT," +
                ALLOWED_ON_WEEKDAYS_WITHOUT_PASS + " TEXT," +
                ALLOWED_ON_WEEKENDS_WITHOUT_PASS + " TEXT," +
                PICTURE + " BLOB" +
                ")";
        db.execSQL(CREATE_PARKING_LOTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + PARKING_LOTS);
        }
    }

    /**
     * Inserts parking lot objects into the table.
     *
     * @param parkingLot the parking lot to be inserted.
     * @throws SQLException if table is not found.
     */
    public void insertParkingLots(ParkingLot parkingLot) throws SQLException {
        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();

        ContentValues values = new ContentValues();
        values.put(NAME, parkingLot.getName());
        values.put(MAX_LATITUDE, parkingLot.getlotMaxLatitude());
        values.put(MAX_LONGITUDE, parkingLot.getlotMaxLongitude());
        values.put(MIN_LONGITUDE, parkingLot.getlotMinLongitude());
        values.put(MIN_LATITUDE, parkingLot.getlotMinLatitude());
        values.put(TIME_RANGE_WITH_PASS_WEEKDAY, parkingLot.getLotTimesAllowedOnWeekdaysSP());
        values.put(TIME_RANGE_WITHOUT_PASS_WEEKDAY, parkingLot.getLotTimesAllowedOnWeekdaysNSP());
        values.put(TIME_RANGE_WITH_PASS_WEEKEND, parkingLot.getLotTimesAllowedOnWeekendsSP());
        values.put(TIME_RANGE_WITHOUT_PASS_WEEKEND, parkingLot.getLotTimesAllowedOnWeekendsNSP());

        values.put(ALLOWED_ON_WEEKDAYS_WITH_PASS, parkingLot.getLotAllowedOnWeekdaysSP());
        values.put(ALLOWED_ON_WEEKENDS_WITH_PASS, parkingLot.getLotAllowedOnWeekendsSP());
        values.put(ALLOWED_ON_WEEKDAYS_WITHOUT_PASS, parkingLot.getLotAllowedOnWeekdaysNSP());
        values.put(ALLOWED_ON_WEEKENDS_WITHOUT_PASS, parkingLot.getLotAllowedOnWeekendsNSP());
        //values.put(PICTURE, parkingLot.getPicture());


        database.insertOrThrow(PARKING_LOTS, null, values);
    }

     /*
        fetch all data from ParkingLotsTable
    */

    public ArrayList<ParkingLot> getAllParkingLots() {

        ArrayList<ParkingLot> parkingLotDetails = new ArrayList<>();

        String LOT_DETAIL_SELECT_QUERY = "SELECT * FROM " + PARKING_LOTS;

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(LOT_DETAIL_SELECT_QUERY, null);

        try {
            if (cursor.moveToFirst()) {
                do {
                    ParkingLot parkingLot = new ParkingLot();
                    parkingLot.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                    parkingLot.setlotMaxLatitude(Float.parseFloat(cursor.getString(cursor.getColumnIndex(MAX_LATITUDE))));
                    parkingLot.setlotMaxLongitude(Float.parseFloat(cursor.getString(cursor.getColumnIndex(MAX_LONGITUDE))));
                    parkingLot.setlotMinLatitude(Float.parseFloat(cursor.getString(cursor.getColumnIndex(MIN_LATITUDE))));
                    parkingLot.setlotMinLongitude(Float.parseFloat(cursor.getString(cursor.getColumnIndex(MIN_LONGITUDE))));
                    parkingLot.setLotTimesAllowedOnWeekdaysSP(cursor.getString(cursor.getColumnIndex(TIME_RANGE_WITH_PASS_WEEKDAY)));
                    parkingLot.setlotTimesAllowedOnWeekdaysNSP(cursor.getString(cursor.getColumnIndex(TIME_RANGE_WITHOUT_PASS_WEEKDAY)));
                    parkingLot.setLotTimesAllowedOnWeekendsSP(cursor.getString(cursor.getColumnIndex(TIME_RANGE_WITH_PASS_WEEKEND)));
                    parkingLot.setlotTimesAllowedOnWeekendsNSP(cursor.getString(cursor.getColumnIndex(TIME_RANGE_WITHOUT_PASS_WEEKEND)));
                    parkingLot.setLotAllowedOnWeekdaysSP(cursor.getString(cursor.getColumnIndex(ALLOWED_ON_WEEKDAYS_WITH_PASS)));
                    parkingLot.setLotAllowedOnWeekdaysNSP(cursor.getString(cursor.getColumnIndex(ALLOWED_ON_WEEKDAYS_WITHOUT_PASS)));
                    parkingLot.setLotAllowedOnWeekendsSP(cursor.getString(cursor.getColumnIndex(ALLOWED_ON_WEEKENDS_WITH_PASS)));
                    parkingLot.setLotAllowedOnWeekendsNSP(cursor.getString(cursor.getColumnIndex(ALLOWED_ON_WEEKENDS_WITHOUT_PASS)));
                    parkingLotDetails.add(parkingLot);

                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.d(TAG, "Error while trying to get posts from database");
        } finally {
            cursor.close();
            db.close();
        }

        return parkingLotDetails;

    }

    /**
     * Creation of different parking lot objects.
     */
    public void createParkingLots() {
        //Squires Parking Lot.
        ParkingLot squires = new ParkingLot();
        squires.setName("Squires Parking Lot");
        squires.setLotAllowedOnWeekdaysSP("true");
        squires.setLotAllowedOnWeekdaysNSP("true");
        squires.setLotAllowedOnWeekendsSP("true");
        squires.setLotAllowedOnWeekendsNSP("true");
        squires.setLotTimesAllowedOnWeekdaysSP("12am - 11:59pm");
        squires.setlotTimesAllowedOnWeekdaysNSP("5pm - 7am");
        squires.setLotTimesAllowedOnWeekendsSP("12am - 11:59pm");
        squires.setlotTimesAllowedOnWeekendsNSP("12am - 11:59pm");

        squires.setlotMinLongitude(-80.417546f);
        squires.setlotMaxLongitude(-80.416135f);
        squires.setlotMinLatitude(37.229146f);
        squires.setlotMaxLatitude(37.229218f);
        //squires.setPicture(//Blob file);

        parkingLots.add(squires);

        //Surge Parking Lot.
        ParkingLot surge = new ParkingLot();
        surge.setName("Surge Parking Lot");
        surge.setLotAllowedOnWeekdaysSP("true");
        surge.setLotAllowedOnWeekdaysNSP("true");
        surge.setLotAllowedOnWeekendsSP("true");
        surge.setLotAllowedOnWeekendsNSP("true");
        surge.setLotTimesAllowedOnWeekdaysSP("12am - 11:59pm");
        surge.setlotTimesAllowedOnWeekdaysNSP("5pm - 7am");
        surge.setLotTimesAllowedOnWeekendsSP("12am - 11:59pm");
        surge.setlotTimesAllowedOnWeekendsNSP("12am - 11:59pm");

        surge.setlotMinLongitude(-80.423878f);
        surge.setlotMaxLongitude(-80.422799f);
        surge.setlotMinLatitude(37.232188f);
        surge.setlotMaxLatitude(37.233085f);
        //surge.setPicture(//Blob file);
        parkingLots.add(surge);

        //Owens Parking Lot.
        ParkingLot owens = new ParkingLot();
        owens.setName("Owens Parking Lot");
        owens.setLotAllowedOnWeekdaysSP("false");
        owens.setLotAllowedOnWeekdaysNSP("false");
        owens.setLotAllowedOnWeekendsSP("false");
        owens.setLotAllowedOnWeekendsNSP("false");
        owens.setLotTimesAllowedOnWeekdaysSP("Never");
        owens.setlotTimesAllowedOnWeekdaysNSP("Never");
        owens.setLotTimesAllowedOnWeekendsSP("Never");
        owens.setlotTimesAllowedOnWeekendsNSP("Never");

        owens.setlotMinLongitude(-80.419026f);
        owens.setlotMaxLongitude(-80.417984f);
        owens.setlotMinLatitude(37.226169f);
        owens.setlotMaxLatitude(37.226618f);
        //owens.setPicture(//Blob file);
        parkingLots.add(owens);

        //Dietrick Hall Parking Lot.
        ParkingLot dietrick = new ParkingLot();
        dietrick.setName("Dietrick Parking Lot");
        dietrick.setLotAllowedOnWeekdaysSP("false");
        dietrick.setLotAllowedOnWeekdaysNSP("false");
        dietrick.setLotAllowedOnWeekendsSP("false");
        dietrick.setLotAllowedOnWeekendsNSP("false");
        dietrick.setLotTimesAllowedOnWeekdaysSP("Never");
        dietrick.setlotTimesAllowedOnWeekdaysNSP("Never");
        dietrick.setLotTimesAllowedOnWeekendsSP("Never");
        dietrick.setlotTimesAllowedOnWeekendsNSP("Never");
        dietrick.setlotMinLongitude(-80.421681f);
        dietrick.setlotMaxLongitude(-80.42113f);
        dietrick.setlotMinLatitude(37.224418f);
        dietrick.setlotMaxLatitude(37.225174f);
        //dietrick.setPicture(//Blob file);
        parkingLots.add(dietrick);

        //Basketball Practice Extension Parking Lot.
        ParkingLot bballPracExt = new ParkingLot();
        bballPracExt.setName("Basketball Practice Extension Parking Lot");
        bballPracExt.setLotAllowedOnWeekdaysSP("false");
        bballPracExt.setLotAllowedOnWeekdaysNSP("false");
        bballPracExt.setLotAllowedOnWeekendsSP("false");
        bballPracExt.setLotAllowedOnWeekendsNSP("false");
        bballPracExt.setLotTimesAllowedOnWeekdaysSP("Never");
        bballPracExt.setlotTimesAllowedOnWeekdaysNSP("Never");
        bballPracExt.setLotTimesAllowedOnWeekendsSP("Never");
        bballPracExt.setlotTimesAllowedOnWeekendsNSP("Never");
        bballPracExt.setlotMinLongitude(-80.417958f);
        bballPracExt.setlotMaxLongitude(-80.417487f);
        bballPracExt.setlotMinLatitude(37.223736f);
        bballPracExt.setlotMaxLatitude(37.223888f);
        //bballPracExt.setPicture(//Blob file);
        parkingLots.add(bballPracExt);

        //Basketball Practice Facility Parking Lot.
        ParkingLot bballPracFac = new ParkingLot();
        bballPracFac.setName("Basketball Practice Facility Parking Lot");
        bballPracFac.setLotAllowedOnWeekdaysSP("false");
        bballPracFac.setLotAllowedOnWeekdaysNSP("false");
        bballPracFac.setLotAllowedOnWeekendsSP("false");
        bballPracFac.setLotAllowedOnWeekendsNSP("false");
        bballPracFac.setLotTimesAllowedOnWeekdaysSP("Never");
        bballPracFac.setlotTimesAllowedOnWeekdaysNSP("Never");
        bballPracFac.setLotTimesAllowedOnWeekendsSP("Never");
        bballPracFac.setlotTimesAllowedOnWeekendsNSP("Never");
        bballPracFac.setlotMinLongitude(-80.418844f);
        bballPracFac.setlotMaxLongitude(-80.418274f);
        bballPracFac.setlotMinLatitude(37.223086f);
        bballPracFac.setlotMaxLatitude(37.223304f);
        //bballPracExt.setPicture(//Blob file);
        parkingLots.add(bballPracFac);

        //Prices Fork 5 Parking Lot.
        ParkingLot pricesFork5 = new ParkingLot();
        pricesFork5.setName("Prices Fork 5 Parking Lot");
        pricesFork5.setLotAllowedOnWeekdaysSP("true");
        pricesFork5.setLotAllowedOnWeekdaysNSP("true");
        pricesFork5.setLotAllowedOnWeekendsSP("true");
        pricesFork5.setLotAllowedOnWeekendsNSP("true");
        pricesFork5.setLotTimesAllowedOnWeekdaysSP("12am - 11:59pm");
        pricesFork5.setlotTimesAllowedOnWeekdaysNSP("5pm - 7am");
        pricesFork5.setLotTimesAllowedOnWeekendsSP("12am - 11:59pm");
        pricesFork5.setlotTimesAllowedOnWeekendsNSP("12am - 11:59pm");
        pricesFork5.setlotMinLongitude(-80.427245f);
        pricesFork5.setlotMaxLongitude(-80.426824f);
        pricesFork5.setlotMinLatitude(37.231300f);
        pricesFork5.setlotMaxLatitude(37.232340f);
        //pricesFork5.setPicture(//Blob file);
        parkingLots.add(pricesFork5);

        //Prices Fork 6 Parking Lot.
        ParkingLot pricesFork6 = new ParkingLot();
        pricesFork6.setName("Prices Fork 6 Parking Lot");
        pricesFork6.setLotAllowedOnWeekdaysSP("true");
        pricesFork6.setLotAllowedOnWeekdaysNSP("true");
        pricesFork6.setLotAllowedOnWeekendsSP("true");
        pricesFork6.setLotAllowedOnWeekendsNSP("true");
        pricesFork6.setLotTimesAllowedOnWeekdaysSP("12am - 11:59pm");
        pricesFork6.setlotTimesAllowedOnWeekdaysNSP("5pm - 7am");
        pricesFork6.setLotTimesAllowedOnWeekendsSP("12am - 11:59pm");
        pricesFork6.setlotTimesAllowedOnWeekendsNSP("12am - 11:59pm");
        pricesFork6.setlotMinLongitude(-80.42788f);
        pricesFork6.setlotMaxLongitude(-80.427704f);
        pricesFork6.setlotMinLatitude(37.229481f);
        pricesFork6.setlotMaxLatitude(37.23178f);
        //pricesFork6.setPicture(//Blob file);
        parkingLots.add(pricesFork6);

        //Duck Pond Road Parking Lot.
        ParkingLot duckPondRd = new ParkingLot();
        duckPondRd.setName("Duck Pond Road Parking Lot");
        duckPondRd.setLotAllowedOnWeekdaysSP("true");
        duckPondRd.setLotAllowedOnWeekdaysNSP("true");
        duckPondRd.setLotAllowedOnWeekendsSP("true");
        duckPondRd.setLotAllowedOnWeekendsNSP("true");
        duckPondRd.setLotTimesAllowedOnWeekdaysSP("12am - 11:59pm");
        duckPondRd.setlotTimesAllowedOnWeekdaysNSP("5pm - 7am");
        duckPondRd.setLotTimesAllowedOnWeekendsSP("12am - 11:59pm");
        duckPondRd.setlotTimesAllowedOnWeekendsNSP("12am - 11:59pm");
        duckPondRd.setlotMinLongitude(-80.430871f);
        duckPondRd.setlotMaxLongitude(-80.426051f);
        duckPondRd.setlotMinLatitude(37.219227f);
        duckPondRd.setlotMaxLatitude(37.221598f);
        //duckPondRd.setPicture(//Blob file);
        parkingLots.add(duckPondRd);

        //Litton Reaves Extension Parking Lot C/G Section.
        ParkingLot LttnRvsExtCG = new ParkingLot();
        LttnRvsExtCG.setName("Litton Reaves Extension Parking Lot C/G Section");
        LttnRvsExtCG.setLotAllowedOnWeekdaysSP("true");
        LttnRvsExtCG.setLotAllowedOnWeekdaysNSP("true");
        LttnRvsExtCG.setLotAllowedOnWeekendsSP("true");
        LttnRvsExtCG.setLotAllowedOnWeekendsNSP("true");
        LttnRvsExtCG.setLotTimesAllowedOnWeekdaysSP("12am - 11:59pm");
        LttnRvsExtCG.setlotTimesAllowedOnWeekdaysNSP("5pm - 7am");
        LttnRvsExtCG.setLotTimesAllowedOnWeekendsSP("12am - 11:59pm");
        LttnRvsExtCG.setlotTimesAllowedOnWeekendsNSP("12am - 11:59pm");
        LttnRvsExtCG.setlotMinLongitude(-80.427007f);
        LttnRvsExtCG.setlotMaxLongitude(-80.426372f);
        LttnRvsExtCG.setlotMinLatitude(37.222632f);
        LttnRvsExtCG.setlotMaxLatitude(37.223067f);
        //LttnRvsExtCG.setPicture(//Blob file);
        parkingLots.add(LttnRvsExtCG);

        //Litton Reaves Extension Parking Lot F/S/V Section.
        ParkingLot LttnRvsExtFSV = new ParkingLot();
        LttnRvsExtFSV.setName("Litton Reaves Extension Parking Lot F/S/V Section");
        LttnRvsExtFSV.setLotAllowedOnWeekdaysSP("true");
        LttnRvsExtFSV.setLotAllowedOnWeekdaysNSP("true");
        LttnRvsExtFSV.setLotAllowedOnWeekendsSP("true");
        LttnRvsExtFSV.setLotAllowedOnWeekendsNSP("true");
        LttnRvsExtFSV.setLotTimesAllowedOnWeekdaysSP("5pm - 7pm");
        LttnRvsExtFSV.setlotTimesAllowedOnWeekdaysNSP("5pm - 7am");
        LttnRvsExtFSV.setLotTimesAllowedOnWeekendsSP("12am - 11:59pm");
        LttnRvsExtFSV.setlotTimesAllowedOnWeekendsNSP("12am - 11:59pm");
        LttnRvsExtFSV.setlotMinLongitude(-80.426387f);
        LttnRvsExtFSV.setlotMaxLongitude(-80.425545f);
        LttnRvsExtFSV.setlotMinLatitude(37.222804f);
        LttnRvsExtFSV.setlotMaxLatitude(37.223177f);
        //LttnRvsExtFSV.setPicture(//Blob file);
        parkingLots.add(LttnRvsExtFSV);

        //Litton Reaves Parking Lot C/G Section.
        ParkingLot LttnRvsLotCG = new ParkingLot();
        LttnRvsLotCG.setName("Litton Reaves Parking Lot C/G Section");
        LttnRvsLotCG.setLotAllowedOnWeekdaysSP("true");
        LttnRvsLotCG.setLotAllowedOnWeekdaysNSP("true");
        LttnRvsLotCG.setLotAllowedOnWeekendsSP("true");
        LttnRvsLotCG.setLotAllowedOnWeekendsNSP("true");
        LttnRvsLotCG.setLotTimesAllowedOnWeekdaysSP("12am - 11:59pm");
        LttnRvsLotCG.setlotTimesAllowedOnWeekdaysNSP("5pm - 7am");
        LttnRvsLotCG.setLotTimesAllowedOnWeekendsSP("12am - 11:59pm");
        LttnRvsLotCG.setlotTimesAllowedOnWeekendsNSP("12am - 11:59pm");
        LttnRvsLotCG.setlotMinLongitude(-80.426921f);
        LttnRvsLotCG.setlotMaxLongitude(-80.426276f);
        LttnRvsLotCG.setlotMinLatitude(37.221292f);
        LttnRvsLotCG.setlotMaxLatitude(37.223121f);
        //LttnRvsLotCG.setPicture(//Blob file);
        parkingLots.add(LttnRvsLotCG);

        //Litton Reaves Parking Lot F/S/V Section.
        ParkingLot LttnRvsLotFSV = new ParkingLot();
        LttnRvsLotFSV.setName("Litton Reaves Parking Lot F/S/V Section");
        LttnRvsLotFSV.setLotAllowedOnWeekdaysSP("true");
        LttnRvsLotFSV.setLotAllowedOnWeekdaysNSP("true");
        LttnRvsLotFSV.setLotAllowedOnWeekendsSP("true");
        LttnRvsLotFSV.setLotAllowedOnWeekendsNSP("true");
        LttnRvsLotFSV.setLotTimesAllowedOnWeekdaysSP("5pm - 7am");
        LttnRvsLotFSV.setlotTimesAllowedOnWeekdaysNSP("5pm - 7am");
        LttnRvsLotFSV.setLotTimesAllowedOnWeekendsSP("12am - 11:59pm");
        LttnRvsLotFSV.setlotTimesAllowedOnWeekendsNSP("12am - 11:59pm");
        LttnRvsLotFSV.setlotMinLongitude(-80.425942f);
        LttnRvsLotFSV.setlotMaxLongitude(-80.425484f);
        LttnRvsLotFSV.setlotMinLatitude(37.220905f);
        LttnRvsLotFSV.setlotMaxLatitude(37.222153f);
        //LttnRvsLotFSV.setPicture(//Blob file);
        parkingLots.add(LttnRvsLotFSV);

        //Insert all parking lots.
        try {
            insertParkingLots(squires);
            insertParkingLots(surge);
            insertParkingLots(owens);
            insertParkingLots(dietrick);
            insertParkingLots(bballPracExt);
            insertParkingLots(bballPracFac);
            insertParkingLots(pricesFork5);
            insertParkingLots(pricesFork6);
            insertParkingLots(duckPondRd);
            insertParkingLots(LttnRvsExtCG);
            insertParkingLots(LttnRvsExtFSV);
            insertParkingLots(LttnRvsLotCG);
            insertParkingLots(LttnRvsLotFSV);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

