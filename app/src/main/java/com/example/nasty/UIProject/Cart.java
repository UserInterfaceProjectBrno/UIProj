package com.example.nasty.UIProject;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Cart
{

    static FirebaseDatabase OrderDatabase = FirebaseDatabase.getInstance();
    final static DatabaseReference OrderRef = OrderDatabase.getReference().child("Orders");
    static String data;
    String imei="null";
    int flag=0;
    int Total;
    int ProductCount;

    public Cart()
    {

        OrderRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(imei).child("Products").child("TotalPrice").getValue()!=null)
                    Total = Integer.parseInt(dataSnapshot.child(imei).child("Products").child("TotalPrice").getValue().toString());

                if(Objects.equals(dataSnapshot.child(imei).child("Locked").getValue(), "Yes"))
                {
                    flag = 1;
                }
                else
                {
                    flag = 0;
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void setImei(String mimei){
        imei=mimei;
       }

    public void addOnCart(String Product, String Quantity, String Price)
     {
          if(flag==0)
          {
               OrderRef.child(imei).child("Products").child(Product).setValue(Quantity);
               Total=(Total+(Integer.parseInt(Quantity)*Integer.parseInt(Price)));
               OrderRef.child(imei).child("Products").child("TotalPrice").setValue(Total);
          }
      }
    public void RemoveFromCart(String Product,String Quantity,String Price)
    {
        if(flag==0)
            OrderRef.child(imei).child("Products").child(Product).setValue("0");
        if(Price!=null && Quantity!=null)
            Total=(Total-(Integer.parseInt(Price)*Integer.parseInt(Quantity)));
            OrderRef.child(imei).child("Products").child("TotalPrice").setValue(Total);
    }



}
