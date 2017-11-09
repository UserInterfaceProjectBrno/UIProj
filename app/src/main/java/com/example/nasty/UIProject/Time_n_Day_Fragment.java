package com.example.nasty.UIProject;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Objects;

public class Time_n_Day_Fragment extends Fragment {

    final FirebaseDatabase OrderDB = FirebaseDatabase.getInstance();
    final DatabaseReference OrderRef = OrderDB.getReference().child("Orders");
    View Mview;
    TextView MinutesText;

    Button plus_1;
    Button plus_5;
    Button plus_10;
    Button minus_1;
    Button minus_5;
    Button minus_10;
    Button Submit;

    int Hour = Calendar.getInstance().getTime().getHours();
    int Minute = Calendar.getInstance().getTime().getMinutes();

    int numberofMin = 0;
    String LockStatus;

    TelephonyManager mngr;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mview = inflater.inflate(R.layout.activity_time_n_day, container, false);

        /////////////////////////////////IMEI PERMISSION///////////////////////////////////////////////////////

        ActivityCompat.requestPermissions(getActivity(),
                new String[]{"android.permission.READ_PHONE_STATE"},
                1);
        mngr = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        final String imei = mngr.getDeviceId();

        /////////////////////////////////Initialization of IDs //////////////////////////////////////////////

        MinutesText = (TextView) Mview.findViewById(R.id.Minutes_Text);
        numberofMin = Integer.parseInt(MinutesText.getText().toString());

        plus_1 = (Button) Mview.findViewById(R.id.plus_1);
        plus_5 = (Button) Mview.findViewById(R.id.plus_5);
        plus_10 = (Button) Mview.findViewById(R.id.plus_10);
        minus_1 = (Button) Mview.findViewById(R.id.minus_1);
        minus_5 = (Button) Mview.findViewById(R.id.minus_5);
        minus_10 = (Button) Mview.findViewById(R.id.minus_10);
        Submit = (Button) Mview.findViewById(R.id.SubmitMin);

