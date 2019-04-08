package com.example.cyparking.parkinglog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cyparking.R;

import java.util.List;

//Very helpful
//https://guides.codepath.com/android/using-the-recyclerview

public class ParkingLogAdapter extends RecyclerView.Adapter<ParkingLogAdapter.ParkingLogViewHolder> {

    private List<ParkingLog> mLogs;

    public static class ParkingLogViewHolder extends RecyclerView.ViewHolder {
        public TextView spotTextView;
        public TextView locationTextView;

        public ParkingLogViewHolder(View v) {
            super(v);
            spotTextView =  (TextView) v.findViewById(R.id.log_spot);
            locationTextView = (TextView)  v.findViewById(R.id.log_location);
        }
    }

    public ParkingLogAdapter(List<ParkingLog> mLogs) {
        this.mLogs = mLogs;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ParkingLogViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View v = inflater.inflate(R.layout.item_parking_log, parent, false);
        return new ParkingLogViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ParkingLogViewHolder viewHolder, int position) {
        // Get the data model based on position
        ParkingLog log = mLogs.get(position);

        // Set item views based on your views and data model
        TextView textView = viewHolder.spotTextView;
        textView.setText(log.getSpaceID());
        TextView textView2 = viewHolder.locationTextView;
        textView2.setText(log.getLotID());

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mLogs.size();
    }
}
