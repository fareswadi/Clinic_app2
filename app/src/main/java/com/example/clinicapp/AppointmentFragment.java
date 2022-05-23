package com.example.clinicapp;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AppointmentFragment extends Fragment {
    private RecyclerView doctor_RV;
    private CardView heartCV;
    private CardView brainCV;
    private CardView earCV;
    private CardView kidneyCV;
    private CardView lungCV;
    private CardView eyeCV;
    private CardView noseCV;
    private CardView toothCV;
    private TextView chooseDoc;

    private TextView heartTV;
    private TextView brainTV;
    private TextView earTV;
    private TextView noseTV;
    private TextView kidneyTV;
    private TextView lungTV;
    private TextView eyeTV;
    private TextView toothTV;

    static String getClinic;

    public AppointmentFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_appointment, container, false);
        heartCV = v.findViewById(R.id.heartCV);
        brainCV = v.findViewById(R.id.brainCV);
        earCV = v.findViewById(R.id.earCV);
        kidneyCV = v.findViewById(R.id.kidneyCV);
        lungCV = v.findViewById(R.id.lungCV);
        eyeCV = v.findViewById(R.id.eyeCV);
        noseCV = v.findViewById(R.id.noseCV);
        toothCV = v.findViewById(R.id.toothCV);
        chooseDoc = v.findViewById(R.id.chooseDoc);


        heartTV = v.findViewById(R.id.heart_TV);
        brainTV = v.findViewById(R.id.brain_TV);
        earTV = v.findViewById(R.id.ear_TV);
        noseTV = v.findViewById(R.id.nose_TV);
        kidneyTV = v.findViewById(R.id.kidney_TV);
        lungTV = v.findViewById(R.id.lung_TV);
        eyeTV = v.findViewById(R.id.eye_TV);
        toothTV = v.findViewById(R.id.tooth_TV);

        doctor_RV = v.findViewById(R.id.doctor_RV);
        ArrayList<Doctor> doctors = new ArrayList<>();
        Doctor doc1 = new Doctor("Ali", "Ahmed", R.drawable.ic_doctor_male);
        Doctor doc2 = new Doctor("Ola", "Sami", R.drawable.ic_doctor_female);
        Doctor doc3 = new Doctor("Ahmed", "Arafat", R.drawable.ic_doctor_male);
        Doctor doc4 = new Doctor("Alaa", "Lola", R.drawable.ic_doctor_female);
        Doctor doc5 = new Doctor("Mohammed", "Samir", R.drawable.ic_doctor_male);
        Doctor doc6 = new Doctor("Huda", "Salem", R.drawable.ic_doctor_female);
        Doctor doc7 = new Doctor("Alia", "Hassan", R.drawable.ic_doctor_male);
        Doctor doc8 = new Doctor("Soha", "Rami", R.drawable.ic_doctor_female);

        doctors.add(doc1);
        doctors.add(doc2);
        doctors.add(doc3);
        doctors.add(doc4);
        doctors.add(doc5);
        doctors.add(doc6);
        doctors.add(doc7);
        doctors.add(doc8);

        RecycleView_Adapter_Doctor recycleView_Adapter_doctor = new RecycleView_Adapter_Doctor(doctors);
        doctor_RV.setAdapter(recycleView_Adapter_doctor);

        heartCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClinic= heartTV.getText().toString();

                chooseDoc.setText("choose Your Doctor");
                RecyclerView.LayoutManager lm = new LinearLayoutManager(v.getContext(), RecyclerView.HORIZONTAL, false);
                doctor_RV.setLayoutManager(lm);

            }
        });
        brainCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClinic= brainTV.getText().toString();
                chooseDoc.setText("choose Your Doctor");
                RecyclerView.LayoutManager lm = new LinearLayoutManager(v.getContext(), RecyclerView.HORIZONTAL, false);
                doctor_RV.setLayoutManager(lm);
            }
        });
        earCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClinic= earTV.getText().toString();
                chooseDoc.setText("choose Your Doctor");
                RecyclerView.LayoutManager lm = new LinearLayoutManager(v.getContext(), RecyclerView.HORIZONTAL, false);
                doctor_RV.setLayoutManager(lm);
            }
        });
        kidneyCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClinic= kidneyTV.getText().toString();
                chooseDoc.setText("choose Your Doctor");
                RecyclerView.LayoutManager lm = new LinearLayoutManager(v.getContext(), RecyclerView.HORIZONTAL, false);
                doctor_RV.setLayoutManager(lm);
            }
        });
        lungCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClinic= lungTV.getText().toString();
                chooseDoc.setText("choose Your Doctor");
                RecyclerView.LayoutManager lm = new LinearLayoutManager(v.getContext(), RecyclerView.HORIZONTAL, false);
                doctor_RV.setLayoutManager(lm);
            }
        });
        eyeCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClinic= eyeTV.getText().toString();
                chooseDoc.setText("choose Your Doctor");
                RecyclerView.LayoutManager lm = new LinearLayoutManager(v.getContext(), RecyclerView.HORIZONTAL, false);
                doctor_RV.setLayoutManager(lm);
            }
        });
        noseCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClinic= noseTV.getText().toString();
                chooseDoc.setText("choose Your Doctor");
                RecyclerView.LayoutManager lm = new LinearLayoutManager(v.getContext(), RecyclerView.HORIZONTAL, false);
                doctor_RV.setLayoutManager(lm);
            }
        });
        toothCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getClinic= toothTV.getText().toString();
                chooseDoc.setText("choose Your Doctor");
                RecyclerView.LayoutManager lm = new LinearLayoutManager(v.getContext(), RecyclerView.HORIZONTAL, false);
                doctor_RV.setLayoutManager(lm);
            }
        });


        return v;
    }

}