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
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Order_Fragment extends Fragment {
    public View Mview;

    ImageButton FishButton;
    ImageButton MealButton;
    ImageButton SaladButton;
    ImageButton PizzaButton;
    ImageButton AlcoholicDrinksButton;
    ImageButton OtherDrinksButton;

    Button LockOrder;

    TextView LockStatus;

    TelephonyManager mngr;
    String imei="null";

    String LockedState;



    FirebaseDatabase OrderDatabase = FirebaseDatabase.getInstance();
    final DatabaseReference OrderRef = OrderDatabase.getReference().child("Orders");


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
        /////////////////////////////////Initialization of IDs //////////////////////////////////////////////
        MealButton = (ImageButton) Mview.findViewById(R.id.MealButt);
        FishButton  = (ImageButton)   Mview.findViewById(R.id.FishButt);
        SaladButton = (ImageButton) Mview.findViewById(R.id.SaladButt);
        PizzaButton = (ImageButton) Mview.findViewById(R.id.PizzaButt);
        AlcoholicDrinksButton = (ImageButton) Mview.findViewById(R.id.AlcoholButt);
        OtherDrinksButton = (ImageButton) Mview.findViewById(R.id.OtherDrinkButt);

        LockOrder   = (Button) Mview.findViewById(R.id.LockButt);

        LockStatus  = (TextView) Mview.findViewById(R.id.LockStatus);


/////////////////////////////////////////////////////////////////////////////////////////////////////////////
       OrderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                LockedState = dataSnapshot.child(imei).child("Locked").getValue().toString();
                if(Objects.equals(LockedState, "Yes"))
                {
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////
        LockOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderRef.child(imei).child("Locked").setValue("Yes");
            }
        });

        FishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               getFragmentManager().beginTransaction().replace(R.id.content_frame
                            ,new Fish_Fragment(),"Fish")
                            .commit();

            }
        });

        MealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.content_frame
                        , new Meal_Fragment())
                        .commit();
            }
        });

        SaladButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.content_frame
                        , new Salad_Fragment())
                        .commit();
            }
        });

        PizzaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.content_frame
                        , new Pizza_Fragment())
                        .commit();
            }
        });

        AlcoholicDrinksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.content_frame
                        , new AlcoholicDrinks_Fragment())
                        .commit();
            }
        });

        OtherDrinksButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.content_frame
                        , new OtherDrinks_Fragment())
                        .commit();
            }
        });

        return Mview;
    }


    public void fadeOutAndHideImage(final View img) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(500);

        fadeOut.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                img.setVisibility(View.GONE);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });

        img.startAnimation(fadeOut);
    }

    public void fadeIn(final View img) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(400);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                img.setVisibility(View.VISIBLE);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });

        img.startAnimation(fadeIn);
    }
}
