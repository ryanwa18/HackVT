package com.example.ryanw.hackvt;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private DatabaseHelper dbHelper;
    private List<ParkingLot> parkingLotsList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private boolean userHasPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Shows the dialog to check if a user has a pass or not.
        showUserPassDialog();
        //Builds the database.
        databaseBuilder();
        //Creation of the recycler view.
        createRecyclerView();

        dbHelper.close();

    }

    /**
     * Builds the database.
     */
    private void databaseBuilder()
    {
        dbHelper = DatabaseHelper.getInstance(getApplicationContext());
        dbHelper.createParkingLots();

        parkingLotsList = dbHelper.getAllParkingLots();
        Collections.sort(parkingLotsList);
    }

    /**
     * Creates the recyclerview to hold the parking lots.
     */
    private void createRecyclerView(){
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(this));
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        int itemPosition = recyclerView.getChildLayoutPosition(view);
                        ParkingLot item = parkingLotsList.get(itemPosition);
                        if (item != null)
                            showParkingLotDialog(item);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
        mAdapter = new ListAdapter(parkingLotsList);
        mAdapter.setHasUserPass(userHasPass);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(mAdapter);

    }

    /**
     * Shows the details for the parking lot.
     * @param parkingLot
     */
    public void showParkingLotDialog(ParkingLot parkingLot)
    {
        AlertDialog.Builder lotInformation = new AlertDialog.Builder(this);
        lotInformation.setTitle(parkingLot.getName());
        final TextView tv = (TextView) findViewById(R.id.lotInfo);
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        Toast t = Toast.makeText(this, currentDateTimeString, Toast.LENGTH_SHORT);
        t.show();
        String info = "";
        if (userHasPass == true)
        {
            info += "Weekdays: " + parkingLot.getLotTimesAllowedOnWeekdaysSP() + "\n";
            info += "Weekends: " + parkingLot.getLotTimesAllowedOnWeekendsSP();
        }
        else
        {
            info += "Weekdays: " + parkingLot.getLotTimesAllowedOnWeekdaysNSP() + "\n";
            info += "Weekends: " + parkingLot.getLotTimesAllowedOnWeekendsNSP();
        }
        lotInformation.setMessage(info);
        lotInformation.show();
    }

    /**
     * Shows the inital dialog box on creation of the application.
     */
    public void showUserPassDialog()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Parks and Tech");
        builder.setMessage("Do you own a parking pass?");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Do nothing but close the dialog
                userHasPass = true;
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                userHasPass = false;
            }
        });
        builder.show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private boolean getUserPassStatus()
    {
        return userHasPass;
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
