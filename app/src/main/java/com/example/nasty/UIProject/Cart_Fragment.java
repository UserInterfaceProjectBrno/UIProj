package com.example.nasty.UIProject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

public class Cart_Fragment extends Fragment {
    View Mview;
    public String data;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mview = inflater.inflate(R.layout.cart_page_activity, container, false);


        Toast.makeText(Mview.getContext().getApplicationContext(),data,Toast.LENGTH_LONG).show();


        return Mview;
    }

}
