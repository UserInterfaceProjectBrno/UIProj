package com.example.nasty.UIProject;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Cart_Fragment extends Fragment {

    static FirebaseDatabase OrderDatabase = FirebaseDatabase.getInstance();
    final static DatabaseReference OrderRef = OrderDatabase.getReference().child("Orders");

    public String data;
    public int children;
    Object X;
    String [] Xx;
    String[] split;
    View Mview;
    TextView CartText;
    TelephonyManager mngr;
    String imei = "null";

    ConstraintLayout LayoutC;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mview = inflater.inflate(R.layout.activity_cart, container, false);
        LayoutC = (ConstraintLayout) Mview.findViewById(R.id.CartBox);
        CartText = (TextView) Mview.findViewById(R.id.CartText);
        //////////////////////////////////////////////////////////////////////////////////////////
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{"android.permission.READ_PHONE_STATE"},
                1);
        mngr = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        final String imei = mngr.getDeviceId();
        ////////////////////////////////////////////////////////////////////////////////////////////
        OrderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                X=dataSnapshot.child(imei).child("Products").getValue();
                children = (int) dataSnapshot.getChildrenCount();
                split = X.toString().substring(1, X.toString().length() - 1).split(",");
                for (String i: split )
                {
                    CartText.setText(i+"\n"+CartText.getText().toString());
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return Mview;
    }

}
