package com.example.nasty.UIProject;

import com.example.nasty.UIProject.Cart.*;
import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Order_Fragment extends Fragment {
    View Mview;

    Button FishButton;
    Button MealButton;

    TelephonyManager mngr;
    String imei="null";




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mview = inflater.inflate(R.layout.order_activity, container, false);

        FishButton = (Button) Mview.findViewById(R.id.FishButt);

        FishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.content_frame
                        , new Fish_Fragment())
                        .commit();
            }
        });
        return Mview;
    }
}
