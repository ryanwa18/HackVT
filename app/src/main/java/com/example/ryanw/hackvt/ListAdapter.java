package com.example.ryanw.hackvt;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryanw.hackvt.ParkingLot;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>
{
    Context context;
    ArrayList<ParkingLot> dataList = new ArrayList<>();
    LayoutInflater inflater;


    public ListAdapter(Context context, ArrayList<ParkingLot> dataList)
    {
        this.context = context;
        this.dataList = dataList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       return null;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        holder.parking_lot.setText(dataList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        TextView parking_lot;

        public ListViewHolder(View itemView) {
            super(itemView);
        }
    }
}