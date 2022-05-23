package com.example.clinicapp;


import static com.example.clinicapp.PersonalData_Fragment.ID;
import static com.example.clinicapp.PersonalData_Fragment.Day;
import static com.example.clinicapp.PersonalData_Fragment.fName;
import static com.example.clinicapp.PersonalData_Fragment.gender;
import static com.example.clinicapp.PersonalData_Fragment.lName;
import static com.example.clinicapp.PersonalData_Fragment.Month;
import static com.example.clinicapp.PersonalData_Fragment.phone;
import static com.example.clinicapp.PersonalData_Fragment.Year;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;
import java.util.HashMap;

public class RegisterFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private EditText createPasswordET;
    private EditText confirmPasswordET;
    //    private TextView confirmTV;
    CollectionReference cr;
    private EditText createEmailET;
    private EditText confirmEmailET;
    private TextView login_TV;
    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        login_TV = v.findViewById(R.id.login_TV);
        Button RegisterBtn = v.findViewById(R.id.RegisterBtn);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        initViews(v);

        RegisterBtn.setOnClickListener(view -> {
            register(v);
        });

        login_TV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginFragment loginFragment = new LoginFragment();

            }
        });
        return v;
    }

    private void initViews(View v) {
        createPasswordET = v.findViewById(R.id.createPassword_ET);
        confirmPasswordET = v.findViewById(R.id.confirmPassword_ET);
        // confirmTV = v.findViewById(R.id.confirm_TV);
        createEmailET = v.findViewById(R.id.createEmail_ET);
        confirmEmailET = v.findViewById(R.id.confirmEmail_ET);
    }

    private void register(View v) {
        String password = createPasswordET.getText().toString().trim();
        String confirmPassword = confirmPasswordET.getText().toString().trim();
        String email = createEmailET.getText().toString().trim();
        String confirmEmail = confirmEmailET.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(v.getContext(), "Email can't be empty", Toast.LENGTH_SHORT).show();
        } else if (!confirmEmail.equals(email)) {
            Toast.makeText(v.getContext(), "Email and confirm email not same!", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 8) {
            Toast.makeText(v.getContext(), "Password should contain at least 8 digit", Toast.LENGTH_SHORT).show();
        } else if (!confirmPassword.equals(password)) {
            Toast.makeText(v.getContext(), "Password and confirm password not same!", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Patient patient = new Patient(fName, lName, ID, phone, email, gender, Day, Month, Year);
                        Toast.makeText(v.getContext(), "Created Account", Toast.LENGTH_SHORT).show();
                        FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            String UID = user.getUid();
                            HashMap<String, String> patientData = new HashMap<>();

                            patientData.put("UID", UID);
                            patientData.put("firstName", patient.getFirstName());
                            patientData.put("secondName", patient.getLastName());
                            patientData.put("id", patient.getID());
                            patientData.put("phone", patient.getPhone());
                            patientData.put("email", patient.getEmail());
                            patientData.put("gender", patient.getGender());
                            patientData.put("DOB", patient.getDay() + "/" + patient.getMonth() + "/" + patient.getYear() + "");
                            cr = FirebaseFirestore.getInstance().collection("patient");

                            firebaseFirestore.collection("Patient").add(patientData).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(v.getContext(), "User Inserted", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(v.getContext(), "Failed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            Intent intent = new Intent(v.getContext(), MainUI.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(v.getContext(), "an error occurred", Toast.LENGTH_SHORT).show();
                        }
                    }

                }
            });
        }
    }
}