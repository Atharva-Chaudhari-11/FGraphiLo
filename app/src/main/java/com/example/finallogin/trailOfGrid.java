package com.example.finallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class trailOfGrid extends AppCompatActivity {

//    private HashMap<Integer, Bitmap> mapimageChunks = (HashMap<Integer, Bitmap>) getIntent().getSerializableExtra("image chunks");

    public static String passwordstring =new String("");

    public static List<Integer> keys=new ArrayList<>();

    private ImageView source_image;
    private Context mContext;
    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle bundle) {

        super.onCreate(bundle);
        setContentView(R.layout.activity_trail_of_grid);

        mContext = this;

//        source_image = findViewById(+R.id.source_image);

        HashMap<Integer, Bitmap> mapimageChunks = (HashMap<Integer, Bitmap>) getIntent().getSerializableExtra("image chunks");
        keys = new ArrayList<Integer>(mapimageChunks.keySet());

        Collections.shuffle(keys);
        ArrayList<Bitmap> imageChunks = new ArrayList<>();

        for (int i : keys) {
            imageChunks.add(mapimageChunks.get(i));
        }

        System.out.println(".......s" + keys);

        GridView grid = (GridView) findViewById(R.id.gridview);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String a = String.valueOf(position);
                passwordstring = passwordstring + keys.get(Integer.valueOf(a.toString()));
//                Toast.makeText(getApplicationContext(),passwordstring, Toast.LENGTH_SHORT).show();

                if (passwordstring.length() == 9) {
                    if (passwordstring.equals("123456789")) {

                        System.out.println("password is correct");
//                        Toast.makeText(getApplicationContext(), "password is correct", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(trailOfGrid.this, SignUpAcativity.class);
                        startActivity(intent);
                    } else {
                        System.out.println("password is incorrect");

                        passwordstring = "";
                        System.out.println("Retry");
                        Toast.makeText(getApplicationContext(), "password is incorrect", Toast.LENGTH_SHORT).show();
                    }

                }

            }

        });


        ImageAdapter adapter = new ImageAdapter(this, imageChunks);
        grid.setAdapter(adapter);
        grid.setNumColumns((int) Math.sqrt(imageChunks.size()));
    }
}