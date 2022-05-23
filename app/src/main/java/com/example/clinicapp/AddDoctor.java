package com.example.clinicapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;


public class AddDoctor extends Fragment {



    public AddDoctor() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }
   Spinner spinner;
    EditText fname;
    EditText lname;
    EditText email;
    EditText phone;
    RadioButton Rmale;
    RadioButton Rfemale;
    String specialization;
    String gender;
    Button bt;
     FirebaseFirestore firebaseFirestore;
    CollectionReference cr;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_doctor, container, false);

        initViews(v);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0){
                    Toast.makeText(getContext(), "please enter the specialization", Toast.LENGTH_SHORT).show();

                }else{
                  specialization=spinner.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                storedata(v);
            }
        });

        return v;
    }
    private void initViews(View v) {
        fname = v.findViewById(R.id.fName_ET);
        lname = v.findViewById(R.id.lName_ET);
        email = v.findViewById(R.id.email);
        phone = v.findViewById(R.id.phone_ET);
        spinner= v.findViewById(R.id.spec);
        Rmale = v.findViewById(R.id.male_RB);
        Rfemale = v.findViewById(R.id.female_RB);
        firebaseFirestore = FirebaseFirestore.getInstance();
        bt=v.findViewById(R.id.adddoctor);

    }
    private void storedata(View v){
        String fnamec = fname.getText().toString();
        String lnamec = lname.getText().toString();

         String emailc=email.getText().toString();
           String phonec= phone.getText().toString();
           String genderc="";
        if (Rmale.isChecked()) {

            genderc = "Male";


        } else if (Rfemale.isChecked()) {
            genderc = "Female";
        }

        Doctor doctor =new Doctor(fnamec,lnamec,specialization,emailc,phonec,genderc);
        HashMap<String, String> DoctorData = new HashMap<>();



        DoctorData.put("firstName", fnamec);
        DoctorData.put("secondName", lnamec);
        DoctorData.put("specialization", specialization);
        DoctorData.put("phone", phonec);
        DoctorData.put("email", emailc);
        DoctorData.put("gender", genderc);

        cr = FirebaseFirestore.getInstance().collection("doctor");

        firebaseFirestore.collection("doctor").add(DoctorData).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(v.getContext(), "Doctor Inserted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(v.getContext(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}