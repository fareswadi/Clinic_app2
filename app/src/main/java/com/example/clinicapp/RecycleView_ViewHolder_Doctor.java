package com.example.clinicapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleView_ViewHolder_Doctor extends RecyclerView.ViewHolder {
    ImageView doctorimage;
    TextView doctorname;
    TextView doctorspecial;
    public RecycleView_ViewHolder_Doctor(@NonNull View itemView) {
        super(itemView);
        doctorimage = itemView.findViewById(R.id.doctorimage);
        doctorname = itemView.findViewById(R.id.doctorname);
        doctorspecial =itemView.findViewById(R.id.doctorspecial);

    }
}
