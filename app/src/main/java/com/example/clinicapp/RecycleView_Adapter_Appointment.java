package com.example.clinicapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecycleView_Adapter_Appointment extends RecyclerView.Adapter<RecycleView_ViewHolder_Appointment> {
    Context context;
    ArrayList<Time> times;
    static String getTime = "";

    public RecycleView_Adapter_Appointment(ArrayList<Time> times) {
        this.times = times;
    }

    @NonNull
    @Override
    public RecycleView_ViewHolder_Appointment onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_appointment, parent, false);
        return new RecycleView_ViewHolder_Appointment(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleView_ViewHolder_Appointment holder, int position) {
        Time t = times.get(position);
        holder.appointmentTimeTV.setText(t.getTime());
        getTime = holder.appointmentTimeTV.getText().toString();
        holder.appointmentTimeTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTime = holder.appointmentTimeTV.getText().toString();
           //     Toast.makeText(view.getContext(), getTime, Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return times.size();
    }
}
