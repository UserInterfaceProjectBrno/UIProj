package com.example.nasty.UIProject;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase soulboundDatabase = FirebaseDatabase.getInstance();
    final DatabaseReference SoulRef = soulboundDatabase.getReference().child("Soulbounded");

    TelephonyManager mngr;
    String imei = "null";
    TextView navEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ////////////////////////////////////////////////////////////////////////////////
       try
       {
           ActivityCompat.requestPermissions(MainActivity.this,
                new String[]{"android.permission.READ_PHONE_STATE"},
                1);
       }catch(NullPointerException ignored){}
        //////////////////////////////////////////////////////////////////////////////////////
        mngr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        imei = mngr.getDeviceId();


        ////////////////////////////////////////////////////////////////////////////////////
        getFragmentManager().beginTransaction().replace(R.id.content_frame
                , new Table_Fragment(),"Order")
                .commit();    //START FROM ORDER PAGE
    ////////////////////////////////////////////////////////////////////////////////////
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View headerView = navigationView.getHeaderView(0);
        navEmail = (TextView) headerView.findViewById(R.id.EmailTextNav);

        SoulRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                navEmail.setText(dataSnapshot.child(imei).getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.LogoutButt) {
            FirebaseDatabase soulboundDatabase = FirebaseDatabase.getInstance();
            final DatabaseReference SoulRef = soulboundDatabase.getReference();
            SoulRef.child("Soulbounded").child(imei).removeValue();
            Intent GoLogin = new Intent(MainActivity.this,login_page.class);
            startActivity(GoLogin);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_table) {
            fragmentManager.beginTransaction().replace(R.id.content_frame
                    , new Table_Fragment())
                    .commit();
        }
        else if (id == R.id.nav_order) {
            fragmentManager.beginTransaction().replace(R.id.content_frame
                    , new Order_Fragment())
                    .commit();
        }
        else if (id == R.id.nav_cart) {
            fragmentManager.beginTransaction().replace(R.id.content_frame
                    , new Cart_Fragment())
                    .commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
