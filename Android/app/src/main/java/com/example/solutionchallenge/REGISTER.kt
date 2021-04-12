package com.example.solutionchallenge

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class REGISTER : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.register)
        val  mAuth:FirebaseAuth
        mAuth = FirebaseAuth.getInstance()
        val address:TextView=findViewById(R.id.ADDRESS)
        val point: Button =findViewById(R.id.SIGNUP)
        val number:TextView=findViewById(R.id.NummberOfPeopleWillingToDonate)
        val animals:CheckBox=findViewById(R.id.ANIMALS)
        val humans:CheckBox=findViewById(R.id.checkBox2)
        val npoint:Button=findViewById(R.id.button2)
        val member:Member=Member()
        val reff: DatabaseReference =FirebaseDatabase.getInstance().getReference().child("Member")

        val save:Button=findViewById(R.id.SAVE)
         var c=0
         var d=0
        save.setOnClickListener{
            if(address.text.toString().trim()=="") {
                Toast.makeText(this,"Adress is requied",Toast.LENGTH_SHORT).show()
                c=0
            } else {
                member.address=address.text.toString().trim()
                c=1
            }
            if(number.text.toString().trim()=="") {
                Toast.makeText(this,"Number of people is requied",Toast.LENGTH_SHORT).show()
                 d=0
            } else {
                member.number=number.text.toString().trim().toInt()
                d=1
            }
            if(humans.isChecked()) {
                member.humans=1
            }
            if(animals.isChecked) {
                member.animals=1
            }
            if(d==1 && c==1)
            {
                reff.push().setValue(member)
                Toast.makeText(this,"Data saved successfully",Toast.LENGTH_SHORT).show()
            }
        }
        point.setOnClickListener {
             if(d==1 && c==1)
             {
                 val intent = Intent(this, Map::class.java)
                 startActivity(intent)

             }
            else
             {
                 Toast.makeText(this,"Save the required data first",Toast.LENGTH_SHORT).show()
             }
        }
       npoint.setOnClickListener {
           if(d==1 && c==1)
           {
               if( member.number<5)
               {
                   Toast.makeText(this,"You need atleast 5 people to create a new point",Toast.LENGTH_SHORT).show()
               }
               else
               {
                   Toast.makeText(this,"This feature is not supported in beta version",Toast.LENGTH_SHORT).show()
               }
           }
           else
           {
               Toast.makeText(this,"Save the required data first",Toast.LENGTH_SHORT).show()
           }
       }




    }

    }
