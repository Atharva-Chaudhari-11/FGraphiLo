package com.example.finallogin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.crashlytics.buildtools.reloc.org.apache.commons.io.output.ByteArrayOutputStream;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity5 extends AppCompatActivity implements View.OnClickListener {
    Button f1,f2,f3;
    ImageView imgGallery2;
    Button split;
    Intent intent;
    ArrayList<Integer> parts = new ArrayList<>(9);
int id=0;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);
        imgGallery2 = findViewById(+R.id.imgGallery2);
        intent = getIntent();
        Uri uri = (Uri) intent.getParcelableExtra("img");
        imgGallery2.setImageURI(uri);


        f1 = (Button) findViewById(+R.id.three);
        f2 = (Button) findViewById(+R.id.four);
        f3 = (Button) findViewById(+R.id.five);

        f1.setOnClickListener(this);
        f2.setOnClickListener(this);
        f3.setOnClickListener(this);

    }
    public void onClick(View view){

        //chunkNumbers is to tell how many chunks the image should split
        int chunkNumbers = 0;

        switch (view.getId()) {
            case R.id.three:
                chunkNumbers = 9 ;
                break;
            case R.id.four:
                chunkNumbers = 16 ;
                break;
            case R.id.five:
                chunkNumbers = 25 ;
        }
        //Getting the source image to split
        imgGallery2 = findViewById(R.id.imgGallery2);

        //invoking this method makes the actual splitting of the source image to given number of chunks
        splitImage(imgGallery2, chunkNumbers);


    }



    private void splitImage(ImageView image, int chunkNumbers) {
        int rows,cols;

        //For height and width of the small image chunks
        int chunkHeight,chunkWidth;

        //To store all the small image chunks in bitmap format in this list
        ArrayList<Bitmap> chunkedImages = new ArrayList<Bitmap>(chunkNumbers);

        HashMap<Integer,Bitmap> cImages = new HashMap(chunkNumbers);


        BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        //Getting the scaled bitmap of the source image
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream); // 50 is the quality percentage
        byte[] compressedBytes = outputStream.toByteArray();
        Bitmap scaledBitmap = BitmapFactory.decodeByteArray(compressedBytes, 0, compressedBytes.length);

        rows = cols = (int) Math.sqrt(chunkNumbers);
        chunkHeight = bitmap.getHeight() / rows;
        chunkWidth = bitmap.getWidth() / cols;

        //--id
       // int id=0;
        //xCoord and yCoord are the pixel positions of the image chunks
        int yCoord = 0;
        for(int x = 0; x < rows; x++) {
            int xCoord = 0;
            for(int y = 0; y < cols; y++) {
//                chunkedImages.add(Bitmap.createBitmap(scaledBitmap, xCoord, yCoord, chunkWidth, chunkHeight));
                id++;
                cImages.put(id,Bitmap.createBitmap(scaledBitmap, xCoord, yCoord, chunkWidth, chunkHeight));
                xCoord += chunkWidth;

                System.out.println(" our each line id is "+id);
                //parts.add(partid);

            }
            yCoord += chunkHeight;

            System.out.println("id is "+id);
        }
        System.out.println("222");

//        Intent intent = new Intent(MainActivity5.this, MainActivity4.class);
//        System.out.println("55");
//
////        intent.putParcelableArrayListExtra("image chunks", chunkedImages);
//
//        intent.putExtra("image chunks",cImages);
//        System.out.println("ns"+id);
//        System.out.println("000");
//        startActivity(intent);


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are You Sure!!");
        builder.setMessage("TO Set This Image Is Set As Password ?");
        builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity5.this, MainActivity4.class);
                System.out.println("55");

//        intent.putParcelableArrayListExtra("image chunks", chunkedImages);

                intent.putExtra("image chunks",cImages);
//                System.out.println("ns"+id);
                System.out.println("000");
                startActivity(intent);
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(MainActivity5.this, MainActivity2.class);
                Toast.makeText(getApplicationContext(),"select Image again",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });

        builder.show();

        Toast.makeText(getApplicationContext(),"Password Set Successfully",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }
}





//        Intent intent = new Intent(MainActivity5.this, MainActivity4.class);
//        System.out.println("55");
//
////        intent.putParcelableArrayListExtra("image chunks", chunkedImages);
//
//        intent.putExtra("image chunks",cImages);
//        System.out.println("ns"+id);
//        System.out.println("000");
//        startActivity(intent);





//    BitmapDrawable drawable = (BitmapDrawable) image.getDrawable();
//    Bitmap bitmap = drawable.getBitmap();
//
//    Bitmap scaledBitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);


//
//
//    AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Are You Sure!!");
//                builder.setMessage("TO Set This Image Is Set As Password ?");
//                builder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialog, int which) {
//        Intent intent = new Intent(MainActivity5.this, MainActivity4.class);
//        System.out.println("55");
//
////        intent.putParcelableArrayListExtra("image chunks", chunkedImages);
//
//        intent.putExtra("image chunks",cImages);
////                System.out.println("ns"+id);
//        System.out.println("000");
//        startActivity(intent);
//        }
//        });
//        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//@Override
//public void onClick(DialogInterface dialog, int which) {
//        Intent intent = new Intent(MainActivity5.this, MainActivity2.class);
//        Toast.makeText(getApplicationContext(),"select Image again",Toast.LENGTH_SHORT).show();
//        startActivity(intent);
//        }
//        });
//
//        builder.show();