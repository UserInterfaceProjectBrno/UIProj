package com.example.nasty.UIProject;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class login_page extends AppCompatActivity {
    EditText userTxt;
    EditText passTxtBox;
    CheckBox RememberCheckBox;
    Button OrderButt;
    ImageButton CreateSmallButt;
    ImageButton ForgotButt;
    ImageButton RegisterButt;
    ImageButton LoginButt;
    ImageView RememberTxt;
    ImageView PasswordTxt;
    ImageView DownTxtBox;
    ImageView ForgotBigButton;
    ImageButton BackToLoginButton;


    private FirebaseAuth mAuth;
    TelephonyManager mngr;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();


            ActivityCompat.requestPermissions(login_page.this,
                    new String[]{"android.permission.READ_PHONE_STATE"},
                    1);

        mngr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);

        soulboundVerificationAndLogin();

        OrderButt = (Button) findViewById(R.id.OrderButt);
        LoginButt = (ImageButton) findViewById(R.id.LoginButt);
        CreateSmallButt = (ImageButton) findViewById(R.id.CreateButt);
        ForgotButt = (ImageButton) findViewById(R.id.ForgotButt);
        RegisterButt = (ImageButton) findViewById(R.id.RegisterButton);
        ForgotBigButton = (ImageButton) findViewById(R.id.ForgotBigButton);
        BackToLoginButton = (ImageButton) findViewById(R.id.BackToLoginButton);

        DownTxtBox= (ImageView) findViewById(R.id.DownTxtBox);
        PasswordTxt = (ImageView) findViewById(R.id.PasswordTxt);
        userTxt = (EditText) findViewById(R.id.UsernameTxtBox);
        passTxtBox = (EditText) findViewById(R.id.PasswordTxtBox);

        RememberCheckBox = (CheckBox) findViewById(R.id.RememberCheckbox);
        RememberTxt = (ImageView) findViewById(R.id.RememberMeTxt);

/////////////////////////////////////ORDER BUTTON /////////////////////////////////////////////
        OrderButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent order_intent = new Intent(login_page.this, order_main_menu.class);
                startActivity(order_intent);
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////

//////////////////////////////// CLEARING EDIT TEXTS ////////////////////////////////////////////
        passTxtBox.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                passTxtBox.setText("");
            }
        });
        ////////////////////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////// LOGIN BUTT ////////////////////////////////////////////////
        LoginButt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (userTxt.getText().toString().isEmpty() || passTxtBox.getText().toString().isEmpty()) {
                    userTxt.setText("Empty");
                    passTxtBox.setText("Empty");
                }
                mAuth.signInWithEmailAndPassword(userTxt.getText().toString(), passTxtBox.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful() && mAuth.getCurrentUser().isEmailVerified()) {
                                    Intent login_intent = new Intent(login_page.this, table_handle.class);
                                    startActivity(login_intent);
                                    Toast.makeText(getApplicationContext(), "CONNECTED SUCCESSFULLY...", Toast.LENGTH_SHORT).show();

                                    soulboundDevice(RememberCheckBox.isChecked());
                                }
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "ERROR CONNECTING...", Toast.LENGTH_SHORT).show();
                                }
                                if(task.isSuccessful() && !mAuth.getCurrentUser().isEmailVerified())
                                {
                                    Toast.makeText(getApplicationContext(), "PLEASE VERIFY EMAIL...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
///////////////////////////////////////////////END LOGIN BUTTON  ////////////////////////////////////////////////////////////////////

        //////////////////////////////////////// FORGOT BUTTON //////////////////////////////////////////////

        ForgotButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeOutAndHideImage(ForgotButt);
                fadeOutAndHideImage(LoginButt);
                fadeOutAndHideImage(RememberCheckBox);
                fadeOutAndHideImage(RememberTxt);
                fadeOutAndHideImage(PasswordTxt);
                fadeOutAndHideImage(DownTxtBox);
                fadeOutAndHideImage(passTxtBox);
                RegisterButt.setVisibility(View.GONE);
                fadeIn(ForgotBigButton);
                fadeOutAndHideImage(CreateSmallButt);
                fadeIn(BackToLoginButton);

                ForgotBigButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        FirebaseAuth.getInstance().sendPasswordResetEmail(userTxt.getText().toString())
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(getApplicationContext(),"RESET EMAIL SENT,PLEASE CHECK YOUR EMAIL'S",Toast.LENGTH_LONG).show();
                                            BackToLoginButton.callOnClick();
                                        }
                                        if(!task.isSuccessful()){
                                            Toast.makeText(getApplicationContext(),"ERROR RESETING PASSWORD",Toast.LENGTH_LONG).show();
                                        }
                                    }

                                });
                    }
                });

            }
        });
       /////////////////////////////////////////////END FORGOT /////////////////////////////////////////////



        //////////////////////////////////////// CREATE NEW BUTTON /////////////////////////////////////////////////////////////////
        CreateSmallButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeOutAndHideImage(CreateSmallButt);
                fadeOutAndHideImage(LoginButt);
                fadeOutAndHideImage(RememberCheckBox);
                fadeOutAndHideImage(RememberTxt);
                fadeIn(RegisterButt);
                fadeIn(PasswordTxt);
                fadeOutAndHideImage(ForgotButt);
                fadeIn(DownTxtBox);
                fadeIn(passTxtBox);
                fadeIn(BackToLoginButton);
                ForgotBigButton.setVisibility(View.GONE);

            }
        });
        ////////////////////////////////////////END CREATE BUTTON/////////////////////////////////////////////////////////////////

