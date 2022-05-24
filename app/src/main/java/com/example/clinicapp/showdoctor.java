package com.example.clinicapp;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;

public class showdoctor extends Fragment {

    RecyclerView doctorRecyclerView;
    ArrayList<Doctor>doctorArray=new ArrayList<Doctor>();
    HashSet<Doctor> doctorHashSet = new HashSet<>();
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;



    public showdoctor() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }




    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        firebaseFirestore =FirebaseFirestore.getInstance();
        View v =inflater.inflate(R.layout.showdoctor, container, false);
         doctorRecyclerView = v.findViewById(R.id.showDoctor);
        RecycleView_Adapter_Doctor recycleView_adapter_doctor = new RecycleView_Adapter_Doctor(doctorArray);
        getUserAppointments(v);
        doctorRecyclerView.setAdapter(recycleView_adapter_doctor);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(v.getContext(), RecyclerView.HORIZONTAL, false);
        doctorRecyclerView.setLayoutManager(lm);

        return v;
    }
    private void getUserAppointments(View v) {

        firebaseFirestore.collection("doctor").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot q : task.getResult()) {
                                String gender;
                                String fname;
                                String lname;
                                String spec;
                                String phone;
                                int img;
                                fname = q.getData().get("firstName").toString();
                                lname = q.getData().get("secondName").toString();
                                gender = q.getData().get("gender").toString();
                                spec = q.getData().get("specialization").toString();
                                phone = q.getData().get("phone").toString();
                                if(gender.equalsIgnoreCase("male")){
                                    img=R.drawable.ic_doctor_male;
                                }else{
                                    img=R.drawable.ic_doctor_female;
                                }

                               doctorArray.add(new Doctor(fname,lname,spec,phone,img));

                            }
                            doctorHashSet.addAll(doctorArray);
                            doctorArray.clear();
                            doctorArray.addAll(doctorHashSet);
                            RecycleView_Adapter_Doctor recycleView_adapter_doctor = new RecycleView_Adapter_Doctor(doctorArray);
                            doctorRecyclerView.setAdapter(recycleView_adapter_doctor);
                            RecyclerView.LayoutManager lm2 = new LinearLayoutManager(v.getContext());
                            doctorRecyclerView.setLayoutManager(lm2);
                        }
                    }
                });
    }
}
