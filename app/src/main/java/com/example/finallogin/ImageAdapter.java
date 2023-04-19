package com.example.finallogin;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

//The adapter class associated with the ChunkedImageActivity class
public class ImageAdapter extends BaseAdapter {
    private ArrayList<Integer>itemID=new ArrayList<>();
    private Integer[] mThumbIds = new Integer[9];


    //  ArrayList<Integer> partIds = getIntent().getIntegerArrayListExtra("part ids");


    private List<Integer> mClickedIds;


    private List<Integer> mSelectedParts = new ArrayList<>();
    private  Context mContext;
    private ArrayList<Bitmap> imageChunks = new ArrayList<>(9);
    private int imageWidth, imageHeight;


    //constructor
    public ImageAdapter(Context c, ArrayList<Bitmap> images) {
        mContext = c;
        imageChunks = images;
        imageWidth = images.get(0).getWidth();
        imageHeight = images.get(0).getHeight();
        mClickedIds = new ArrayList<>();
//        Collections.shuffle(imageChunks);
    }

    @Override
    public int getCount() {
        return imageChunks.size();
    }

    @Override
    public Object getItem(int position) {
        return imageChunks.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView image;

        if (convertView == null) {
            image = new ImageView(mContext);
//        if (convertView == null) {
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.gr, parent, false);
            /*
             -----> NOTE: I have set imageWidth - 10 and imageHeight
             -----> as arguments to LayoutParams class.
             -----> But you can take anything as per your requirement
             */
            image.setLayoutParams(new GridView.LayoutParams(imageWidth - 10, imageHeight));
            image.setPadding(0, 0, 0, 0);

            image.setScaleType(ImageView.ScaleType.CENTER_CROP);

            image.setLayoutParams(new GridView.LayoutParams(340, 340));
        } else {
            image = (ImageView) convertView;
        }
        image.setImageBitmap(imageChunks.get(position));
        System.out.println("2132461");
        return image;
    }


}