///////////////////////////////////////END Back BUTTON///////////////////////////////////////////////////////////////
        BackToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeIn(CreateSmallButt);
                fadeIn(LoginButt);
                fadeIn(RememberCheckBox);
                fadeIn(RememberTxt);
                RegisterButt.setVisibility(View.GONE);
                fadeIn(PasswordTxt);
                fadeIn(DownTxtBox);
                fadeIn(passTxtBox);
                fadeIn(ForgotButt);
                ForgotBigButton.setVisibility(View.GONE);
                fadeOutAndHideImage(BackToLoginButton);
            }
        });

///////////////////////////////////////END Back BUTTON///////////////////////////////////////////////////////////////

    /////////////////////////////////////// REGISTER BUTTON /////////////////////////////////////////////////////////////////
        RegisterButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userTxt.getText().toString().isEmpty() || passTxtBox.getText().toString().isEmpty()) {
                    userTxt.setText("Empty");
                    passTxtBox.setText("Empty");
                }
                mAuth.createUserWithEmailAndPassword(userTxt.getText().toString(), passTxtBox.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Toast.makeText(getApplicationContext(),"REGISTER DONE",Toast.LENGTH_SHORT).show();
                                    sendVerificationEmail();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"REGISTER NOT DONE",Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

            }
        });
////////////////////////////////////////////////////////////////////////////////
    }//....ON CREATE....//

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void soulboundVerificationAndLogin()
    {
        final String imei = mngr.getDeviceId();

        FirebaseDatabase soulboundDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference SoulRef = soulboundDatabase.getReference().child("Soulbounded");

        SoulRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(imei)) {

                        Toast.makeText(getApplicationContext(), "Automatic Connected As: " + dataSnapshot.child(imei).getValue(), Toast.LENGTH_LONG).show();
                        Intent SoulIntent = new Intent(login_page.this, table_handle.class);
                        startActivity(SoulIntent);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void soulboundDevice(boolean checked)
    {
        if(checked)
        {
            FirebaseDatabase soulboundDatabase = FirebaseDatabase.getInstance();
            DatabaseReference SoulRef = soulboundDatabase.getReference().child("Soulbounded").child(mngr.getDeviceId());
            SoulRef.setValue(mngr.getDeviceId());
        }
        else
        {
            Intent SoulInt = new Intent(login_page.this,table_handle.class);
            startActivity(SoulInt);
        }


    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(getApplicationContext(), "PLEASE CHECK YOUR EMAIL'S FOR VERIFICATION", Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"ERROR SENTING VERIFICATION",Toast.LENGTH_LONG).show();
                        }
                    }
                });
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

}//.....//MAIN//.....//

