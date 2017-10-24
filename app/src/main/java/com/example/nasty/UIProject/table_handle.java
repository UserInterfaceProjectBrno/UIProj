package com.example.nasty.UIProject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static java.lang.Integer.parseInt;

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

/////////////////////////////////////// TABLE CHECK START /////////////////////////////////////////////////////
        final FirebaseDatabase TableDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference TableRef = TableDatabase.getReference().child("Tables");
        final String table[][] = new String[10][3];
        final int[] children = new int[1];

        TableRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                children[0] = (int) dataSnapshot.getChildrenCount();

                for (int i = 1; i < dataSnapshot.getChildrenCount(); i++)
                {
                    table[i][1] = dataSnapshot.child(Integer.toString(i)).child("Seats").getValue().toString();
                    table[i][2] = dataSnapshot.child(Integer.toString(i)).child("Reserved").child("Yes").getValue().toString();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });

        CheckForTableButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 1; i < 3;i++)
                {
                    Toast.makeText( getApplicationContext() , table[i][1] , Toast.LENGTH_SHORT ).show();
                }
            }
        });
///////////////////////////////////////////////////// TABLE CHECK END /////////////////////////////////////////////////
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
        /////////////////////////////////////////////////////////////////////////////////////////////


    }
}