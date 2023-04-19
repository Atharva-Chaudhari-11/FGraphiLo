package com.example.finallogin;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity  {
    Button  btnGallery,b1;
    private final int GALLERY_REQ_CODE=1000;
    ImageView imgGallery ;
    Uri uri;
    FirebaseDatabase database;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        imgGallery = findViewById(+R.id.imgGallery);
        btnGallery = findViewById(R.id.btnCamera);
        b1 = (Button) findViewById(+R.id.three);
        database= FirebaseDatabase.getInstance();
        storage=FirebaseStorage.getInstance();


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainActivity2.this,MainActivity5.class);
                intent.putExtra("img",uri);
                startActivity(intent);
            }
        });

        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                System.out.println("hii1");
//                Intent iGallery = new Intent(Intent.ACTION_PICK);
//                iGallery.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                startActivityForResult(iGallery, GALLERY_REQ_CODE);
//                System.out.println("hiii22");

                Intent intent=new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,1);
            }
        });
    }
    //    }
    @Override
    protected void onActivityResult( int requestCode, int resultCode, @Nullable Intent data){
        System.out.println("hii4444444");
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && data!=null){
            uri=data.getData();
            imgGallery.setImageURI(uri);
            final StorageReference reference=storage.getReference().child("image");
            reference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            database.getReference().child("image").setValue(uri.toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(getApplicationContext(),"Image uploaded",Toast.LENGTH_SHORT).show();
                                }
                            });

                        }
                    });
                }
            });

        }
    }





    public void logoutprocess(View view)
    {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity2.this,MainActivity.class));
    }


}
