package com.example.nasty.UIProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class table_handle extends AppCompatActivity {

    Button CheckForTableButt;
    ImageButton AddPersonBtn;
    ImageButton DeletePersonBtn;
    TextView NumberOfPeople;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_handle);

        CheckForTableButt = (Button) findViewById(R.id.CheckForTableButton);

        AddPersonBtn = (ImageButton) findViewById(R.id.AddPersonBtn);
        DeletePersonBtn = (ImageButton) findViewById(R.id.DeletePersonBtn);


        CheckForTableButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(table_handle.this, order_main_menu.class);
                startActivity(intent);
            }
        });

        AddPersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int number = Integer.getInteger(NumberOfPeople.getText().toString());
               NumberOfPeople.setText(number++);
            }
        });
        DeletePersonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int number = Integer.getInteger(NumberOfPeople.getText().toString());
                NumberOfPeople.setText(number++);
            }
        });


    }
}

