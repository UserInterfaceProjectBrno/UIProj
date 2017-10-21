package com.example.nasty.UIProject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

public class table_handle extends AppCompatActivity {

    Button CheckForTableButt;
    ImageButton AddPersonBtn;
    ImageButton DeletePersonBtn;
    EditText NumberOfPeople;



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


    int number;

    public void clickedBtn(View view) {
            number= Integer.valueOf(NumberOfPeople.getText().toString());
            switch (view.getId())
            {
                case R.id.AddPersonBtn:
                    number++;
                    NumberOfPeople.setText(number);
                    break;
                case R.id.DeletePersonBtn:
                    if(number >=1 ){
                        number--;
                        NumberOfPeople.setText(number);
                    }
                    break;
            }
        }


    public void clickedCheck(View view) {
        //Integer.valueOf(NumberOfPeople.getText().toString());
        // to be continued
    }
}

