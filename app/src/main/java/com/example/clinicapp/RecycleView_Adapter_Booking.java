package com.example.clinicapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;

public class RecycleView_Adapter_Booking extends RecyclerView.Adapter<RecycleView_ViewHolder_Booking> {
    Context context;
    ArrayList<BookingAppointment> bookingAppointments = new ArrayList<>();

    public RecycleView_Adapter_Booking(ArrayList<BookingAppointment> bookingAppointments) {
        this.bookingAppointments = bookingAppointments;
    }

    @NonNull
    @Override
    public RecycleView_ViewHolder_Booking onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_booking, parent, false);
        return new RecycleView_ViewHolder_Booking(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleView_ViewHolder_Booking holder, int position) {

        BookingAppointment bA = bookingAppointments.get(position);
        holder.clinicNameRV_TV.setText(bA.getClinicName());
        holder.dateRV_TV.setText(bA.getDate());
        holder.doctorNameRV_TV.setText(bA.getDoctorName());
        holder.timeRV_TV.setText(bA.getTime());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //  Intent intent = new Intent(view.getContext(), BookingActivity.class);
                //  intent.putExtra("appointment", bA);
                //  view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingAppointments.size();
    }
}
