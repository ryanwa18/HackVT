package com.example.ryanw.hackvt;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    RecyclerView recyclerView;
    DatabaseHelper dbHelper;
    ListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        dbHelper = DatabaseHelper.getInstance(getApplicationContext());
        dbHelper.createParkingLots();

        ArrayList<ParkingLot> lots = dbHelper.getAllParkingLots();
        System.out.println("Name: " + lots.get(0).getName());

        String max_latitude = String.format("%.6f", lots.get(0).getlotMaxLatitude());
        System.out.println("Max Latitude: " + max_latitude);
        String min_latitude = String.format("%.6f", lots.get(0).getlotMinLatitude());
        System.out.println("Min Latitude: " + min_latitude);
        String max_longitude = String.format("%.6f", lots.get(0).getlotMaxLongitude());
        System.out.println("Max Longitude: " + max_latitude);
        String min_longitude = String.format("%.6f", lots.get(0).getlotMinLongitude());
        System.out.println("Min Longitude: " + min_longitude);

        System.out.println("Allowed on weekdays with pass: " + lots.get(0).getLotAllowedOnWeekdaysSP());
        System.out.println("Allowed on weekdays without pass: " + lots.get(0).getLotAllowedOnWeekdaysNSP());
        System.out.println("Allowed on weekends with pass: " + lots.get(0).getLotAllowedOnWeekendsSP());
        System.out.println("Allowed on weekends without pass: " + lots.get(0).getLotAllowedOnWeekendsNSP());

        System.out.println("Time ranges on weekdays with pass: " + lots.get(0).getLotTimesAllowedOnWeekdaysSP());
        System.out.println("Time ranges on weekdays without pass: " + lots.get(0).getLotTimesAllowedOnWeekdaysNSP());
        System.out.println("Time ranges on weekends with pass: " + lots.get(0).getLotTimesAllowedOnWeekendsSP());
        System.out.println("Time ranges on weekends without pass: " + lots.get(0).getLotTimesAllowedOnWeekendsNSP());

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
