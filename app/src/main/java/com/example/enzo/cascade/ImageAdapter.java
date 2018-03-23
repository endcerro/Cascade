package com.example.enzo.cascade;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by enzo on 19/03/18.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    private int count;
    private int id;
    public Integer[] cellList;


    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count){
        this.count = count;
    }

    public Object getItem(int position) {
return id;
    }

    public long getItemId(int position) {
        return id;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(100, 100));

            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setAdjustViewBounds(true);
            //imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        Random r = new Random();
        int v = r.nextInt(4)+1;
        imageView.setImageResource(cellList[position]);
        //imageView.setId(v);
        return imageView;
    }


    public void fillList()
    {
        cellList = new Integer[count];
        for (int i=0;i<count;i++)
        {
            Random r = new Random();
            int v = r.nextInt(4)+1;
            cellList[i]=mThumbIds[v];
        }
    }




    // references to our images
    public Integer[] mThumbIds = {
            R.drawable.tuile0,
            R.drawable.tuile1,
            R.drawable.tuile2,
            R.drawable.tuile3,
            R.drawable.tuile4
    };
}