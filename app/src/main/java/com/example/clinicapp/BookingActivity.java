package com.example.clinicapp;

import static com.example.clinicapp.AppointmentFragment.getClinic;
import static com.example.clinicapp.RecycleView_Adapter_Appointment.getTime;
import static com.example.clinicapp.RecycleView_Adapter_Doctor.getDocName;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class BookingActivity extends AppCompatActivity {
    String date;
    private CalendarView calendarView;
    FirebaseAuth auth;
    FirebaseFirestore fireStore;
    private Button book_BTN;
    String clinicName;
    String doctorName;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);
        auth = FirebaseAuth.getInstance();
        fireStore = FirebaseFirestore.getInstance();

        insertTime();
        receiveDate();
    }

    private void insertTime() {
        ArrayList<Time> times = new ArrayList<>();
        times.add(new Time("2:00 PM"));
        times.add(new Time("2:30 PM"));
        times.add(new Time("3:00 PM"));
        times.add(new Time("3:30 PM"));
        times.add(new Time("4:00 PM"));
        times.add(new Time("4:30 PM"));
        times.add(new Time("5:00 PM"));
        times.add(new Time("5:30 PM"));
        times.add(new Time("6:00 PM"));
        times.add(new Time("6:30 PM"));
        times.add(new Time("7:00 PM"));
        times.add(new Time("7:30 PM"));
        times.add(new Time("8:00 PM"));
        times.add(new Time("8:30 PM"));

        RecyclerView appointmentRV = findViewById(R.id.appointmentRV);
        RecycleView_Adapter_Appointment recycleView_adapter_Appointment = new RecycleView_Adapter_Appointment(times);
        appointmentRV.setAdapter(recycleView_adapter_Appointment);
        RecyclerView.LayoutManager lm = new LinearLayoutManager(BookingActivity.this, RecyclerView.HORIZONTAL, false);
        appointmentRV.setLayoutManager(lm);

    }

    private void receiveDate() {
        book_BTN = findViewById(R.id.book_BTN);
        Calendar calender = Calendar.getInstance();
        int d = calender.get(Calendar.DAY_OF_MONTH);
        int m = calender.get(Calendar.MONTH);
        int y = calender.get(Calendar.YEAR);

        date = d + "-" + (m + 1) + "-" + y;

        clinicName = getClinic;
        doctorName = getDocName;


        calendarView = findViewById(R.id.calender_CV);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                if ((month + 1) == (m + 1)) {
                    if (day >= d) {
                        Toast.makeText(getApplicationContext(), day + "-" + (month + 1) + "-" + year + "", Toast.LENGTH_SHORT).show();
                        date = day + "-" + (month + 1) + "-" + year;
                    } else {
                        Toast.makeText(getApplicationContext(), "try to select date after " + d + "-" + (m + 1) + "-" + y, Toast.LENGTH_LONG).show();
                    }
                } else if ((month + 1) >= (m + 1)) {
                    Toast.makeText(getApplicationContext(), day + "-" + (month + 1) + "-" + year + "", Toast.LENGTH_SHORT).show();
                    date = day + "-" + (month + 1) + "-" + year;
                } else if (year >= y) {
                    Toast.makeText(getApplicationContext(), day + "-" + (month + 1) + "-" + year + "", Toast.LENGTH_SHORT).show();
                    date = day + "-" + (month + 1) + "-" + year;
                } else {
                    Toast.makeText(getApplicationContext(), "try to select date after " + d + "-" + (m + 1) + "-" + y, Toast.LENGTH_LONG).show();
                }
            }
        });

        book_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HashMap<String, String> patientDate = new HashMap<>();
                user = auth.getCurrentUser();
                if (user != null) {
                    String UID = user.getUid();
                    FirebaseFirestore.getInstance().collection("patient");
                    BookingAppointment bookingAppointment = new BookingAppointment(getDocName, clinicName, date, getTime);
                    patientDate.put("UID", UID);
                    patientDate.put("doctorName", bookingAppointment.getDoctorName());
                    patientDate.put("clinicName", bookingAppointment.getClinicName());
                    patientDate.put("time", bookingAppointment.getTime());
                    patientDate.put("date", bookingAppointment.getDate());
                    fireStore.collection("date").add(patientDate).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                onBackPressed();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                } else {
                    Toast.makeText(getApplicationContext(), "Add Failed", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

}