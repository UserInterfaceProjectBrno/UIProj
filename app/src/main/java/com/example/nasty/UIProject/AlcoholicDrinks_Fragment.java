package com.example.nasty.UIProject;

import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Objects;

import static java.lang.Integer.parseInt;

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class AlcoholicDrinks_Fragment extends Fragment {
    View Mview;
    TelephonyManager mngr;
    String imei = "null";


    TextView FirstText;
    TextView SecondText;
    TextView ThirdText;

    ImageButton FirstArrowUp;
    ImageButton FirstArrowDown;

    ImageButton SecondArrowUp;
    ImageButton SecondArrowDown;

    ImageButton ThirdArrowUp;
    ImageButton ThirdArrowDown;

    String FirstProd = "Drink - Ouzo ";
    String SecondProd = "Drink - Zubrowka ";
    String ThirdProd = "Drink - Raki ";

    Button Submit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mview = inflater.inflate(R.layout.activity_alcoholicdrinks, container, false);

        /////////////////////////////////IMEI PERMISSION///////////////////////////////////////////
        ActivityCompat.requestPermissions(getActivity(),
                new String[]{"android.permission.READ_PHONE_STATE"},
                1);
        mngr = (TelephonyManager) getActivity().getSystemService(Context.TELEPHONY_SERVICE);
        final String imei = mngr.getDeviceId();
        /////////////////////////////////Initialization of IDs //////////////////////////////////////////////

        final Cart mCart = new Cart();
        mCart.setImei(imei);


        FirstText = (TextView) Mview.findViewById(R.id.FirstRowText);
        SecondText = (TextView) Mview.findViewById(R.id.SecondRowText);
        ThirdText = (TextView) Mview.findViewById(R.id.ThirdRowText);

        FirstArrowUp = (ImageButton) Mview.findViewById(R.id.FirstArrowUp);
        FirstArrowDown = (ImageButton) Mview.findViewById(R.id.FirstArrowDown);

        SecondArrowUp = (ImageButton) Mview.findViewById(R.id.SecondArrowUp);
        SecondArrowDown = (ImageButton) Mview.findViewById(R.id.SecondArrowDown);

        ThirdArrowUp = (ImageButton) Mview.findViewById(R.id.ThirdArrowUp);
        ThirdArrowDown = (ImageButton) Mview.findViewById(R.id.ThirdArrowDown);

        Submit = (Button) Mview.findViewById(R.id.SubmitButt);

        FirstArrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = parseInt(FirstText.getText().toString());
                FirstText.setText(Integer.toString(x + 1));
                if(parseInt(FirstText.getText().toString()) > 50)
                    FirstText.setText("50");

            }
        });

        FirstArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = parseInt(FirstText.getText().toString());
                FirstText.setText(Integer.toString(x - 1));
                if(parseInt(FirstText.getText().toString()) < 0)
                    FirstText.setText("0");
            }
        });


        SecondArrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = parseInt(SecondText.getText().toString());
                SecondText.setText(Integer.toString(x + 1));
                if(parseInt(SecondText.getText().toString()) > 50)
                    SecondText.setText("50");


            }
        });

        SecondArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = parseInt(SecondText.getText().toString());
                SecondText.setText(Integer.toString(x - 1));
                if(parseInt(SecondText.getText().toString()) < 0)
                    SecondText.setText("0");
            }
        });


        ThirdArrowUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = parseInt(ThirdText.getText().toString());
                ThirdText.setText(Integer.toString(x + 1));
                if(parseInt(ThirdText.getText().toString()) > 50)
                    ThirdText.setText("50");

            }
        });

        ThirdArrowDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = parseInt(ThirdText.getText().toString());
                ThirdText.setText(Integer.toString(x - 1));
                if(parseInt(ThirdText.getText().toString()) < 0)
                    ThirdText.setText("0");
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String FirstQuan = FirstText.getText().toString();
                String SecondQuan = SecondText.getText().toString();
                String ThirdQuan = ThirdText.getText().toString();

                if(Objects.equals(FirstQuan, "0"))
                    mCart.RemoveFromCart(FirstProd);
                if(Objects.equals(SecondQuan, "0"))
                    mCart.RemoveFromCart(SecondProd);
                if(Objects.equals(ThirdQuan, "0"))
                    mCart.RemoveFromCart(ThirdProd);

                if (!Objects.equals(FirstQuan, "0"))
                    mCart.addOnCart(FirstProd, FirstQuan, ThirdPrice);

                if (!Objects.equals(SecondQuan, "0"))
                    mCart.addOnCart(SecondProd, SecondQuan, ThirdPrice);

                if (!Objects.equals(ThirdQuan, "0"))
                    mCart.addOnCart(ThirdProd, ThirdQuan, ThirdPrice);

                getFragmentManager().beginTransaction().replace(R.id.content_frame,new Order_Fragment()).commit();
            }
        });

        return Mview;
    }

}
