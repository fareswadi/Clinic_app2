package com.example.clinicapp;

import static com.example.clinicapp.R.id.fab;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


public class Details_doctor extends Fragment {



    public Details_doctor() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
 FirebaseFirestore firebaseFirestore;
    ImageView img;
    TextView spec,name,email,phonetx;
    FloatingActionButton fab;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.details_doctor, container, false);
        Bundle args=getArguments();
        String phone=args.getString("phone");
        Log.d("phone", "onCreateView: "+phone);
        firebaseFirestore = FirebaseFirestore.getInstance();
        img=v.findViewById(R.id.img);
        spec=v.findViewById(R.id.spec);
        name=v.findViewById(R.id.name);
        email=v.findViewById(R.id.email);
        phonetx=v.findViewById(R.id.phone);
        fab=v.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                Bundle args = new Bundle();
                args.putString("phone",phone);
                Update_doctor update_doctor=new Update_doctor();
                update_doctor.setArguments(args);
                activity.getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, update_doctor).addToBackStack(null).commit();

            }
        });
        getdoctor(v,phone);
        return v;
    }
    private void getdoctor(View v, String phone) {
        firebaseFirestore.collection("doctor")
                .whereEqualTo("phone", phone)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                String gender=queryDocumentSnapshot.getData().get("gender").toString() ;
                                if(gender.equalsIgnoreCase("male")){
                                    img.setImageResource(R.drawable.ic_doctor_male);
                                }else{
                                    img.setImageResource(R.drawable.ic_doctor_female);
                                }
                                spec.setText(queryDocumentSnapshot.getData().get("specialization").toString() );
                                name.setText(queryDocumentSnapshot.getData().get("firstName").toString() + " " + queryDocumentSnapshot.getData().get("secondName") );
                                email.setText(queryDocumentSnapshot.getData().get("email").toString() );
                                phonetx.setText(phone );
                            }
                        }
                    }
                });
    }
}