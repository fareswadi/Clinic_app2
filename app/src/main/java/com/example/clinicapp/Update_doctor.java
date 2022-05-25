package com.example.clinicapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.util.Log;
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
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    Button updatebt;
    String specialization;
    String gender;
    Button bt;
    FirebaseFirestore firebaseFirestore;
    CollectionReference cr;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View v = inflater.inflate(R.layout.update_doctor, container, false);
        initViews(v);
        Bundle args = getArguments();
        String phonet = args.getString("phone");
        Log.d("ff", "onCreateView: " + phonet);
        getdoctor(v, phonet);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Toast.makeText(getContext(), "please enter the specialization", Toast.LENGTH_SHORT).show();

                } else {
                    specialization = spinner.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        updatebt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatedoctor(v, phonet);
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .add(android.R.id.content, new showdoctor()).addToBackStack(null).commit();
            }
        });
        return v;
    }

    private void initViews(View v) {
        fname = v.findViewById(R.id.fName_ET);
        lname = v.findViewById(R.id.lName_ET);
        email = v.findViewById(R.id.email);
        phone = v.findViewById(R.id.phone_ET);
        spinner = v.findViewById(R.id.spec);
        Rmale = v.findViewById(R.id.male_RB);
        Rfemale = v.findViewById(R.id.female_RB);
        firebaseFirestore = FirebaseFirestore.getInstance();
        bt = v.findViewById(R.id.adddoctor);
        updatebt = v.findViewById(R.id.updatedoctor);


    }

    String spec;
    String fnamet;
    String lnamet;
    String emailt;
    String ref;
    String genderU;
    private void getdoctor(View v, String phonet) {

        firebaseFirestore.collection("doctor")
                .whereEqualTo("phone", phonet)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {

                                ref = queryDocumentSnapshot.getId();
                                Log.d("rrr", "onComplete: " + ref);
                                String gender = queryDocumentSnapshot.getData().get("gender").toString();
                                genderU = gender;
                                if (gender.equalsIgnoreCase("male")) {
                                    Rmale.setChecked(true);
                                } else {
                                    Rfemale.setChecked(true);
                                }
                                fnamet = queryDocumentSnapshot.getData().get("firstName").toString();
                                fname.setText(queryDocumentSnapshot.getData().get("firstName").toString());
                                lnamet = queryDocumentSnapshot.getData().get("secondName").toString();
                                lname.setText(queryDocumentSnapshot.getData().get("secondName").toString());
                                emailt = queryDocumentSnapshot.getData().get("email").toString();
                                email.setText(queryDocumentSnapshot.getData().get("email").toString());
                                spec = queryDocumentSnapshot.getData().get("specialization").toString();
                                ArrayList<String> arrayList = new ArrayList<>();
                                arrayList.add("chose the special");
                                arrayList.add("heart");
                                arrayList.add("brain");
                                arrayList.add("ear");
                                arrayList.add("nose");
                                arrayList.add("eyes");
                                arrayList.add("lung");
                                arrayList.add("tooth");
                                for (int i = 0; i < arrayList.size(); i++) {
                                    if (arrayList.get(i).equalsIgnoreCase(spec)) {
                                        spinner.setSelection(i);
                                        break;
                                    }
                                }
                                phone.setText(phonet);

                            }
                        }
                    }
                });
    }

    private void updatedoctor(View v, String phonet) {
        Map<String, String> doctorMap = new HashMap<>();
        doctorMap.put("firstName", fname.getText().toString());
        doctorMap.put("secondName", lname.getText().toString());
        doctorMap.put("email", email.getText().toString());
        doctorMap.put("phone", phone.getText().toString());
        doctorMap.put("specialization", specialization);


        if (Rmale.isChecked()) {

            doctorMap.put("gender", "male");
            genderU="male";


        } else if (Rfemale.isChecked()) {
            doctorMap.put("gender", "female");
            genderU="female";
        }

        firebaseFirestore.collection("doctor").document(ref).
                update("firstName", fname.getText().toString()
                        , "secondName", lname.getText().toString(),
                        "email", email.getText().toString(),
                        "phone", phone.getText().toString(),
                        "gender",genderU,
                        "specialization", specialization);

    }
}