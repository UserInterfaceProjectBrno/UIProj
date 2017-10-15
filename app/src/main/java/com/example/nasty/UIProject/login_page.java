package com.example.nasty.UIProject;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;


public class login_page extends AppCompatActivity {

    private static final String TAG = "MyFirebaseMsgService";


    EditText userTxt;
    EditText passTxtBox;
    CheckBox RememberCheckBox;
    Button OrderButt;
    ImageButton CreateButt;
    ImageButton ForgotButt;
    ImageButton RegisterButt;
    ImageButton LoginButt;
    ImageView RememberTxt;
    ImageView PasswordTxt;
    ImageView DownTxtBox;
    ImageView ForgotBigButton;
    ImageButton BackToLoginButton;

    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        OrderButt = (Button) findViewById(R.id.OrderButt);
        LoginButt = (ImageButton) findViewById(R.id.LoginButt);
        CreateButt = (ImageButton) findViewById(R.id.CreateButt);
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
                mAuth.signInWithEmailAndPassword("nastyrakakis@gmail.com", "112233")/*userTxt.getText().toString(), passTxtBox.getText().toString()*/
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Intent login_intent = new Intent(login_page.this, table_handle.class);
                                    startActivity(login_intent);
                                    Toast.makeText(getApplicationContext(), "CONNECTED SUCCESSFULLY...", Toast.LENGTH_SHORT).show();
                                }
                                if (!task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "ERROR CONNECTING...", Toast.LENGTH_SHORT).show();
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
                fadeOutAndHideImage(RegisterButt);
                fadeIn(ForgotBigButton);
                fadeIn(CreateButt);
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
        CreateButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeOutAndHideImage(CreateButt);
                fadeOutAndHideImage(LoginButt);
                fadeOutAndHideImage(RememberCheckBox);
                fadeOutAndHideImage(RememberTxt);
                fadeIn(RegisterButt);
                fadeIn(ForgotButt);
                fadeIn(PasswordTxt);
                fadeIn(DownTxtBox);
                fadeIn(passTxtBox);
                fadeIn(BackToLoginButton);
                fadeOutAndHideImage(ForgotBigButton);

            }
        });
        ////////////////////////////////////////END CREATE BUTTON/////////////////////////////////////////////////////////////////

///////////////////////////////////////END Back BUTTON///////////////////////////////////////////////////////////////
        BackToLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fadeIn(CreateButt);
                fadeIn(LoginButt);
                fadeIn(RememberCheckBox);
                fadeIn(RememberTxt);
                fadeOutAndHideImage(RegisterButt);
                fadeIn(PasswordTxt);
                fadeIn(DownTxtBox);
                fadeIn(passTxtBox);
                fadeIn(ForgotButt);
                fadeOutAndHideImage(ForgotBigButton);
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

    private void sendVerificationEmail()
    {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        user.sendEmailVerification()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                        {
                        }
                        else
                        {

                        }
                    }
                });
    }

    private void fadeOutAndHideImage(final View img) {
        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(50);

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

    private void fadeIn(final View img) {
        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new AccelerateInterpolator());
        fadeIn.setDuration(50);

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

