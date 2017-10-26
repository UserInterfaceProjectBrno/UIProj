package com.example.nasty.UIProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class order_main_menu extends AppCompatActivity {

    Button OrderMenuButton;
    Button Database_Reset;
    ImageButton MealButt;
    ImageButton SaladButt;
    ImageButton CoffeeButt;
    ImageButton FishButt;
    ImageButton PizzaButt;
    ImageButton OtherDrinksButt;

    int Children = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main_menu);

        final FirebaseDatabase TableDatabase = FirebaseDatabase.getInstance();
        final DatabaseReference TableRef = TableDatabase.getReference().child("Tables");

        TableRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
               Children = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        OrderMenuButton = (Button) findViewById(R.id.OrderMenuButton);
        MealButt = (ImageButton) findViewById(R.id.MealButt);
        SaladButt = (ImageButton) findViewById(R.id.SaladButton);
        CoffeeButt = (ImageButton) findViewById(R.id.CoffeeButton);
        FishButt = (ImageButton) findViewById(R.id.FishButton);
        PizzaButt = (ImageButton) findViewById(R.id.PizzaButton);
        OtherDrinksButt = (ImageButton) findViewById(R.id.OtherDrinkButton);
        Database_Reset = (Button) findViewById(R.id.Database_Reset);

        Database_Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=1;i<=Children; i++)
                { //RESETING DATABASE!!   !!!! TABLES START FROM 1 !!!!
                    TableRef.child(Integer.toString(i)).child("Reserved").child("Yes").setValue("No");
                    TableRef.child(Integer.toString(i)).child("Reserved").child("ID-Phone").setValue("None");
                }
            }
        });

        OrderMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(order_main_menu.this, login_page.class);
                startActivity(intent);
            }
        });

        MealButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentChick= new Intent(order_main_menu.this, product_coffee.class);
                startActivity(intentChick);
            }
        });

        SaladButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCarrot = new Intent(order_main_menu.this, product_coffee.class);
                startActivity(intentCarrot);
            }
        });

        CoffeeButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCup = new Intent(order_main_menu.this, product_coffee.class);
                startActivity(intentCup);
            }
        });

        FishButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFish = new Intent(order_main_menu.this, product_coffee.class);
                startActivity(intentFish);
            }
        });

        PizzaButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentPizza = new Intent(order_main_menu.this, product_coffee.class);
                startActivity(intentPizza);
            }
        });

        OtherDrinksButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentWine = new Intent(order_main_menu.this, product_coffee.class);
                startActivity(intentWine);
            }
        });
    }
}
