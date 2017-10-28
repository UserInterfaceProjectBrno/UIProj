package com.example.nasty.UIProject;

import java.util.ArrayList;

public class Cart
{
    private int id=0;
    static ArrayList Table = new ArrayList(10);


    public Cart()
    {
        System.out.print("ok Const");
    }

    public ArrayList AddOnCart()
    {
        Table.add("SS");
        return Table;
    }

}