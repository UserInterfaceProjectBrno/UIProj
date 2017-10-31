package com.example.nasty.UIProject;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Cart extends Fish_Fragment
{
    String imei="null";
    FirebaseDatabase OrderDatabase = FirebaseDatabase.getInstance();
    final DatabaseReference OrderRef = OrderDatabase.getReference().child("Orders");

    int flag=0;


    public Cart()
    {

        OrderRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
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

    public void addOnCart(String Product, String Quantity)
     {
          if(flag==0)
          {
               OrderRef.child(imei).child("Products").child(Product).setValue(Quantity);
          }
      }


}
