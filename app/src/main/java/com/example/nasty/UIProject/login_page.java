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
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.util.Calendar;

public class login_page extends AppCompatActivity {

    EditText UserTxt;
    EditText PassTxt;
    Button LoginBigButt;
    Button LoginButt;
    Button ForgotButt;
    Button ForgotBigButt;
    Button RegisterButt;
    Button RegisterBigButt;
    TextView PassTxtView;
    TextView UserTxtView;
    TextView PassTxtView2;
    EditText PassTxt2;

    TelephonyManager mngr;
    String imei = "null";

    FirebaseDatabase DateDB = FirebaseDatabase.getInstance();
    final DatabaseReference DateRef = DateDB.getReference().child("Orders");
    FirebaseDatabase soulboundDatabase = FirebaseDatabase.getInstance();
    final DatabaseReference SoulRef = soulboundDatabase.getReference().child("Soulbounded");

    int Day = Calendar.getInstance().get(Calendar.DATE);
    int Month = Calendar.getInstance().get(Calendar.MONTH) + 1;
    int Year = Calendar.getInstance().get(Calendar.YEAR);
    int Hour = Calendar.getInstance().getTime().getHours();
    int Minute = Calendar.getInstance().getTime().getMinutes();

    private FirebaseAuth mAuth;


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final Intent GoLogin = new Intent(login_page.this, login_page.class);
        mAuth = FirebaseAuth.getInstance();

        LoginBigButt = (Button) findViewById(R.id.LoginBigButt);
        LoginButt = (Button) findViewById(R.id.LoginButt);
        UserTxt = (EditText) findViewById(R.id.UserTxtInput);
        PassTxt = (EditText) findViewById(R.id.PassTxtInput);
        ForgotButt = (Button) findViewById(R.id.ForgotButt);
        ForgotBigButt = (Button) findViewById(R.id.ForgotBigButt);
        RegisterButt = (Button) findViewById(R.id.RegisterButt);
        RegisterBigButt = (Button) findViewById(R.id.RegisterBigButt);
        PassTxtView = (TextView) findViewById(R.id.PassTxt);
        UserTxtView = (TextView) findViewById(R.id.UserTxt);
        PassTxtView2 = (TextView) findViewById(R.id.PassTxt2);
        PassTxt2 = (EditText) findViewById(R.id.PassTxtInput2);

        final float[] StartPosLoginButt = {LoginButt.getTranslationX()};
        final float[] StartPosUserTxt = {UserTxt.getTranslationY()};
        final float[] StartPosPassTxt = {PassTxt.getTranslationY()};
        final float[] StartPosUserTxtInput = {UserTxtView.getTranslationY()};
        final float[] StartPosPassTxtInput = {PassTxtView.getTranslationY()};


        //////////////////////////////////////////////////////////////////////////////////////////////////

        ActivityCompat.requestPermissions(login_page.this,
                new String[]{"android.permission.READ_PHONE_STATE"},
                1);

        mngr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        imei = mngr.getDeviceId();
        soulboundVerificationAndLogin();
        ///////////////////////////////////////////////////////LOGIN BUTTON/////////////////////////////////////////////////////////
        LoginButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeIn(LoginBigButt);
                fadeOutAndHideImage(RegisterBigButt);
                fadeOutAndHideImage(ForgotBigButt);
                fadeIn(RegisterButt);
                fadeIn(ForgotButt);
                fadeOutAndHideImage(LoginButt);
                fadeOutAndHideImage(PassTxt2);
                fadeOutAndHideImage(PassTxtView2);
                fadeIn(PassTxt);
                fadeIn(PassTxtView);
                PassTxt.setImeOptions(EditorInfo.IME_ACTION_GO);

            }
        });


        LoginBigButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserTxt.getText().toString().isEmpty() || PassTxt.getText().toString().isEmpty()
                        ) {
                    UserTxt.setText("Empty");
                    PassTxt.setText("Empty");
                }
                mAuth.signInWithEmailAndPassword(UserTxt.getText().toString(), PassTxt.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @RequiresApi(api = Build.VERSION_CODES.M)
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful() && mAuth.getCurrentUser().isEmailVerified()) {
                                    Toast.makeText(getApplicationContext(), "CONNECTED SUCCESSFULLY...", Toast.LENGTH_SHORT).show();
                                    soulboundDevice(true, UserTxt.getText().toString()); //RememberCheckBox On TRUE
                                }
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "ERROR CONNECTING...", Toast.LENGTH_SHORT).show();
                                }
                                if (task.isSuccessful() && !mAuth.getCurrentUser().isEmailVerified()) {
                                    Toast.makeText(getApplicationContext(), "PLEASE VERIFY EMAIL...", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
        ////////////////////////////////////////////////END LOGIN BUTTON  ////////////////////////////////////////////////////////////////////
        PassTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView exampleView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO && LoginBigButt.getVisibility() != View.GONE) {
                    LoginBigButt.callOnClick();
                }
                return false;
            }
        });
        PassTxt2.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView exampleView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_GO && RegisterBigButt.getVisibility() != View.GONE) {
                    RegisterBigButt.callOnClick();
                }
                return false;
            }
        });
        UserTxt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView exampleView, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT && ForgotBigButt.getVisibility() != View.GONE) {
                    ForgotBigButt.callOnClick();

                }
                return false;
            }
        });

        PassTxt.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                PassTxt.getText().clear();
                return false;
            }
        });

        PassTxt2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                PassTxt2.getText().clear();
                return false;
            }
        });


