package com.example.clinicapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecycleView_ViewHolder_Home extends RecyclerView.ViewHolder {
    ImageView homeRV_IV;
    TextView homeRV_TV;

    public RecycleView_ViewHolder_Home(@NonNull View itemView) {
        super(itemView);

        homeRV_IV = itemView.findViewById(R.id.homeRV_IV);
        homeRV_TV = itemView.findViewById(R.id.homeRV_TV);
    }
}
