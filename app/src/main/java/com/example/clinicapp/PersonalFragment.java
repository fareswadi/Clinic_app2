package com.example.clinicapp;

import android.app.FragmentManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;

public class PersonalFragment extends Fragment {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    FragmentTransaction ft;

    private TextView UserName,logout;
    private String gender;
    private CircleImageView circleImageView;
    private RadioButton arabic;
    private RadioButton english;
    SharedPreferences sharedPreferences;


    public PersonalFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_personal, container, false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        ft = getFragmentManager().beginTransaction();
        sharedPreferences= getContext().getSharedPreferences("myclinic",getContext().MODE_PRIVATE);
        getUser(v);
        changeLanguage(v);
        logout=v.findViewById(R.id.logout);
      logout.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              SharedPreferences.Editor custome=sharedPreferences.edit();
              custome.putString("email","e");
              custome.commit();
              Intent intent = new Intent(v.getContext(), MainActivity.class);
              startActivity(intent);
          }
      });
        return v;
    }

    private void getUser(View v) {
        firebaseFirestore.collection("Patient")
                .whereEqualTo("UID", firebaseAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        UserName = v.findViewById(R.id.UserName);
                        circleImageView = v.findViewById(R.id.circleImageView);



                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                UserName.setText(queryDocumentSnapshot.getData().get("firstName").toString() + " " + queryDocumentSnapshot.getData().get("secondName"));
                                gender = queryDocumentSnapshot.getData().get("gender").toString();
                                getGender(v);
                            }
                        }

                    }
                });
    }

    private void getGender(View v) {
        if (gender.equals("Male")) {
            circleImageView.setImageResource(R.drawable.male);
        } else if (gender.equals("Female")) {
            circleImageView.setImageResource(R.drawable.female);
        }
    }

    private void changeLanguage(View v) {
        arabic = v.findViewById(R.id.arabicRadio);
        english = v.findViewById(R.id.englishRadio);
        arabic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setLocal("ar");
                Toast.makeText(v.getContext(), "Arabic Language ", Toast.LENGTH_SHORT).show();
            }
        });

        english.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                setLocal("en");
                Toast.makeText(v.getContext(), "English Language", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void setLocal(String str) {
        Locale mylocale = new Locale(str);
        PersonalFragment personalFragment = new PersonalFragment();
        DisplayMetrics dm = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        configuration.locale = mylocale;
        getResources().updateConfiguration(configuration, dm);
        ft.replace(R.id.FCV, personalFragment).commit();
    }
}