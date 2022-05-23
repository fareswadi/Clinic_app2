package com.example.clinicapp;


import android.app.DatePickerDialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Calendar;


public class RecycleView_Adapter_Doctor extends RecyclerView.Adapter<RecycleView_ViewHolder_Doctor> {
    Context context;
    ArrayList<Doctor> doctors = new ArrayList<>();

   static String getDocName;
    public RecycleView_Adapter_Doctor(ArrayList<Doctor> doctors) {
        this.doctors = doctors;

    }

    @NonNull
    @Override
    public RecycleView_ViewHolder_Doctor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_doctor, parent, false);
        return new RecycleView_ViewHolder_Doctor(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleView_ViewHolder_Doctor holder, int position) {
        Doctor d = doctors.get(position);
        holder.doctorimage.setImageResource(d.getImg());
        holder.doctorname.setText(d.getFirstName() + " " + d.getLastName());
        holder.doctorspecial.setText(d.getSpecialization());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent = new Intent(view.getContext(), BookingActivity.class);
               // getDocName=holder.appointmentRV_TV.getText().toString();
               // intent.putExtra("doctor", d);
               // view.getContext().startActivity(intent);

//                // Toast.makeText(view.getContext(), "Clicked", Toast.LENGTH_SHORT).show();
//                Calendar cal = Calendar.getInstance();
//                int year = cal.get(Calendar.YEAR);
//                int month = cal.get(Calendar.MONTH);
//                int day = cal.get(Calendar.DAY_OF_MONTH);
//
//                DatePickerDialog dialog = new DatePickerDialog(view.getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePicker, year, month, day);
//                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//                dialog.show();
//
//                datePicker = new DatePickerDialog.OnDateSetListener() {
//                    @Override
//                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//
//                        date = (year + " - " + month + " - " + day + "");
//                        AppointmentFragment fragment = new AppointmentFragment();
//                        Bundle bundle = new Bundle();
//                        bundle.putString("date", date);
//                        fragment.setArguments(bundle);
//
//                    }
//                };

            }
        });

    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }
}
