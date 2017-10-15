package com.example.nasty.UIProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class order_main_menu extends AppCompatActivity {

    Button OrderMenuButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_main_menu);

        OrderMenuButton = (Button) findViewById(R.id.OrderMenuButton);


        OrderMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(order_main_menu.this, login_page.class);
                startActivity(intent);
            }
        });
    }
}
