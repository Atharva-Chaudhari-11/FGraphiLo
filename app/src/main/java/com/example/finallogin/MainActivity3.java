package com.example.finallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {
    Button btn,b1,b2,b3,b4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        btn=findViewById(+R.id.button6);

        b1=findViewById(+R.id.btn4);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this,MainActivity.class));
            }
        });

        b2=findViewById(R.id.btn1);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent in=new Intent(MainActivity3.this,ToDoManActivity.class);
               startActivity(in);
            }
        });

        b3=findViewById(R.id.pass);
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity3.this,Calculator.class);
                startActivity(intent);
            }
        });

        b4=findViewById(R.id.lockabtn);
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity3.this,Browser.class));
            }
        });
    }

//    public void logoutprocess(View view)
//    {
//        FirebaseAuth.getInstance().signOut();
//        startActivity(new Intent(MainActivity3.this,LoginActivity.class));
//    }
}