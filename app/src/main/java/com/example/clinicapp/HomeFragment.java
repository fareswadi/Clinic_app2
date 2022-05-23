package com.example.clinicapp;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;


public class HomeFragment extends Fragment {
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    private TextView userName;
    SharedPreferences sharedPreferences;
    private RecyclerView bookingRecyclerView;

    ArrayList<BookingAppointment> bookingAppointments = new ArrayList<>();
    HashSet<BookingAppointment> bookingAppointmentHashSet = new HashSet<>();

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        bookingRecyclerView = v.findViewById(R.id.bookingRV);
        sharedPreferences = getActivity().getPreferences(v.getContext().MODE_PRIVATE);

        avilableClinic(v);
        getUser(v);
        getUserAppointments(v);
        return v;
    }

    private void avilableClinic(View v) {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        String day = sdf.format(d);

        ArrayList<ClinicName> clinicNames = new ArrayList<>();

        if (day.toLowerCase().equals("saturday") || day.toLowerCase().equals("tuesday")) {
            clinicNames.add(new ClinicName("Heart", R.drawable.ic_heart_svgrepo_com));
            clinicNames.add(new ClinicName("Nose", R.drawable.ic_nose_svgrepo_com));
            clinicNames.add(new ClinicName("Tooth", R.drawable.ic_tooth_svgrepo_com));
        } else if (day.toLowerCase().equals("sunday") || day.toLowerCase().equals("wednesday")) {
            clinicNames.add(new ClinicName("Lung", R.drawable.ic_lung_svgrepo_com));
            clinicNames.add(new ClinicName("Ear", R.drawable.ic_ear_fill_svgrepo_com));
            clinicNames.add(new ClinicName("Eyes", R.drawable.ic_eye_svgrepo_com));
        } else if (day.toLowerCase().equals("monday") || day.toLowerCase().equals("thursday")) {
            clinicNames.add(new ClinicName("Kidney", R.drawable.ic_kidneys_kidney_svgrepo_com));
            clinicNames.add(new ClinicName("Brain", R.drawable.ic_brain_svgrepo_com));
            clinicNames.add(new ClinicName("Tooth", R.drawable.ic_tooth_svgrepo_com));
        } else {
            clinicNames.add(new ClinicName("Weekend", R.drawable.closed));
        }


        RecyclerView clinicRecyclerView = v.findViewById(R.id.ClinicRV);
        RecycleView_Adapter_Home recycleView_adapter_home = new RecycleView_Adapter_Home(clinicNames);
        clinicRecyclerView.setAdapter(recycleView_adapter_home);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(v.getContext(), RecyclerView.HORIZONTAL, false);
        clinicRecyclerView.setLayoutManager(lm);


    }

    private void getUser(View v) {
        firebaseFirestore.collection("Patient")
                .whereEqualTo("UID", firebaseAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        userName = v.findViewById(R.id.userName);
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot queryDocumentSnapshot : task.getResult()) {
                                userName.setText(queryDocumentSnapshot.getData().get("firstName").toString() + " " + queryDocumentSnapshot.getData().get("secondName"));
                            }
                        }
                    }
                });
    }

    private void getUserAppointments(View v) {
        firebaseFirestore.collection("date")
                .whereEqualTo("UID", firebaseAuth.getCurrentUser().getUid())
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot q : task.getResult()) {
                                String clinic;
                                String doctor;
                                String date;
                                String time;

                                doctor = q.getData().get("doctorName").toString();
                                clinic = q.getData().get("clinicName").toString();
                                date = q.getData().get("date").toString();
                                time = q.getData().get("time").toString();

                                bookingAppointments.add(new BookingAppointment(doctor, clinic, date, time));

                            }
                            bookingAppointmentHashSet.addAll(bookingAppointments);
                            bookingAppointments.clear();
                            bookingAppointments.addAll(bookingAppointmentHashSet);
                            RecycleView_Adapter_Booking recycleView_adapter_booking = new RecycleView_Adapter_Booking(bookingAppointments);
                            bookingRecyclerView.setAdapter(recycleView_adapter_booking);
                            RecyclerView.LayoutManager lm2 = new LinearLayoutManager(v.getContext());
                            bookingRecyclerView.setLayoutManager(lm2);
                        }
                    }
                });
    }

    private void assign(View v) {

    }

}
