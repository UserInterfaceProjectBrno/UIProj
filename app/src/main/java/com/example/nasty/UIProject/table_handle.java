package com.example.nasty.UIProject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
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

import java.util.Objects;

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
        final String table[][] = new String[1000][5];//MAX: 999 Table and 4 Specifications per table.
        final int[] children = new int[1]; // Tables in Database.

        TableRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                children[0] = (int) dataSnapshot.getChildrenCount();

                for (int i = 1; i <= dataSnapshot.getChildrenCount(); i++)  // THIS IS FOR GETTING VALUES FROM DATABASE TO A MATRIX.
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

        final int[] YourTable = {0}; // TABLE HOLDER.

        CheckForTableButt.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                for (int i = 1; i <= children[0]; i++)
                {
                    if(parseInt(table[i][1]) >= number && !Objects.equals(table[i][2], "Yes")) //check for empty table and for fitting
                    {
                            YourTable[0] = i;
                            TableRef.child(Integer.toString(i)).child("Reserved").child("Yes").setValue("Yes");
                            Toast.makeText(getApplicationContext(), "Your Table is Number: "+ YourTable[0], Toast.LENGTH_LONG).show();
                            Intent CheckGo = new Intent(table_handle.this,order_main_menu.class);
                            startActivity(CheckGo);
                            break;
                    }
                }
                if(YourTable[0]==0)
                {
                    Toast.makeText(getApplicationContext(), "There Is No Table For "+number+" Person.", Toast.LENGTH_SHORT).show();
                }
            }
        });
///////////////////////////////////////////////////// TABLE CHECK END /////////////////////////////////////////////////
        AddPersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number<10)
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