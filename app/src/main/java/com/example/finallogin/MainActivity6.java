package com.example.finallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity6 extends AppCompatActivity {
Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);
        home=findViewById(+R.id.home1);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(MainActivity6.this,trailOfGrid.class));
                Intent intent = new Intent(MainActivity6.this,MainActivity4.class);

//                HashMap<Integer, Bitmap> mapimageChunks = (HashMap<Integer, Bitmap>) getIntent().getSerializableExtra("image chunks");
                startActivity(intent);
            }
        });

    }
}