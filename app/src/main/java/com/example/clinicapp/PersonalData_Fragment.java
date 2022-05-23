package com.example.clinicapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;

public class PersonalData_Fragment extends Fragment {
    private EditText fNameET;
    private EditText lNameET;
    private EditText idET;
    private EditText phoneET;
    private EditText dbET;
    private RadioGroup rg;
    private RadioButton maleRB;
    private RadioButton femaleRB;
    private Button continueBtn;

    static String fName;
    static String lName;
    static String ID;
    static String phone;
    static String birthDate;
    static String gender;


    static int Year;
    static int Day;
    static int Month;

    static int isTouched = 0;

    String date;
    DatePickerDialog.OnDateSetListener datePicker;

    public PersonalData_Fragment() {

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal_data_, container, false);
        initViews(v);
        continueBtn = v.findViewById(R.id.continueBtn);
        continueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register(v);
            }
        });

        dbET.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH + 1);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(view.getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth, datePicker, year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();

                datePicker = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        date = (day + " / " + (month + 1) + " / " + year + "");
                        Day = day;
                        Month = month + 1;
                        Year = year;
                        dbET.setText(date);
                    }
                };

            }
        });


        return v;
    }

    private void initViews(View v) {
        fNameET = v.findViewById(R.id.fName_ET);
        lNameET = v.findViewById(R.id.lName_ET);
        idET = v.findViewById(R.id.id_ET);
        phoneET = v.findViewById(R.id.phone_ET);
        dbET = v.findViewById(R.id.db_ET);
        rg = v.findViewById(R.id.rg);
        maleRB = v.findViewById(R.id.male_RB);
        femaleRB = v.findViewById(R.id.female_RB);

    }

    private void register(View v) {
        fName = fNameET.getText().toString();
        lName = lNameET.getText().toString();
        ID = idET.getText().toString();
        phone = phoneET.getText().toString();
        birthDate = dbET.getText().toString();
        if (maleRB.isChecked()) {
            gender = "Male";
        } else if (femaleRB.isChecked()) {
            gender = "Female";
        }
        if (fName.isEmpty()) {
            Toast.makeText(getContext(), "First Name can't be empty", Toast.LENGTH_SHORT).show();
        } else if (lName.isEmpty()) {
            Toast.makeText(getContext(), "Last Name can't be empty", Toast.LENGTH_SHORT).show();
        } else if (ID.isEmpty()) {
            Toast.makeText(getContext(), "ID can't be empty", Toast.LENGTH_SHORT).show();
        } else if (phone.isEmpty()) {
            Toast.makeText(getContext(), "phone can't be empty", Toast.LENGTH_SHORT).show();
        } else if (birthDate.isEmpty()) {
            Toast.makeText(getContext(), "Birth Date can't be empty", Toast.LENGTH_SHORT).show();
        } else {
            RegisterFragment fragment = new RegisterFragment();
            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FCV, fragment);
            fragmentTransaction.commit();
        }
    }


}