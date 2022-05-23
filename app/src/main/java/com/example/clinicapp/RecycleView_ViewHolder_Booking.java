package com.example.clinicapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleView_ViewHolder_Booking extends RecyclerView.ViewHolder {
    TextView timeRV_TV;
    TextView clinicNameRV_TV;
    TextView doctorNameRV_TV;
    TextView dateRV_TV;

    public RecycleView_ViewHolder_Booking(@NonNull View itemView) {
        super(itemView);

        timeRV_TV = itemView.findViewById(R.id.timeRV_TV);
        clinicNameRV_TV = itemView.findViewById(R.id.clinicNameRV_TV);
        doctorNameRV_TV = itemView.findViewById(R.id.doctorNameRV_TV);
        dateRV_TV = itemView.findViewById(R.id.dateRV_TV);

    }
}