        plus_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberofMin++;
                MinutesText.setText(numberofMin + " m.");
                if (numberofMin > 240) {
                    numberofMin = 240;
                    MinutesText.setText("4 h.");
                }
                if (numberofMin == 60) {
                    numberofMin = 60;
                    MinutesText.setText("1 h.");
                }
                if (numberofMin == 90) {
                    numberofMin = 90;
                    MinutesText.setText("1.5 h.");
                }
                if (numberofMin == 120) {
                    numberofMin = 120;
                    MinutesText.setText("2 h.");
                }
                if (numberofMin == 150) {
                    numberofMin = 150;
                    MinutesText.setText("2.5 h.");
                }
                if (numberofMin == 180) {
                    numberofMin = 180;
                    MinutesText.setText("3 h.");
                }
                if (numberofMin == 210) {
                    numberofMin = 210;
                    MinutesText.setText("3.5 h.");
                }

            }
        });
        plus_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberofMin += 5;
                MinutesText.setText(numberofMin + " m.");
                if (numberofMin > 240) {
                    numberofMin = 240;
                    MinutesText.setText("4 h.");
                }
                if (numberofMin == 60) {
                    numberofMin = 60;
                    MinutesText.setText("1 h.");
                }
                if (numberofMin == 90) {
                    numberofMin = 90;
                    MinutesText.setText("1.5 h.");
                }
                if (numberofMin == 120) {
                    numberofMin = 120;
                    MinutesText.setText("2 h.");
                }
                if (numberofMin == 150) {
                    numberofMin = 150;
                    MinutesText.setText("2.5 h.");
                }
                if (numberofMin == 180) {
                    numberofMin = 180;
                    MinutesText.setText("3 h.");
                }
                if (numberofMin == 210) {
                    numberofMin = 210;
                    MinutesText.setText("3.5 h.");
                }
            }
        });
        plus_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberofMin += 10;
                MinutesText.setText(numberofMin + " m.");
                if (numberofMin > 240) {
                    numberofMin = 240;
                    MinutesText.setText("4 h.");
                }
                if (numberofMin == 60) {
                    numberofMin = 60;
                    MinutesText.setText("1 h.");
                }
                if (numberofMin == 90) {
                    numberofMin = 90;
                    MinutesText.setText("1.5 h.");
                }
                if (numberofMin == 120) {
                    numberofMin = 120;
                    MinutesText.setText("2 h.");
                }
                if (numberofMin == 150) {
                    numberofMin = 150;
                    MinutesText.setText("2.5 h.");
                }
                if (numberofMin == 180) {
                    numberofMin = 180;
                    MinutesText.setText("3 h.");
                }
                if (numberofMin == 210) {
                    numberofMin = 210;
                    MinutesText.setText("3.5 h.");
                }
            }
        });
        minus_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberofMin--;
                MinutesText.setText(numberofMin + " m.");
                if (numberofMin < 15) {
                    numberofMin = 15;
                    MinutesText.setText("0 m.");
                }
                if (numberofMin == 60) {
                    numberofMin = 60;
                    MinutesText.setText("1 h.");
                }
                if (numberofMin == 90) {
                    numberofMin = 90;
                    MinutesText.setText("1.5 h.");
                }
                if (numberofMin == 120) {
                    numberofMin = 120;
                    MinutesText.setText("2 h.");
                }
                if (numberofMin == 150) {
                    numberofMin = 150;
                    MinutesText.setText("2.5 h.");
                }
                if (numberofMin == 180) {
                    numberofMin = 180;
                    MinutesText.setText("3 h.");
                }
                if (numberofMin == 210) {
                    numberofMin = 210;
                    MinutesText.setText("3.5 h.");
                }
            }
        });
        minus_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberofMin -= 5;
                MinutesText.setText(numberofMin + " m.");
                if (numberofMin < 15) {
                    numberofMin = 15;
                    MinutesText.setText("0 m.");
                }
                if (numberofMin == 60) {
                    numberofMin = 60;
                    MinutesText.setText("1 h.");
                }
                if (numberofMin == 90) {
                    numberofMin = 90;
                    MinutesText.setText("1.5 h.");
                }
                if (numberofMin == 120) {
                    numberofMin = 120;
                    MinutesText.setText("2 h.");
                }
                if (numberofMin == 150) {
                    numberofMin = 150;
                    MinutesText.setText("2.5 h.");
                }
                if (numberofMin == 180) {
                    numberofMin = 180;
                    MinutesText.setText("3 h.");
                }
                if (numberofMin == 210) {
                    numberofMin = 210;
                    MinutesText.setText("3.5 h.");
                }
            }
        });
        minus_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberofMin -= 10;
                MinutesText.setText(numberofMin + " m.");
                if (numberofMin < 15) {
                    numberofMin = 15;
                    MinutesText.setText("15 m.");
                }
                if (numberofMin == 60) {
                    numberofMin = 60;
                    MinutesText.setText("1 h.");
                }
                if (numberofMin == 90) {
                    numberofMin = 90;
                    MinutesText.setText("1.5 h.");
                }
                if (numberofMin == 120) {
                    numberofMin = 120;
                    MinutesText.setText("2 h.");
                }
                if (numberofMin == 150) {
                    numberofMin = 150;
                    MinutesText.setText("2.5 h.");
                }
                if (numberofMin == 180) {
                    numberofMin = 180;
                    MinutesText.setText("3 h.");
                }
                if (numberofMin == 210) {
                    numberofMin = 210;
                    MinutesText.setText("3.5 h.");
                }
            }
        });
        OrderRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LockStatus = dataSnapshot.child(imei).child("Locked").getValue().toString();
                if (Objects.equals(LockStatus, "Yes")) {
                    Toast.makeText(getContext().getApplicationContext(), "ORDER IS LOCKED!", Toast.LENGTH_LONG).show();
                    getFragmentManager().beginTransaction().replace(R.id.content_frame, new Cart_Fragment()).commit();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!Objects.equals(LockStatus, "Yes")) {
                    OrderRef.child(imei).child("ReserveTime").setValue("In " + numberofMin + " Minutes From " + Hour + ":" + Minute + " .");
                    Toast.makeText(getContext().getApplicationContext(), "In " + numberofMin + " Minutes From " + Hour + ":" + Minute + " .", Toast.LENGTH_LONG).show();
                    getFragmentManager().beginTransaction().replace(R.id.content_frame, new Order_Fragment()).commit();
                }

            }
        });


        return Mview;
    }
}