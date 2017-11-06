package com.example.nasty.UIProject;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Time_n_Day_Fragment extends Fragment
{

    View Mview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        Mview = inflater.inflate(R.layout.activity_time_n_day, container, false);

        return Mview;
    }

}