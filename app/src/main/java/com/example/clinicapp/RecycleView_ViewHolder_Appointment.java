package com.example.clinicapp;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleView_ViewHolder_Appointment extends RecyclerView.ViewHolder {
    TextView appointmentTimeTV;

    public RecycleView_ViewHolder_Appointment(@NonNull View itemView) {
        super(itemView);
        appointmentTimeTV = itemView.findViewById(R.id.appointmentTime_TV);
    }
}
