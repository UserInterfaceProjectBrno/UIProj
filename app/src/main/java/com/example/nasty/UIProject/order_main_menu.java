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
    ImageButton fishButt;
    ImageButton pizzaButt;
    ImageButton wineButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main_menu);

        OrderMenuButton = (Button) findViewById(R.id.OrderMenuButton);
        chickButt = (ImageButton) findViewById(R.id.chickenButton);
        carrotButt = (ImageButton) findViewById(R.id.carrotButton);
        cupButt = (ImageButton) findViewById(R.id.cupButton);
        fishButt = (ImageButton) findViewById(R.id.fishButton);
        pizzaButt = (ImageButton) findViewById(R.id.pizzaButton);
        wineButt = (ImageButton) findViewById(R.id.wineButton);

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
                Intent intentChick= new Intent(order_main_menu.this, product_activity.class);
                startActivity(intentChick);
            }
        });

        carrotButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCarrot = new Intent(order_main_menu.this, product_activity.class);
                startActivity(intentCarrot);
            }
        });

        cupButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCup = new Intent(order_main_menu.this, product_activity.class);
                startActivity(intentCup);
            }
        });

        fishButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFish = new Intent(order_main_menu.this, product_activity.class);
                startActivity(intentFish);
            }
        });

        pizzaButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPizza = new Intent(order_main_menu.this, product_activity.class);
                startActivity(intentPizza);
            }
        });

        wineButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWine = new Intent(order_main_menu.this, product_activity.class);
                startActivity(intentWine);
            }
        });
    }
}
