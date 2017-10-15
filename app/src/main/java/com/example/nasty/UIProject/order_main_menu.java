package com.example.nasty.UIProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class order_main_menu extends AppCompatActivity {

    Button OrderMenuButton;
    ImageButton chickButt;
    ImageButton carrotButt;
    ImageButton cupButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main_menu);

        OrderMenuButton = (Button) findViewById(R.id.OrderMenuButton);
        chickButt = (ImageButton) findViewById(R.id.chickenButton);
        carrotButt = (ImageButton) findViewById(R.id.carrotButton);
        cupButt = (ImageButton) findViewById(R.id.cupButton);

        OrderMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(order_main_menu.this, login_page.class);
                startActivity(intent);
            }
        });

        chickButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChick= new Intent(order_main_menu.this, login_page.class);
                startActivity(intentChick);
            }
        });

        carrotButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCarrot = new Intent(order_main_menu.this, login_page.class);
                startActivity(intentCarrot);
            }
        });

        cupButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCup = new Intent(order_main_menu.this, login_page.class);
                startActivity(intentCup);
            }
        });
    }
}
