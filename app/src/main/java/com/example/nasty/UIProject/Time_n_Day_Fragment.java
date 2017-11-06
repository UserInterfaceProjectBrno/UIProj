package com.example.nasty.UIProject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Time_n_Day_Fragment extends Fragment
{

    View Mview;

    TextView MinutesText;

    Button plus_1;
    Button plus_5;
    Button plus_10;
    Button minus_1;
    Button minus_5;
    Button minus_10;
    Button Submit;

    int numberofMin = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        Mview = inflater.inflate(R.layout.activity_time_n_day, container, false);

        MinutesText = (TextView) Mview.findViewById(R.id.Minutes_Text);
        numberofMin = Integer.parseInt(MinutesText.getText().toString());

        plus_1 = (Button) Mview.findViewById(R.id.plus_1);
        plus_5 = (Button) Mview.findViewById(R.id.plus_5);
        plus_10 = (Button) Mview.findViewById(R.id.plus_10);
        minus_1 = (Button) Mview.findViewById(R.id.minus_1);
        minus_5 = (Button) Mview.findViewById(R.id.minus_5);
        minus_10 = (Button) Mview.findViewById(R.id.minus_10);
        Submit = (Button) Mview.findViewById(R.id.SubmitMin);

        plus_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberofMin++;
                MinutesText.setText(numberofMin + " m.");
                if (numberofMin > 240) {
                    numberofMin = 240;
                    MinutesText.setText("4 H.");
                }
                if (numberofMin == 60) {
                    numberofMin = 60;
                    MinutesText.setText("1 H.");
                }
                if (numberofMin == 90) {
                    numberofMin = 90;
                    MinutesText.setText("1.5 H.");
                }
                if (numberofMin == 120) {
                    numberofMin = 120;
                    MinutesText.setText("2 H.");
                }
                if (numberofMin == 150) {
                    numberofMin = 150;
                    MinutesText.setText("2.5 H.");
                }
                if (numberofMin == 180) {
                    numberofMin = 180;
                    MinutesText.setText("3 H.");
                }
                if (numberofMin == 210) {
                    numberofMin = 210;
                    MinutesText.setText("3.5 H.");
                }

            }
        });
        plus_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberofMin += 5;
                MinutesText.setText(numberofMin + " m.");
                if (numberofMin > 240) {
                    numberofMin = 240;
                    MinutesText.setText("4 H.");
                }
                if (numberofMin == 60) {
                    numberofMin = 60;
                    MinutesText.setText("1 H.");
                }
                if (numberofMin == 90) {
                    numberofMin = 90;
                    MinutesText.setText("1.5 H.");
                }
                if (numberofMin == 120) {
                    numberofMin = 120;
                    MinutesText.setText("2 H.");
                }
                if (numberofMin == 150) {
                    numberofMin = 150;
                    MinutesText.setText("2.5 H.");
                }
                if (numberofMin == 180) {
                    numberofMin = 180;
                    MinutesText.setText("3 H.");
                }
                if (numberofMin == 210) {
                    numberofMin = 210;
                    MinutesText.setText("3.5 H.");
                }
            }
        });
        plus_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberofMin += 10;
                MinutesText.setText(numberofMin + " m.");
                if (numberofMin > 240) {
                    numberofMin = 240;
                    MinutesText.setText("4 H.");
                }
                if (numberofMin == 60) {
                    numberofMin = 60;
                    MinutesText.setText("1 H.");
                }
                if (numberofMin == 90) {
                    numberofMin = 90;
                    MinutesText.setText("1.5 H.");
                }
                if (numberofMin == 120) {
                    numberofMin = 120;
                    MinutesText.setText("2 H.");
                }
                if (numberofMin == 150) {
                    numberofMin = 150;
                    MinutesText.setText("2.5 H.");
                }
                if (numberofMin == 180) {
                    numberofMin = 180;
                    MinutesText.setText("3 H.");
                }
                if (numberofMin == 210) {
                    numberofMin = 210;
                    MinutesText.setText("3.5 H.");
                }
            }
        });
        minus_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberofMin--;
                MinutesText.setText(numberofMin + " m.");
                if (numberofMin < 0) {
                    numberofMin = 0;
                    MinutesText.setText("0 m.");
                }
                if (numberofMin == 60) {
                    numberofMin = 60;
                    MinutesText.setText("1 H.");
                }
                if (numberofMin == 90) {
                    numberofMin = 90;
                    MinutesText.setText("1.5 H.");
                }
                if (numberofMin == 120) {
                    numberofMin = 120;
                    MinutesText.setText("2 H.");
                }
                if (numberofMin == 150) {
                    numberofMin = 150;
                    MinutesText.setText("2.5 H.");
                }
                if (numberofMin == 180) {
                    numberofMin = 180;
                    MinutesText.setText("3 H.");
                }
                if (numberofMin == 210) {
                    numberofMin = 210;
                    MinutesText.setText("3.5 H.");
                }
            }
        });
        minus_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberofMin -= 5;
                MinutesText.setText(numberofMin + " m.");
                if (numberofMin < 0) {
                    numberofMin = 0;
                    MinutesText.setText("0 m.");
                }
                if (numberofMin == 60) {
                    numberofMin = 60;
                    MinutesText.setText("1 H.");
                }
                if (numberofMin == 90) {
                    numberofMin = 90;
                    MinutesText.setText("1.5 H.");
                }
                if (numberofMin == 120) {
                    numberofMin = 120;
                    MinutesText.setText("2 H.");
                }
                if (numberofMin == 150) {
                    numberofMin = 150;
                    MinutesText.setText("2.5 H.");
                }
                if (numberofMin == 180) {
                    numberofMin = 180;
                    MinutesText.setText("3 H.");
                }
                if (numberofMin == 210) {
                    numberofMin = 210;
                    MinutesText.setText("3.5 H.");
                }
            }
        });
        minus_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberofMin -= 10;
                MinutesText.setText(numberofMin + " m.");
                if (numberofMin < 0) {
                    numberofMin = 0;
                    MinutesText.setText("0 m.");
                }
                if (numberofMin == 60) {
                    numberofMin = 60;
                    MinutesText.setText("1 H.");
                }
                if (numberofMin == 90) {
                    numberofMin = 90;
                    MinutesText.setText("1.5 H.");
                }
                if (numberofMin == 120) {
                    numberofMin = 120;
                    MinutesText.setText("2 H.");
                }
                if (numberofMin == 150) {
                    numberofMin = 150;
                    MinutesText.setText("2.5 H.");
                }
                if (numberofMin == 180) {
                    numberofMin = 180;
                    MinutesText.setText("3 H.");
                }
                if (numberofMin == 210) {
                    numberofMin = 210;
                    MinutesText.setText("3.5 H.");
                }
            }
        });


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });




        return Mview;
    }
}