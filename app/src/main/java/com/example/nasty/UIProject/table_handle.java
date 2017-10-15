package com.example.nasty.UIProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class table_handle extends AppCompatActivity {

    Button CheckForTableButt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_handle);

        CheckForTableButt = (Button) findViewById(R.id.CheckForTableButton);


        CheckForTableButt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(table_handle.this, order_main_menu.class);
                startActivity(intent);
            }
        });

    }


}
