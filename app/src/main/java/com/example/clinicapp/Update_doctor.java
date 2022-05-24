package com.example.clinicapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class Update_doctor extends Fragment {




    public Update_doctor() {
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


        View v= inflater.inflate(R.layout.update_doctor, container, false);
        initViews(v);
        Bundle args=getArguments();
       String phonet=args.getString("phone");
       Log.d("ff", "onCreateView: "+phonet);
       getdoctor(v,phonet);
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
    private void getdoctor(View v, String phonet) {
        firebaseFirestore.collection("doctor")
                .whereEqualTo("phone", phonet)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                String gender=queryDocumentSnapshot.getData().get("gender").toString() ;
                                if(gender.equalsIgnoreCase("male")){
                                    Rmale.setChecked(true);
                                }else {
                                    Rfemale.setChecked(true);
                                }
                                fname.setText(queryDocumentSnapshot.getData().get("firstName").toString());
                                lname.setText(queryDocumentSnapshot.getData().get("secondName").toString());
                                email.setText(queryDocumentSnapshot.getData().get("email").toString() );

                                phone.setText(phonet);
                            }
                        }
                    }
                });
    }
}