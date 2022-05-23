package com.example.clinicapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class AdminActivity extends AppCompatActivity  {
    ImageView add,show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        add=findViewById(R.id.add);
        show=findViewById(R.id.show2);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new AddDoctor()).commit();
            }
        });
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("l", "onClick: 111");
                getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new showdoctor()).commit();
            }
        });
    }
}