package com.example.nasty.UIProject;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Cart_Fragment extends Fragment {

    static FirebaseDatabase OrderDatabase = FirebaseDatabase.getInstance();
    final static DatabaseReference OrderRef = OrderDatabase.getReference().child("Orders");

    public String data;
    public int children;
    Object X;
    String[] split;
    View Mview;
    int flag=0;
    TextView CartText;
    TelephonyManager mngr;
    String imei = "null";
    Button CartClearButt;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mview = inflater.inflate(R.layout.activity_cart, container, false);
        CartClearButt = (Button) Mview.findViewById(R.id.ClearButt);
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
                if (X != null)
                {
                    split = X.toString().substring(1, X.toString().length() - 1).split(",");
                    for (String i : split) {
                        CartText.setText(i + "\n" + CartText.getText().toString());
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ///////////////////////////////////////////////////////////////////////////////////////////////
        OrderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(Objects.equals(dataSnapshot.child(imei).child("Locked").getValue(), "Yes"))
                {
                    flag = 1;
                }
                else
                {
                    flag = 0;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////
        CartClearButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(flag == 0)
                {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                OrderRef.child(imei).child("Products").removeValue();
                                getFragmentManager().beginTransaction().replace(R.id.content_frame,new Cart_Fragment()).commit();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
            else if(flag == 1)
                {
                    CartClearButt.setEnabled(false);
                    CartClearButt.setBackgroundColor(getResources().getColor(R.color.ButtonColor));
                    CartClearButt.setText(" --  ORDER LOCKED  -- ");
                }

        }
        });
        return Mview;
    }

}
