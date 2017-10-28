package com.example.nasty.UIProject;

import com.example.nasty.UIProject.Cart;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
public class Menu_Drinks_Fragment extends Fragment {
    View Mview;

    Cart my = new Cart();
    my.AddOnCart("SS");

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Mview = inflater.inflate(R.layout.drinks_activity, container, false);
        return Mview;
    }

    com.example.nasty.UIProject.Cart mcart = new Cart();

}
