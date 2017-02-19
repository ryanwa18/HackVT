package com.example.ryanw.hackvt;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryanw.hackvt.ParkingLot;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder>
{
    private List<ParkingLot> parkingLotsList;
    private final static int FADE_DURATION = 1000;
    private boolean hasUserPass;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView lot;

        public ViewHolder(View view) {
            super(view);
            lot = (TextView) view.findViewById(R.id.name);
        }
    }

    public void setHasUserPass(boolean hasUserPass)
    {
        this.hasUserPass = hasUserPass;
    }

    public ListAdapter(List<ParkingLot> parkingLotsList) {
        this.parkingLotsList = parkingLotsList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parking_lot_row, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ParkingLot lot = parkingLotsList.get(position);
        holder.lot.setText(lot.getName());
        setFadeAnimation(holder.itemView);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    @Override
    public int getItemCount() {
        return parkingLotsList.size();
    }
}