/////////////////////////////////////// REGISTER BUTTON /////////////////////////////////////////////////////////////////
        RegisterButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeOutAndHideImage(RegisterButt);
                fadeOutAndHideImage(ForgotBigButt);
                fadeOutAndHideImage(LoginBigButt);
                fadeIn(LoginButt);
                fadeIn(RegisterBigButt);
                fadeIn(PassTxtView);
                fadeIn(PassTxt);
                fadeIn(ForgotButt);
                fadeIn(PassTxt2);
                fadeIn(PassTxtView2);
                PassTxt.setImeOptions(EditorInfo.IME_ACTION_NEXT);
                UserTxtView.setTranslationY(StartPosUserTxt[0] - 200);
                UserTxt.setTranslationY(StartPosUserTxtInput[0] - 200);
                PassTxtView.setTranslationY(StartPosPassTxt[0] - 200);
                PassTxt.setTranslationY(StartPosPassTxtInput[0] - 200);

                LoginButt.setTranslationX(StartPosLoginButt[0] + 200);
            }
        });


        RegisterBigButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (UserTxt.getText().toString().isEmpty() || PassTxt.getText().toString().isEmpty() || PassTxt2.getText().toString().isEmpty()) {
                    UserTxt.setText("Empty");
                    PassTxt.setText("Empty");
                    PassTxt2.setText("Empty");
                }
                if (PassTxt2.getText().toString().equals(PassTxt.getText().toString())) {
                    mAuth.createUserWithEmailAndPassword(UserTxt.getText().toString(), PassTxt.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(getApplicationContext(), "REGISTER DONE", Toast.LENGTH_LONG).show();
                                        sendVerificationEmail();
                                        DateRef.child(imei).child("Locked").setValue("No");
                                        startActivity(GoLogin);
                                    } else {
                                        Toast.makeText(getApplicationContext(), "REGISTER NOT DONE", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } else {
                    Toast.makeText(getApplicationContext(), "PASSWORD IS NOT VALID\nPLEASE ENTER THE SAME", Toast.LENGTH_LONG).show();
                }

            }
        });


        //////////////////////////////////////// FORGOT BUTTON //////////////////////////////////////////////
        ForgotBigButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().sendPasswordResetEmail(UserTxt.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "RESET EMAIL SENT,PLEASE CHECK YOUR EMAIL'S", Toast.LENGTH_LONG).show();
                                    startActivity(GoLogin);

                                }
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "ERROR RESETING PASSWORD", Toast.LENGTH_LONG).show();
                                    startActivity(GoLogin);
                                }
                            }

                        });
            }
        });

        ForgotButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                fadeOutAndHideImage(LoginBigButt);
                fadeOutAndHideImage(ForgotButt);
                fadeOutAndHideImage(RegisterBigButt);
                fadeOutAndHideImage(PassTxt);
                fadeOutAndHideImage(PassTxtView);
                fadeOutAndHideImage(PassTxt2);
                fadeOutAndHideImage(PassTxtView2);
                fadeIn(ForgotBigButt);
                fadeIn(RegisterButt);
                fadeIn(LoginButt);


                LoginButt.setTranslationX(StartPosLoginButt[0] - 300);

            }
        });

        /////////////////////////////////////////////END FORGOT /////////////////////////////////////////////
    }
/////////////////////////////////////////////////////////////////////////////////
//....ON CREATE....//
////////////////////////////////////////////////////////////////////////////////

    private void sendVerificationEmail() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "PLEASE CHECK YOUR EMAIL'S FOR VERIFICATION", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "ERROR SENTING VERIFICATION", Toast.LENGTH_LONG).show();
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

    public void fadeIn(final View Item) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(400);

        fadeIn.setAnimationListener(new Animation.AnimationListener() {
            public void onAnimationEnd(Animation animation) {
                Item.setVisibility(View.VISIBLE);
            }

            public void onAnimationRepeat(Animation animation) {
            }

            public void onAnimationStart(Animation animation) {
            }
        });

        Item.startAnimation(fadeIn);
    }

    private void soulboundDevice(boolean checked, String s) {
        if (checked) {
            FirebaseDatabase soulboundDatabase = FirebaseDatabase.getInstance();
            DatabaseReference SoulRef = soulboundDatabase.getReference().child("Soulbounded").child(mngr.getDeviceId());
            SoulRef.setValue(s);

        } else {
            Intent GoMain = new Intent(login_page.this, MainActivity.class);
            startActivity(GoMain);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void soulboundVerificationAndLogin() {
        final String imei = mngr.getDeviceId();
        DateRef.child(imei).child("Date").setValue(Day + "/" + Month + "/" + Year + "," + Integer.toString(Hour) + ":" + Integer.toString(Minute));

        SoulRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(imei)) {
                    Toast.makeText(getApplicationContext(), "Automatic Connected As: " + dataSnapshot.child(imei).getValue(), Toast.LENGTH_LONG).show();

                    Intent GoMain = new Intent(login_page.this, MainActivity.class);
                    startActivity(GoMain);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
