package com.example.clinicapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragment extends Fragment {
    private FirebaseAuth firebaseAuth;
    private TextView createAccount;
    private AppCompatButton loginBtn;
    private AppCompatEditText userET;
    private AppCompatEditText passET;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_log_in, container, false);
        firebaseAuth = FirebaseAuth.getInstance();
        initViews(v);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonalData_Fragment fragment = new PersonalData_Fragment();
                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.FCV, fragment);
                fragmentTransaction.commit();
            }
        });
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(v);
            }
        });
        return v;
    }

    private void initViews(View v) {
        createAccount = v.findViewById(R.id.createAccount);
        loginBtn = v.findViewById(R.id.loginBtn);
        userET = v.findViewById(R.id.user_ET);
        passET = v.findViewById(R.id.pass_ET);
    }

    private void login(View v) {
        String password = passET.getText().toString().trim();
        String email = userET.getText().toString().trim();

        if (email.isEmpty()) {
            Toast.makeText(v.getContext(), "Email required", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(v.getContext(), "Password required", Toast.LENGTH_SHORT).show();
        } else {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        sharedPref(email, v);
                        Toast.makeText(v.getContext(), "Sign in successfully", Toast.LENGTH_SHORT).show();
                    } else if (email.equals("a") && password.equals("a")) {
                        startActivity(new Intent(v.getContext(), AdminActivity.class));
                    } else {
                        Toast.makeText(v.getContext(), "something error, check email and password", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private void sharedPref(String email, View v) {
        SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("loginData", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isSharedPref", true);
        editor.putString("UserEmail", email);
        editor.apply();
        Intent intent = new Intent(v.getContext(), MainUI.class);
        startActivity(intent);
    }


}
