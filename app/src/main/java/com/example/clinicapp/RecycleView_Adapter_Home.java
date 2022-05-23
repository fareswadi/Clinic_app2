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

public class RecycleView_Adapter_Home extends RecyclerView.Adapter<RecycleView_ViewHolder_Home> {
    Context context;
    ArrayList<ClinicName> clinicNames;

    public RecycleView_Adapter_Home(ArrayList<ClinicName> clinicNames) {
        this.clinicNames = clinicNames;

    }

    @NonNull
    @Override
    public RecycleView_ViewHolder_Home onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycleview_home, parent, false);
        return new RecycleView_ViewHolder_Home(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleView_ViewHolder_Home holder, int position) {
        ClinicName clname = clinicNames.get(position);
        holder.homeRV_IV.setImageResource(clname.getImage());
        holder.homeRV_TV.setText(clname.getName());

    }

    @Override
    public int getItemCount() {
        return clinicNames.size();
    }
}
