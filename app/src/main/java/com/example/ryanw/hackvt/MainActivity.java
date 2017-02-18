package com.example.ryanw.hackvt;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private DatabaseHelper dbHelper;
    private List<ParkingLot> parkingLotsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        dbHelper = DatabaseHelper.getInstance(getApplicationContext());
        dbHelper.createParkingLots();

        parkingLotsList = dbHelper.getAllParkingLots();

        mAdapter = new ListAdapter(parkingLotsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        /*
        String name = lots.get(0).getName() + "\n";
        String max_latitude = String.format("Max Latitude: %.6f\n", lots.get(0).getlotMaxLatitude());
        String min_latitude = String.format("Min latitude: %.6f\n", lots.get(0).getlotMinLatitude());
        String max_longitude = String.format("Max longitude: %.6f\n", lots.get(0).getlotMaxLongitude());
        String min_longitude = String.format("Min Longitude: %.6f\n", lots.get(0).getlotMinLongitude());

        System.out.println("Allowed on weekdays with pass: " + lots.get(0).getLotAllowedOnWeekdaysSP());
        System.out.println("Allowed on weekdays without pass: " + lots.get(0).getLotAllowedOnWeekdaysNSP());
        System.out.println("Allowed on weekends with pass: " + lots.get(0).getLotAllowedOnWeekendsSP());
        System.out.println("Allowed on weekends without pass: " + lots.get(0).getLotAllowedOnWeekendsNSP());

        System.out.println("Time ranges on weekdays with pass: " + lots.get(0).getLotTimesAllowedOnWeekdaysSP());
        System.out.println("Time ranges on weekdays without pass: " + lots.get(0).getLotTimesAllowedOnWeekdaysNSP());
        System.out.println("Time ranges on weekends with pass: " + lots.get(0).getLotTimesAllowedOnWeekendsSP());
        System.out.println("Time ranges on weekdays without pass: " + lots.get(0).getLotTimesAllowedOnWeekendsNSP());*/

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
