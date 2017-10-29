package com.example.nasty.UIProject;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.Calendar;

import static java.lang.Integer.parseInt;

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class Fish_Fragment extends Fragment {
    View Mview;

    TextView FirstText;
    TextView SecondText;
    TextView ThirdText;

    ImageButton FirstArrowUp;
    ImageButton FirstArrowDown;

    ImageButton SecondArrowUp;
    ImageButton SecondArrowDown;

    ImageButton ThirdArrowUp;
    ImageButton ThirdArrowDown;


    TelephonyManager mngr;
    String imei="null";

    FirebaseDatabase OrderDatabase = FirebaseDatabase.getInstance();
    final DatabaseReference OrderRef = OrderDatabase.getReference().child("Orders");
    int Day = Calendar.getInstance().get(Calendar.DATE);
    int Month = Calendar.getInstance().get(Calendar.MONTH) +1;
    int Year = Calendar.getInstance().get(Calendar.YEAR);
    int Hour = Calendar.getInstance().getTime().getHours();
    int Minute = Calendar.getInstance().getTime().getMinutes();

    Button Submit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mview = inflater.inflate(R.layout.activity_fish, container, false);

        /////////////////////////////////IMEI PERMISSION///////////////////////////////////////////
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{"android.permission.READ_PHONE_STATE"},
                1);
        mngr = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        imei = mngr.getDeviceId();
        ////////////////////////////////////////////////////////////////////////////////////////////

        FirstText  = (TextView) Mview.findViewById(R.id.FirstRowText);
        SecondText = (TextView) Mview.findViewById(R.id.SecondRowText);
        ThirdText  = (TextView) Mview.findViewById(R.id.ThirdRowText);

        FirstArrowUp = (ImageButton) Mview.findViewById(R.id.FirstArrowUp);
        FirstArrowDown = (ImageButton) Mview.findViewById(R.id.FirstArrowDown);

        SecondArrowUp = (ImageButton) Mview.findViewById(R.id.SecondArrowUp);
        SecondArrowDown = (ImageButton) Mview.findViewById(R.id.SecondArrowDown);

        ThirdArrowUp = (ImageButton) Mview.findViewById(R.id.ThirdArrowUp);
        ThirdArrowDown = (ImageButton) Mview.findViewById(R.id.ThirdArrowDown);

        Submit = (Button) Mview.findViewById(R.id.SubmitButtFish);

        FirstArrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x= parseInt(FirstText.getText().toString());
                FirstText.setText(Integer.toString(x+1));
            }
        });

        FirstArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x= parseInt(FirstText.getText().toString());
                FirstText.setText(Integer.toString(x-1));
            }
        });


        SecondArrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x= parseInt(SecondText.getText().toString());
                SecondText.setText(Integer.toString(x+1));
            }
        });

        SecondArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x= parseInt(SecondText.getText().toString());
                SecondText.setText(Integer.toString(x-1));
            }
        });


        ThirdArrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x= parseInt(ThirdText.getText().toString());
                ThirdText.setText(Integer.toString(x+1));
            }
        });

        ThirdArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x= parseInt(ThirdText.getText().toString());
                ThirdText.setText(Integer.toString(x-1));
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String X = FirstText.getText().toString();
                String Y = SecondText.getText().toString();
                String Z = ThirdText.getText().toString();

                OrderRef.child(imei).child("Date").setValue(Day+"/"+Month+"/"+Year+","+Integer.toString(Hour)+":"+Integer.toString(Minute));
                OrderRef.child(imei).child("Food").child("FISH").setValue(X);
                OrderRef.child(imei).child("Food").child("FISH2").setValue(Y);
                OrderRef.child(imei).child("Food").child("FISH3").setValue(Z);

            }
        });





        return Mview;
    }

}
