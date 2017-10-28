package com.example.nasty.UIProject;

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
import android.widget.ImageButton;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;


import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Order_Fragment extends Fragment {
    View Mview;
    ImageButton MealButton;
    TelephonyManager mngr;
    FirebaseDatabase OrderDatabase = FirebaseDatabase.getInstance();
    final DatabaseReference OrderRef = OrderDatabase.getReference().child("Orders");
    String imei="null";

    int Day = Calendar.getInstance().get(Calendar.DATE);
    int Month = Calendar.getInstance().get(Calendar.MONTH) +1;
    int Year = Calendar.getInstance().get(Calendar.YEAR);
    int Hour = Calendar.getInstance().getTime().getHours();
    int Minute = Calendar.getInstance().getTime().getMinutes();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mview = inflater.inflate(R.layout.order_activity, container, false);
        /////////////////////////////////IMEI PERMISSION///////////////////////////////////////////
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{"android.permission.READ_PHONE_STATE"},
                1);
        mngr = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        imei = mngr.getDeviceId();


        MealButton = (ImageButton) Mview.findViewById(R.id.MealButt);

        MealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Order("Fish","100","Milk","10");
            }
        });


        return Mview;
    }


    public void Order(String Food,String Food_Quantity,String Drink,String Drink_Quantity)
    {
        OrderRef.child(imei).child("Date").setValue(Day+"/"+Month+"/"+Year+","+Integer.toString(Hour)+":"+Integer.toString(Minute));
        OrderRef.child(imei).child("Drinks").child(Food).setValue(Food_Quantity);
        OrderRef.child(imei).child("Food").child(Drink).setValue(Drink_Quantity);
    }

}
