package com.example.nasty.UIProject;

import android.content.Context;
import android.content.Intent;
import android.media.tv.TvContract;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class table_handle extends AppCompatActivity {

    Button CheckForTableButt;
    ImageButton AddPersonBtn;
    ImageButton DeletePersonBtn;
    TextView NumberOfPeople;
    ImageButton Logout_butt;
    int number = 1;
    TelephonyManager mngr;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_handle);
        //////////////////////////////////IMEI PERMISSION///////////////////////////////////////////
        ActivityCompat.requestPermissions(table_handle.this,
                new String[]{"android.permission.READ_PHONE_STATE"},
                1);
        mngr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        final String imei = mngr.getDeviceId();
        ////////////////////////////////////////////////////////////////////////////////////////////
        CheckForTableButt = (Button) findViewById(R.id.CheckForTableButton);
        NumberOfPeople = (TextView) findViewById(R.id.PersNum);
        AddPersonBtn = (ImageButton) findViewById(R.id.AddPersonBtn);
        DeletePersonBtn = (ImageButton) findViewById(R.id.DeletePersonBtn);
        Logout_butt = (ImageButton) findViewById(R.id.Logout_tableHand);



        CheckForTableButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(table_handle.this, order_main_menu.class);
                startActivity(intent);
            }
        });
        AddPersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number++;
                NumberOfPeople.setText(String.valueOf(number));
            }
        });
        DeletePersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number>1)
                number--;
                NumberOfPeople.setText(String.valueOf(number));
            }
        });


        ///////////////////////////////LOGOUT PROGRESS///////////////////////////////////////////////
        Logout_butt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                FirebaseDatabase soulboundDatabase = FirebaseDatabase.getInstance();
                DatabaseReference LogoutRef = soulboundDatabase.getReference().child("Soulbounded").child(imei);
                LogoutRef.removeValue();
                Intent LogoutInt = new Intent(table_handle.this, login_page.class);
                startActivity(LogoutInt);
            }
        });


    }
}

