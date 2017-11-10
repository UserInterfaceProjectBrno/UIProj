package com.example.nasty.UIProject;

import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

import static java.lang.Integer.parseInt;

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class Payment_Fragment extends Fragment {
    View Mview;

    static FirebaseDatabase OrderDatabase = FirebaseDatabase.getInstance();
    final static DatabaseReference OrderRef = OrderDatabase.getReference().child("Orders");
    TelephonyManager mngr;

    Button payButton;
    RadioButton creditCardRButton;
    RadioButton cashRButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mview = inflater.inflate(R.layout.activity_payment, container, false);

        payButton = (Button) Mview.findViewById(R.id.PayAndLockButt);
        creditCardRButton = (RadioButton) Mview.findViewById(R.id.creditCardRadioButt);
        cashRButton = (RadioButton) Mview.findViewById(R.id.cashRadioButt);

        ActivityCompat.requestPermissions(getActivity(),
                new String[]{"android.permission.READ_PHONE_STATE"},
                1);
        mngr = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        final String imei = mngr.getDeviceId();

        creditCardRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cashRButton.setChecked(false);
            }
        });

        cashRButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creditCardRButton.setChecked(false);
            }
        });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:

                                OrderRef.child(imei).child("Locked").setValue("Yes");

                                if (creditCardRButton.isChecked() || cashRButton.isChecked())
                                   getFragmentManager().beginTransaction().replace(R.id.content_frame, new Cart_Fragment()).commit();

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("PAY \nAre You Sure? ").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }
        });
        return Mview;
    }

}
