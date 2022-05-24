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
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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
    FragmentTransaction fragmentTransaction ;

    @Override
    public void onBindViewHolder(@NonNull RecycleView_ViewHolder_Doctor holder, int position) {
        Doctor d = doctors.get(position);
        holder.doctorimage.setImageResource(d.getImg());
        holder.doctorname.setText(d.getFirstName() + " " + d.getLastName());
        holder.doctorspecial.setText(d.getSpecialization());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Bundle args = new Bundle();
                args.putString("phone",d.getPhone());
                Details_doctor details_doctor =new Details_doctor();
                details_doctor.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, details_doctor).addToBackStack(null).commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }
}
